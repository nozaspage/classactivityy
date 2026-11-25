package com.example.githubusers
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException
data class User(
    val id: Int,
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String? = null
)
interface GitHubService {
    @GET("/users")
    suspend fun users(@Query("per_page") perPage: Int = 5): List<User>
@GET("/users/{login}")
suspend fun user(@Path("login") login: String): User
}
object ApiInstance {
    private const val BASE_URL = "https://api.github.com/"
private val authInterceptor = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer demo-token").build())
}

private val loggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
    }
}

private val retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun \<T> createService(serviceClass: Class\<T>): T = retrofit.create(serviceClass)
}
sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class HttpError(val code: Int, val message: String) : ApiResult<Nothing>
    data object NetworkError : ApiResult<Nothing>
}
sealed interface UiState {
    data object Loading : UiState
    data class Success(val users: List<User>) : UiState
    data class Error(val message: String, val isNetwork: Boolean = false, val retries: Int = 0) : UiState
}
class GitHubViewModel(
    private val service: GitHubService = ApiInstance.createService(GitHubService::class.java)
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
private val _users = MutableStateFlow\<List<User>>(emptyList())
val users: StateFlow\<List<User>> = _users.asStateFlow()

private val _selectedUser = MutableStateFlow\<User?>(null)
val selectedUser: StateFlow\<User?> = _selectedUser.asStateFlow()

private var retries = 0

private suspend fun \<T> safeApiCall(call: suspend () -> T): ApiResult\<T> = try {
    ApiResult.Success(call())
} catch (e: HttpException) {
    ApiResult.HttpError(e.code(), "Server error: ${e.code()}")
} catch (_: IOException) {
    ApiResult.NetworkError
}

fun load(perPage: Int = 5) = viewModelScope.launch {
    _uiState.value = UiState.Loading
    when (val res = safeApiCall { service.users(perPage) }) {
        is ApiResult.Success -> {
            retries = 0
            _users.value = res.data
            _uiState.value = UiState.Success(res.data)
        }
        is ApiResult.HttpError -> _uiState.value = UiState.Error(res.message, false, retries)
        is ApiResult.NetworkError -> {
            retries++
            _uiState.value = UiState.Error("No network", true, retries)
        }
    }
}

fun loadUser(login: String) = viewModelScope.launch {
    when (val res = safeApiCall { service.user(login) }) {
        is ApiResult.Success -> _selectedUser.value = res.data
        is ApiResult.HttpError -> _uiState.value = UiState.Error(res.message, false, retries)
        is ApiResult.NetworkError -> {
            retries++
            _uiState.value = UiState.Error("No network", true, retries)
        }
    }
}
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { GitHubScreen() } }
    }
}
@Composable
fun GitHubScreen(vm: GitHubViewModel = viewModel()) {
    val state by vm.uiState.collectAsState()
    val selected by vm.selectedUser.collectAsState()
    val context = LocalContext.current
LaunchedEffect(Unit) { vm.load(5) }

Column(Modifier.fillMaxSize().padding(16.dp)) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text("Users", style = MaterialTheme.typography.headlineSmall)
        TextButton(onClick = { vm.loadUser("no-such-login-xyz") }) { Text("404") }
    }
    when (val s = state) {
        is UiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
        is UiState.Error -> Column(Modifier.fillMaxSize(), Alignment.CenterHorizontally, Arrangement.Center) {
            Text(s.message)
            Spacer(Modifier.height(8.dp))
            if (s.isNetwork && s.retries >= 3) {
                Button(onClick = { context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }) {
                    Text("Open Wi-Fi settings")
                }
            } else {
                Button(onClick = { vm.load(5) }) { Text("Retry") }
            }
        }
        is UiState.Success -> {
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(s.users, key = { it.id }) { u ->
                    Card(Modifier.fillMaxWidth().clickable { vm.loadUser(u.login) }) {
                        Text(u.login, Modifier.padding(12.dp))
                    }
                }
            }
            selected?.let {
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text("id: ${it.id} | avatarUrl: ${it.avatarUrl}")
            }
        }
    }
}
}
Retrofit + Compose



data class User(val id: Int, val login: String, @SerializedName("avatar_url") val avatarUrl: String? = null)

interface Api {
    @GET("/users") suspend fun users(@Query("per_page") perPage: Int = 5): List<User>
    @GET("/users/{login}") suspend fun user(@Path("login") login: String): User
}

object Net {
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}

class GitHubVm : ViewModel() {
    val users = MutableStateFlow<List<User>>(emptyList())
    val error = MutableStateFlow<String?>(null)
    val retries = MutableStateFlow(0)

    fun load() = viewModelScope.launch {
        error.value = null
        try {
            users.value = Net.api.users(5)
            retries.value = 0
        } catch (_: IOException) {
            retries.value += 1
            error.value = "No network"
        } catch (e: HttpException) {
            error.value = "Server error: ${e.code()}"
        }
    }
}
Coroutines


fun main() = runBlocking {
    launch { delay(200); println("A done") }
    launch { delay(100); println("B done") }
    println("Start")

    val job = launch {
        try {
            repeat(5) { println("tick $it"); delay(100) }
        } finally {
            println("cleanup")
        }
    }
    delay(250)
    job.cancelAndJoin()

    val time = measureTimeMillis {
        val a = async { delay(100); "Ada" }
        val b = async { delay(100); 30 }
        println("${a.await()} ${b.await()}")
    }
    println("Time: ${time}ms")
}
Offline State + Sign-In + Taste Picke




@Composable
fun NameBox(name: String, onChange: (String) -> Unit) {
    OutlinedTextField(value = name, onValueChange = onChange)
}

@Composable
fun NameDemo() {
    var name by rememberSaveable { mutableStateOf("") }
    Column {
        NameBox(name) { name = it }
        Text("Hello, $name")
    }
}

sealed interface AuthState {
    data object Idle : AuthState
    data object Loading : AuthState
    data class Error(val msg: String) : AuthState
    data class Done(val email: String) : AuthState
}

@Composable
fun TastePicker(artists: List<String>, liked: Set<String>, onToggle: (String) -> Unit) {
    LazyColumn {
        items(artists) { artist ->
            Row(Modifier.clickable { onToggle(artist) }.padding(12.dp)) {
                Text(artist, Modifier.weight(1f))
                Text(if (artist in liked) "♥" else "♡")
            }
        }
    }
}
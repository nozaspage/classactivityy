package uz.ttpu.movieshelf

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import uz.ttpu.movieshelf.ui.theme.MovieShelfTheme
import java.io.IOException

fun exercise1_1() {
    val city = "Tashkent"
    var temperature = 18.5
    temperature = 21.0
    println("$city: $temperature")
}

fun exercise1_2() {
    val price = 3000
    var stock = 10
    var totalCollected = 0

    stock -= 1
    totalCollected += price
    println("Stock: $stock, Total collected: $totalCollected")

    stock -= 1
    totalCollected += price
    println("Stock: $stock, Total collected: $totalCollected")

    stock -= 1
    totalCollected += price
    println("Stock: $stock, Total collected: $totalCollected")

    println("Grand total collected: $totalCollected")
}

fun exercise2_1() {
    val customer = "Diyor"
    val total = 145000

    println("Hi $customer, your order of $total so'm is confirmed.")
    println("Free delivery: ${if (total >= 100000) "Yes" else "No"}")
}

fun printReceipt(item: String, price: Int) {
    val receipt = """
        Item: "$item"
        Price: $$price
        Thank you for your purchase!
    """.trimIndent()
    println(receipt)
}

fun exercise2_2() {
    printReceipt("Kotlin Mug", 12)
    printReceipt("Kotlin Sticker Pack", 5)
}

fun exercise3_1() {
    println(9 / 4)
    println(9.0 / 4)
    println(9 % 4)
    println(9 / 4.0)
    println(2 + 3 * 5)
    println((2 + 3) * 5)
}

fun exercise4_1() {
    val defaultPlaylist = listOf("Song A", "Song B", "Song C", "Song D", "Song E")
    println(defaultPlaylist)

    val likedSongs = mutableListOf<String>()
    likedSongs.add("Song A")
    likedSongs.add("Song C")
    likedSongs.add("Song F")
    println(likedSongs)
}

fun exercise4_2() {
    val menu = mapOf("Coffee" to 15000, "Tea" to 10000, "Croissant" to 12000, "Bagel" to 18000)

    for ((name, price) in menu) {
        println("$name costs $price")
    }

    val total = menu.values.sumOf { it }
    println("Total menu price: $total")

    val cheapest = menu.minByOrNull { it.value }
    val priciest = menu.maxByOrNull { it.value }
    println("Cheapest: ${cheapest?.key}, Priciest: ${priciest?.key}")
}

fun exercise4_3() {
    val signIns = listOf("Aziz", "Nodira", "Aziz", "Bekzod", "Nodira", "Aziz")

    val uniqueStudents: Set<String> = signIns.toSet()
    val attendance: Map<String, Boolean> = uniqueStudents.associateWith { true }

    println(attendance)
    println("Present: ${attendance.size} students")
}

fun exercise5_1(score: Int): String {
    return when {
        score >= 90 -> "A"
        score >= 80 -> "B"
        score >= 70 -> "C"
        score >= 60 -> "D"
        else -> "F"
    }
}

fun exercise5_2() {
    for (i in 25 downTo 0 step 5) {
        println(i)
    }
    println("Liftoff!")
}

fun exercise5_3() {
    val attempts = listOf(1111, 4321, 9999)
    val correctPin = 4321
    var i = 0
    var unlocked = false

    while (i < attempts.size && !unlocked) {
        if (attempts[i] == correctPin) {
            unlocked = true
        } else {
            println("Wrong PIN: ${attempts[i]}")
        }
        i++
    }

    println(if (unlocked) "Card unlocked" else "Card locked")
}

fun exercise5_4() {
    for (n in 1..30) {
        val output = when {
            n % 15 == 0 -> "FizzBuzz"
            n % 3 == 0 -> "Fizz"
            n % 5 == 0 -> "Buzz"
            else -> n.toString()
        }
        println(output)
    }
}

fun notify(message: String, channel: String = "Email"): String {
    return "[$channel] $message"
}

fun exercise6_1() {
    println(notify("Server restarted"))
    println(notify("Server restarted", "SMS"))
    println(notify(channel = "Push", message = "Server restarted"))
}

fun absoluteValue(x: Int): Int = if (x < 0) -x else x

fun isPassing(score: Int): Boolean = score >= 60

fun exercise6_3() {
    val scores = listOf(45, 60, 82, 59, 91, 60, 33)
    val passingScores = scores.filter { isPassing(it) }
    println(passingScores)
}

fun digitSum(n: Int): Int {
    var remaining = n
    var sum = 0
    while (remaining > 0) {
        sum += remaining % 10
        remaining /= 10
    }
    return sum
}

fun exercise6_4() {
    println(digitSum(1234))
    println(digitSum(507))
}

class BasicBook(val title: String, val author: String, val pages: Int)

fun exercise7_1() {
    val book1 = BasicBook("Atomic Habits", "James Clear", 320)
    val book2 = BasicBook("Kotlin in Action", "Dmitry Jemerov", 360)

    println("${book1.title} by ${book1.author}")
    println("${book2.title} by ${book2.author}")
}

data class Book(val title: String, val author: String, val pages: Int)

fun exercise7_2() {
    val book1 = Book("Atomic Habits", "James Clear", 320)
    val book2 = Book("Atomic Habits", "James Clear", 320)
    val revisedEdition = book1.copy(pages = 340)

    println(book1)
    println(book1 == book2)
    println(revisedEdition)
}

class Student(val name: String, val grades: List<Int>) {
    fun average(): Double = grades.sumOf { it }.toDouble() / grades.size
}

fun exercise7_3() {
    val amina = Student("Amina", listOf(85, 90, 78))
    val diyor = Student("Diyor", listOf(60, 72, 68, 74))

    println("${amina.name}: ${amina.average()}")
    println("${diyor.name}: ${diyor.average()}")
}

fun exercise8_1() {
    var nickname: String? = null
    println(nickname?.length ?: 0)

    nickname = "Shery"
    println(nickname?.length ?: 0)
}

fun describeString(maybeString: String?): String {
    if (maybeString != null && maybeString.length > 0) {
        return maybeString
    }
    return "empty or null"
}

fun describeStringElvis(maybeString: String?): String {
    return maybeString?.takeIf { it.isNotEmpty() } ?: "empty or null"
}

fun exercise8_3() {
    val readings: List<String?> = listOf(null, null, "23.5", "19.1", null)
    val firstValid = readings.firstOrNull { it != null } ?: "No data"
    println(firstValid)
}

fun String.shout(): String = this.uppercase() + "!!!"

fun exercise10_1() {
    println("hello".shout())
    println("kotlin is fun".shout())
}

fun Int.isPrime(): Boolean {
    if (this < 2) return false
    for (divisor in 2 until this) {
        if (this % divisor == 0) return false
    }
    return true
}

fun exercise10_2() {
    for (n in 2..20) {
        if (n.isPrime()) println(n)
    }
}

fun exercise11_1() {
    val maybeName: String? = "Amin"
    maybeName?.let {
        println(it.uppercase())
    }
}

class MutableBook(var title: String = "", var author: String = "", var pages: Int = 0)

fun exercise11_2() {
    val book = MutableBook().apply {
        title = "Kotlin in Action"
        author = "Dmitry Jemerov"
        pages = 360
    }
    println(book.title)
}

fun exercise11_3() {
    val book = MutableBook(title = "Atomic Habits", author = "James Clear", pages = 320).also {
        println("Created book: ${it.title}")
    }
    println(book.author)
}

fun exercise11_4() {
    val b1 = MutableBook(title = "Sample", author = "Someone", pages = 0).let {
        it.pages = 400
        it
    }
    println(b1.title)

    val b2 = MutableBook(title = "Sample", author = "Someone", pages = 0).apply {
        pages = 400
    }
    println(b2.title)

    val b3 = MutableBook(title = "Sample", author = "Someone", pages = 400).also {
        println(it.title)
    }
    println(b3.pages)
}

fun buildGreeting(action: StringBuilder.() -> Unit): String {
    val builder = StringBuilder()
    builder.action()
    return builder.toString()
}

fun exercise12_1() {
    val message = buildGreeting {
        append("Hello, ")
        append("class! ")
        append("Welcome to Kotlin.")
    }
    println(message)
}

abstract class AbstractShape {
    abstract fun area(): Double
    fun describe(): String = "This shape has an area of ${area()}"
}

class AbstractCircle(val radius: Double) : AbstractShape() {
    override fun area(): Double = Math.PI * radius * radius
}

class AbstractRectangle(val width: Double, val height: Double) : AbstractShape() {
    override fun area(): Double = width * height
}

fun exercise13_1() {
    println(AbstractCircle(2.0).describe())
    println(AbstractRectangle(3.0, 4.0).describe())
}

interface Shape {
    fun area(): Double
    fun describe(): String = "This shape has an area of ${area()}"
}

class Circle(val radius: Double) : Shape {
    override fun area(): Double = Math.PI * radius * radius
}

class Rectangle(val width: Double, val height: Double) : Shape {
    override fun area(): Double = width * height
}

fun exercise13_2() {
    println(Circle(2.0).describe())
    println(Rectangle(3.0, 4.0).describe())
}

object AppConfig {
    val appName = "GradeBook"
    var isDebug = false

    fun toggleDebug() {
        isDebug = !isDebug
    }
}

fun exercise14_1() {
    println(AppConfig.appName)
    AppConfig.toggleDebug()
    println(AppConfig.isDebug)
}

class User private constructor(val name: String) {
    companion object {
        fun create(name: String): User = User(name)
    }
}

fun exercise14_2() {
    val user = User.create("Amin")
    println(user.name)
}

open class Vehicle(val make: String, val model: String) {
    open fun honk(): String = "Beep!"
}

class Car(make: String, model: String, val numberOfDoors: Int) : Vehicle(make, model) {
    override fun honk(): String = "Honk honk!"
}

fun exercise15_1() {
    val car = Car("Chevrolet", "Cobalt", 4)
    println("${car.make} ${car.model}: ${car.honk()}")
}

class Seminar3MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}

class HomeFragment : Fragment() {

    fun navigateWithoutSafeArgs() {
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

    fun navigateWithSafeArgs(itemId: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(itemId)
        findNavController().navigate(action)
    }
}

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receivedItemId = args.itemId
        println(receivedItemId)
    }
}

data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val rating: Double,
    val isFavorite: Boolean = false,
)

data class MoviesResult(
    val movies: List<Movie>,
    val isFromCache: Boolean,
)

interface MovieRepository {
    suspend fun getMovies(): MoviesResult
    suspend fun toggleFavorite(movieId: Int): Boolean
}

class GetMoviesUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(): MoviesResult {
        val result = repository.getMovies()
        val sorted = result.movies.sortedWith(
            compareByDescending<Movie> { it.rating }
                .thenBy { it.title }
        )
        return result.copy(movies = sorted)
    }
}

class ToggleFavoriteUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): Boolean {
        return repository.toggleFavorite(movieId)
    }
}

class GetFavoriteMoviesUseCase(
    private val getMovies: GetMoviesUseCase,
) {
    suspend operator fun invoke(): MoviesResult {
        val result = getMovies()
        return result.copy(movies = result.movies.filter { it.isFavorite })
    }
}

data class MovieDto(
    val id: Int,
    val title: String,
    val releaseYear: Int,
    val score: Double,
)

interface MovieRemoteDataSource {
    suspend fun fetchMovies(): List<MovieDto>
}

class FakeMovieRemoteDataSource : MovieRemoteDataSource {

    @Volatile
    var isOnline: Boolean = true

    override suspend fun fetchMovies(): List<MovieDto> {
        delay(800)
        if (!isOnline) throw IOException("No connection")
        return listOf(
            MovieDto(1, "The Silk Road Express", 2019, 8.4),
            MovieDto(2, "Midnight in Samarkand", 2021, 7.9),
            MovieDto(3, "Registan Rhapsody", 2018, 8.4),
            MovieDto(4, "Steel Orchard", 2023, 6.8),
            MovieDto(5, "Chorsu Blues", 2020, 7.2),
            MovieDto(6, "Echoes of Khiva", 2022, 9.0),
            MovieDto(7, "Paper Minarets", 2017, 7.9),
            MovieDto(8, "Last Train to Bukhara", 2024, 8.1),
        )
    }
}

interface MovieLocalDataSource {
    suspend fun getCachedMovies(): List<MovieDto>?
    suspend fun saveMovies(movies: List<MovieDto>)
    suspend fun getFavoriteIds(): Set<Int>
    suspend fun setFavorite(id: Int, favorite: Boolean)
}

class InMemoryMovieLocalDataSource : MovieLocalDataSource {

    private var cached: List<MovieDto>? = null
    private val favorites = mutableSetOf<Int>()

    override suspend fun getCachedMovies(): List<MovieDto>? = cached

    override suspend fun saveMovies(movies: List<MovieDto>) {
        cached = movies
    }

    override suspend fun getFavoriteIds(): Set<Int> = favorites.toSet()

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        if (favorite) favorites.add(id) else favorites.remove(id)
    }
}

class SharedPrefsMovieLocalDataSource(context: Context) : MovieLocalDataSource {

    private val prefs = context.getSharedPreferences("movieshelf", Context.MODE_PRIVATE)
    private var cached: List<MovieDto>? = null

    override suspend fun getCachedMovies(): List<MovieDto>? = cached

    override suspend fun saveMovies(movies: List<MovieDto>) {
        cached = movies
    }

    override suspend fun getFavoriteIds(): Set<Int> {
        return prefs.getStringSet("favorites", emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet()
            ?: emptySet()
    }

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        val current = prefs.getStringSet("favorites", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (favorite) {
            current.add(id.toString())
        } else {
            current.remove(id.toString())
        }
        prefs.edit().putStringSet("favorites", current).apply()
    }
}

fun MovieDto.toDomain(isFavorite: Boolean): Movie {
    return Movie(
        id = id,
        title = title,
        year = releaseYear,
        rating = score,
        isFavorite = isFavorite,
    )
}

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
) : MovieRepository {

    override suspend fun getMovies(): MoviesResult {
        val favoriteIds = local.getFavoriteIds()
        return try {
            val remoteMovies = remote.fetchMovies()
            local.saveMovies(remoteMovies)
            MoviesResult(
                movies = remoteMovies.map { it.toDomain(it.id in favoriteIds) },
                isFromCache = false,
            )
        } catch (e: IOException) {
            val cached = local.getCachedMovies() ?: throw e
            MoviesResult(
                movies = cached.map { it.toDomain(it.id in favoriteIds) },
                isFromCache = true,
            )
        }
    }

    override suspend fun toggleFavorite(movieId: Int): Boolean {
        val currentlyFavorite = movieId in local.getFavoriteIds()
        val newState = !currentlyFavorite
        local.setFavorite(movieId, newState)
        return newState
    }
}

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(
        val movies: List<Movie>,
        val isFromCache: Boolean,
        val favoritesOnly: Boolean = false,
    ) : MovieListUiState
    data class Error(val message: String) : MovieListUiState
}

class MovieListViewModel(
    private val getMovies: GetMoviesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
    private val getFavoriteMovies: GetFavoriteMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val state: StateFlow<MovieListUiState> = _state.asStateFlow()

    private var favoritesOnly = false

    init {
        load()
    }

    fun onRefresh() = load()

    fun onFavoritesOnlyChange(enabled: Boolean) {
        favoritesOnly = enabled
        load()
    }

    fun onFavoriteClick(movieId: Int) {
        viewModelScope.launch {
            val newFavoriteState = toggleFavorite(movieId)
            _state.update { current ->
                if (current is MovieListUiState.Success) {
                    val updatedList = current.movies.map { movie ->
                        if (movie.id == movieId) movie.copy(isFavorite = newFavoriteState) else movie
                    }
                    val finalList = if (favoritesOnly) {
                        updatedList.filter { it.isFavorite }
                    } else {
                        updatedList
                    }
                    current.copy(movies = finalList)
                } else {
                    current
                }
            }
        }
    }

    private fun load() {
        _state.value = MovieListUiState.Loading
        viewModelScope.launch {
            try {
                val result = if (favoritesOnly) getFavoriteMovies() else getMovies()
                _state.value = MovieListUiState.Success(
                    movies = result.movies,
                    isFromCache = result.isFromCache,
                    favoritesOnly = favoritesOnly,
                )
            } catch (e: IOException) {
                _state.value = MovieListUiState.Error(
                    "Can't load movies. Check your connection and try again."
                )
            }
        }
    }
}

@Composable
fun MovieListScreen(
    state: MovieListUiState,
    onFavoriteClick: (Int) -> Unit,
    onRefresh: () -> Unit,
    onFavoritesOnlyChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is MovieListUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is MovieListUiState.Error -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(text = state.message)
                    Button(onClick = onRefresh) {
                        Text("Retry")
                    }
                }
            }

            is MovieListUiState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        FilterChip(
                            selected = state.favoritesOnly,
                            onClick = { onFavoritesOnlyChange(!state.favoritesOnly) },
                            label = { Text("Favorites only") },
                        )
                        TextButton(onClick = onRefresh) {
                            Text("Refresh")
                        }
                    }

                    if (state.isFromCache) {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Offline — showing saved data",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.movies, key = { it.id }) { movie ->
                            MovieItem(
                                movie = movie,
                                onFavoriteClick = { onFavoriteClick(movie.id) },
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "${movie.year} · Rating ${movie.rating}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (movie.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (movie.isFavorite) "Remove from favorites" else "Add to favorites",
            )
        }
    }
}

private val sampleMovies = listOf(
    Movie(6, "Echoes of Khiva", 2022, 9.0, isFavorite = true),
    Movie(3, "Registan Rhapsody", 2018, 8.4, isFavorite = false),
)

@Preview(showBackground = true)
@Composable
private fun PreviewLoading() {
    MovieListScreen(
        state = MovieListUiState.Loading,
        onFavoriteClick = {},
        onRefresh = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSuccess() {
    MovieListScreen(
        state = MovieListUiState.Success(movies = sampleMovies, isFromCache = false),
        onFavoriteClick = {},
        onRefresh = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSuccessCache() {
    MovieListScreen(
        state = MovieListUiState.Success(movies = sampleMovies, isFromCache = true),
        onFavoriteClick = {},
        onRefresh = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewError() {
    MovieListScreen(
        state = MovieListUiState.Error("Can't load movies. Check your connection and try again."),
        onFavoriteClick = {},
        onRefresh = {},
    )
}

class AppContainer(context: Context) {
    val fakeRemote = FakeMovieRemoteDataSource()
    private val local: MovieLocalDataSource = SharedPrefsMovieLocalDataSource(context)
    private val repository: MovieRepository = MovieRepositoryImpl(fakeRemote, local)

    val getMovies = GetMoviesUseCase(repository)
    val toggleFavorite = ToggleFavoriteUseCase(repository)
    val getFavoriteMovies = GetFavoriteMoviesUseCase(getMovies)
}

class MovieShelfApp : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(applicationContext)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as MovieShelfApp).container

        setContent {
            MovieShelfTheme {
                val viewModel: MovieListViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer {
                            MovieListViewModel(
                                container.getMovies,
                                container.toggleFavorite,
                                container.getFavoriteMovies,
                            )
                        }
                    }
                )
                val state by viewModel.state.collectAsStateWithLifecycle()
                var online by remember { mutableStateOf(container.fakeRemote.isOnline) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("Fake network online", Modifier.weight(1f))
                            Switch(
                                checked = online,
                                onCheckedChange = {
                                    online = it
                                    container.fakeRemote.isOnline = it
                                },
                            )
                        }
                        MovieListScreen(
                            state = state,
                            onFavoriteClick = viewModel::onFavoriteClick,
                            onRefresh = viewModel::onRefresh,
                            onFavoritesOnlyChange = viewModel::onFavoritesOnlyChange,
                        )
                    }
                }
            }
        }
    }
}

class FakeMovieRepository(
    private val result: MoviesResult,
) : MovieRepository {
    override suspend fun getMovies() = result
    override suspend fun toggleFavorite(movieId: Int) = true
}

class GetMoviesUseCaseTest {

    @Test
    fun movies_are_sorted_by_rating_desc_then_title() = runBlocking<Unit> {
        val input = listOf(
            Movie(1, "B", 2020, 8.0),
            Movie(2, "A", 2021, 8.0),
            Movie(3, "C", 2022, 9.1),
        )
        val useCase = GetMoviesUseCase(FakeMovieRepository(MoviesResult(input, false)))

        val output = useCase()

        assertEquals(listOf("C", "A", "B"), output.movies.map { it.title })
    }

    @Test
    fun is_from_cache_flag_is_passed_through() = runBlocking<Unit> {
        val useCase = GetMoviesUseCase(FakeMovieRepository(MoviesResult(emptyList(), true)))

        val output = useCase()

        assertTrue(output.isFromCache)
    }

    @Test
    fun get_favorite_movies_filters_only_favorites() = runBlocking<Unit> {
        val input = listOf(
            Movie(1, "A", 2020, 8.0, isFavorite = false),
            Movie(2, "B", 2021, 9.0, isFavorite = true),
        )
        val getMovies = GetMoviesUseCase(FakeMovieRepository(MoviesResult(input, false)))
        val getFavorites = GetFavoriteMoviesUseCase(getMovies)

        val output = getFavorites()

        assertEquals(listOf("B"), output.movies.map { it.title })
    }
}

private class FakeRemote(
    var movies: List<MovieDto> = emptyList(),
    var fail: Boolean = false,
) : MovieRemoteDataSource {
    override suspend fun fetchMovies(): List<MovieDto> {
        if (fail) throw IOException("offline")
        return movies
    }
}

class MovieRepositoryImplTest {

    private val dtos = listOf(
        MovieDto(1, "A", 2020, 8.0),
        MovieDto(2, "B", 2021, 7.0),
    )

    @Test
    fun online_returns_fresh_data_with_favorite_flags() = runBlocking<Unit> {
        val local = InMemoryMovieLocalDataSource().also { it.setFavorite(2, true) }
        val repo = MovieRepositoryImpl(FakeRemote(dtos), local)

        val result = repo.getMovies()

        assertFalse(result.isFromCache)
        assertEquals(listOf(false, true), result.movies.map { it.isFavorite })
    }

    @Test
    fun offline_after_a_successful_load_returns_cache() = runBlocking<Unit> {
        val remote = FakeRemote(dtos)
        val local = InMemoryMovieLocalDataSource()
        val repo = MovieRepositoryImpl(remote, local)

        repo.getMovies()
        remote.fail = true
        val cachedResult = repo.getMovies()

        assertTrue(cachedResult.isFromCache)
        assertEquals(2, cachedResult.movies.size)
    }

    @Test(expected = IOException::class)
    fun offline_with_empty_cache_throws() = runBlocking<Unit> {
        val remote = FakeRemote(dtos, fail = true)
        val local = InMemoryMovieLocalDataSource()
        val repo = MovieRepositoryImpl(remote, local)

        repo.getMovies()
    }

    @Test
    fun toggle_returns_new_state() = runBlocking<Unit> {
        val local = InMemoryMovieLocalDataSource()
        val repo = MovieRepositoryImpl(FakeRemote(dtos), local)

        assertTrue(repo.toggleFavorite(1))
        assertFalse(repo.toggleFavorite(1))
    }
}
```[cite: 1, 2, 3, 11]
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

fun main() {
    exercise10_1()
    exercise10_2()
    exercise11_1()
    exercise11_2()
    exercise11_3()
    exercise11_4()
    exercise12_1()
    exercise13_1()
    exercise13_2()
    exercise14_1()
    exercise14_2()
    exercise15_1()
}
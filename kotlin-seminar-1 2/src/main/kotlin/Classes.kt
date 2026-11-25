fun exercise71() {
    class Book(val title: String, val author: String, val pages: Int)

    val book1 = Book("Atomic Habits", "James Clear", 320)
    val book2 = Book("Kotlin in Action", "Dmitry Jemerov", 360)

    println("${book1.title} by ${book1.author}")
    println("${book2.title} by ${book2.author}")
}

fun exercise72() {
    data class Book(val title: String, val author: String, val pages: Int)

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

fun exercise73() {
    val amina = Student("Amina", listOf(85, 90, 78))
    val diyor = Student("Diyor", listOf(60, 72, 68, 74))

    println("${amina.name}: ${amina.average()}")
    println("${diyor.name}: ${diyor.average()}")
}

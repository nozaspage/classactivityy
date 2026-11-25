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

fun main() {
    exercise1_1()
    exercise1_2()
    exercise2_1()
    exercise2_2()
    exercise3_1()
    exercise4_1()
    exercise4_2()
    exercise4_3()
    println(exercise5_1(85))
    exercise5_2()
    exercise5_3()
    exercise5_4()
    exercise6_1()
    println(absoluteValue(-15))
    exercise6_3()
    exercise6_4()
    exercise7_1()
    exercise7_2()
    exercise7_3()
    exercise8_1()
    println(describeString(null))
    println(describeStringElvis("Kotlin"))
    exercise8_3()
}
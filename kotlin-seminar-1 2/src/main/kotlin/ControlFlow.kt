fun gradeFor(score: Int): String = when {
    score >= 90 -> "A"
    score >= 80 -> "B"
    score >= 70 -> "C"
    score >= 60 -> "D"
    else -> "F"
}

fun exercise51() {
    for (score in listOf(95, 85, 72, 64, 30)) {
        println("$score -> ${gradeFor(score)}")
    }
}

fun exercise52() {
    for (i in 25 downTo 0 step 5) {
        println(i)
    }
    println("Liftoff!")
}

fun exercise53() {
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

fun exercise54() {
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

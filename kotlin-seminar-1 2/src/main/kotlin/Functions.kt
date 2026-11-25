fun notify(message: String, channel: String = "Email"): String {
    return "[$channel] $message"
}

fun exercise61() {
    println(notify("Server restarted"))
    println(notify("Server restarted", "SMS"))
    println(notify(channel = "Push", message = "Server restarted"))
}

fun absoluteValue(x: Int): Int = if (x < 0) -x else x

fun exercise62() {
    println(absoluteValue(-7))
    println(absoluteValue(5))
}

fun isPassing(score: Int): Boolean = score >= 60

fun exercise63() {
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

fun exercise64() {
    println(digitSum(1234))
    println(digitSum(507))
}

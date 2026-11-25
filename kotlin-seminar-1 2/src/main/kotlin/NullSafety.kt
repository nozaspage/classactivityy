fun exercise81() {
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

fun exercise82() {
    println(describeString(null))
    println(describeString(""))
    println(describeString("Kotlin"))
    println(describeStringElvis(null))
    println(describeStringElvis(""))
    println(describeStringElvis("Kotlin"))
}

fun exercise83() {
    val readings: List<String?> = listOf(null, null, "23.5", "19.1", null)
    val firstValid = readings.firstOrNull { it != null } ?: "No data"
    println(firstValid)

    val empty: List<String?> = listOf(null, null)
    println(empty.firstOrNull { it != null } ?: "No data")
}

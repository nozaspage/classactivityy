fun exercise41() {
    val defaultPlaylist = listOf("Song A", "Song B", "Song C", "Song D", "Song E")
    println(defaultPlaylist)

    val likedSongs = mutableListOf<String>()
    likedSongs.add("Song A")
    likedSongs.add("Song C")
    likedSongs.add("Song F")
    println(likedSongs)
}

fun exercise42() {
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

fun exercise43() {
    val signIns = listOf("Aziz", "Nodira", "Aziz", "Bekzod", "Nodira", "Aziz")

    val uniqueStudents: Set<String> = signIns.toSet()
    val attendance: Map<String, Boolean> = uniqueStudents.associateWith { true }

    println(attendance)
    println("Present: ${attendance.size} students")
}

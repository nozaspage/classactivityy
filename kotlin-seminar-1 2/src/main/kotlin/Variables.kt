fun exercise11() {
    val city = "Tashkent"
    var temperature = 18.5
    temperature = 19.2
    println("$city: $temperature")
}

fun exercise12() {
    val price = 3000
    var stock = 10
    var totalCollected = 0

    repeat(3) {
        stock -= 1
        totalCollected += price
        println("Stock: $stock, Total collected: $totalCollected")
    }

    println("Grand total collected: $totalCollected")
}

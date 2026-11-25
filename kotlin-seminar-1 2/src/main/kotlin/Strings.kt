fun exercise21() {
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

fun exercise22() {
    printReceipt("Kotlin Mug", 12)
    printReceipt("Kotlin Sticker Pack", 5)
}

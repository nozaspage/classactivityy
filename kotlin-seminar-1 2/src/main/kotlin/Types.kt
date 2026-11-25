fun exercise31() {
    println("9 / 4 = ${9 / 4} (${(9 / 4)::class.simpleName})")
    println("9.0 / 4 = ${9.0 / 4} (${(9.0 / 4)::class.simpleName})")
    println("9 % 4 = ${9 % 4} (${(9 % 4)::class.simpleName})")
    println("9 / 4.0 = ${9 / 4.0} (${(9 / 4.0)::class.simpleName})")
    println("2 + 3 * 5 = ${2 + 3 * 5} (${(2 + 3 * 5)::class.simpleName})")
    println("(2 + 3) * 5 = ${(2 + 3) * 5} (${((2 + 3) * 5)::class.simpleName})")
}

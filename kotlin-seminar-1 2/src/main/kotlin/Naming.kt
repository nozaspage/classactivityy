fun exercise91() {
    val renames = listOf(
        Triple("user_name", "variable", "userName"),
        Triple("MAX-RETRIES", "constant", "MAX_RETRIES"),
        Triple("get_user_name", "function", "getUserName()"),
        Triple("circle", "class", "Circle"),
        Triple("PI_value", "constant", "PI"),
        Triple("is_logged_in", "variable", "isLoggedIn"),
        Triple("DEFAULT-TIMEOUT", "constant", "DEFAULT_TIMEOUT")
    )

    for ((old, kind, new) in renames) {
        println("$old ($kind) -> $new")
    }
}

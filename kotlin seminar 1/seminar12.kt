package com.example.coroutineslab
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
fun main() = runBlocking {
    task1()
    task2()
    task3()
    task4()
    task5()
}
suspend fun task1() = coroutineScope {
    launch {
        delay(200)
        println("A done")
    }
    launch {
        delay(100)
        println("B done")
    }
    println("Start")
}
suspend fun task2() {
    suspend fun fetchName(attempt: Int): String? {
        delay(100)
        return if (attempt <= 2) null else "Ada"
    }
suspend fun showUser(maxTries: Int) {
    for (k in 1..maxTries) {
        val name = fetchName(k)
        if (name != null) {
            println("User: $name")
            return
        } else {
            println("try $k failed")
        }
    }
    println("Server is down, show cached user")
}

showUser(3)
showUser(2)}
suspend fun task3() = coroutineScope {
    val job = launch {
        try {
            for (i in 0..4) {
                println("tick $i")
                delay(100)
            }
        } finally {
            println("cleanup")
        }
    }
    delay(250)
    job.cancelAndJoin()
    println("isCancelled=${job.isCancelled}")
}
suspend fun task4() = coroutineScope {
    suspend fun loadName(): String {
        delay(100)
        return "Ada"
    }
suspend fun loadAge(): Int {
    delay(100)
    return 30
}

val seqTime = measureTimeMillis {
    loadName()
    loadAge()
}
println("Sequential: ${seqTime}ms")

val parTime = measureTimeMillis {
    val name = async { loadName() }
    val age = async { loadAge() }
    println("${name.await()} (${age.await()})")
}
println("Concurrent async/await: ${parTime}ms")}
suspend fun task5() = coroutineScope {
    launch(Dispatchers.Default) {
        println("Default: ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        println("IO: ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined: ${Thread.currentThread().name}")
    }
    val single = newSingleThreadContext("MyThread")
    try {
        launch(single) {
            println("Single before delay: ${Thread.currentThread().name}")
            delay(50)
            println("Single after delay: ${Thread.currentThread().name}")
        }.join()
    } finally {
        single.close()
    }
    launch {
        println("Inherited: ${Thread.currentThread().name}")
    }
}
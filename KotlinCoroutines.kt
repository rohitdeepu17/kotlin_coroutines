package com.example.testkotlincoroutines

import kotlin.concurrent.thread

fun main(){
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    thread{
        println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
    }

    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

/*
OUTPUT:
    main program starts at 0: main
    Main program ends at 8: main
    Fake work starts at 8: Thread-0
    Fake work finished at 1021: Thread-0
 */
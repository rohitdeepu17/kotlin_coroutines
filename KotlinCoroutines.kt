package com.example.testkotlincoroutines

import android.provider.Settings.Global
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun main(){
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    GlobalScope.launch{
        println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
        Thread.sleep(1000)              //Blocks the thread on which it is running. Then why we should use coroutine?
        println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
    }

    Thread.sleep(2000)
    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

/*
OUTPUT:
    main program starts at 0: main
    Fake work starts at 85: DefaultDispatcher-worker-1
    Fake work finished at 1096: DefaultDispatcher-worker-1
    Main program ends at 2092: main
 */
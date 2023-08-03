package com.example.testkotlincoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {//Main Thread
        val startTime = System.currentTimeMillis()
        println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

        launch{     //Thread main
            println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")  //Thread main
            mySuspendFun(1000)     //does not block the thread on which it is running
            println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")     //Either main thread or some other thread
        }

        mySuspendFun(2000)

        println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

suspend fun mySuspendFun(timeToDelay: Long){
    delay(timeToDelay)
}

/*
OUTPUT:
main program starts at 0: main
Fake work starts at 12: main
Fake work finished at 1022: main
Main program ends at 2015: main
 */
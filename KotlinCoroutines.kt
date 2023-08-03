package com.example.testkotlincoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
        val startTime = System.currentTimeMillis()
        println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

        GlobalScope.launch{     //Thread T1
            println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")  //Thread T1
            mySuspendFun(1000)     //does not block the thread on which it is running
            println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")     //Either T1 or some other thread
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
Fake work starts at 10: DefaultDispatcher-worker-1
Fake work finished at 1017: DefaultDispatcher-worker-1
Main program ends at 2024: main
 */
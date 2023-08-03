package com.example.testkotlincoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {//Main Thread
        val startTime = System.currentTimeMillis()
        println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

        val job: Job = launch{     //Thread main
            println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")  //Thread main
            mySuspendFun(1000)     //does not block the thread on which it is running
            println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")     //Either main thread or some other thread
        }

        job.join()          //wait for the coroutine to finish it's job

        println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

suspend fun mySuspendFun(timeToDelay: Long){
    delay(timeToDelay)
}

/*
OUTPUT:
main program starts at 0: main
Fake work starts at 11: main
Fake work finished at 1027: main
Main program ends at 1030: main
 */
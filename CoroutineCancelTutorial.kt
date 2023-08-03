package com.example.testkotlincoroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

fun main() = runBlocking {//Main Thread
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    val job: Job = launch(Dispatchers.Default){
        try{
            for(i in 1..1000){
                print("$i ")
                delay(5)
            }
        }catch (ex: CancellationException){
            println("Exception caught safely : ${ex.message}")
        }finally {
            withContext(NonCancellable){//a new coroutine is created as the older one is cancelled.
                delay(10)
                println("closing resources")
            }
        }

    }

    delay(15)
    job.cancel(CancellationException("my crash message"))
    job.join()
    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

/*
OUTPUT:
main program starts at 0: main
1 2 3 4 Exception caught safely : my crash message
closing resources
Main program ends at 80: main
 */
package com.example.testkotlincoroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {//Main Thread
        val startTime = System.currentTimeMillis()
        println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

        val jobDeferred: Deferred<Int> = async{     //Thread main
            println("Fake work starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")  //Thread main
            val suspendFunReturnVal = mySuspendFun(1000)     //does not block the thread on which it is running
            println("The value returned by mySuspendFun is $suspendFunReturnVal at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
            println("Fake work finished at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")     //Either main thread or some other thread
            15
        }

        val num:Int = jobDeferred.await()          //wait for the coroutine to finish it's job and return result
        println("The value returned from coroutine is $num at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

        println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

suspend fun mySuspendFun(timeToDelay: Long): String{
    delay(timeToDelay)
    return "some value from mySuspendFun"
}

/*
OUTPUT:
main program starts at 0: main
Fake work starts at 10: main
The value returned by mySuspendFun is some value from mySuspendFun at 1025: main
Fake work finished at 1027: main
The value returned from coroutine is 15 at 1030: main
Main program ends at 1030: main
 */
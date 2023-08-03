package com.example.testkotlincoroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.yield

fun main() = runBlocking {//Main Thread
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    val msgOne : Deferred<String> = async(start = CoroutineStart.LAZY){getMessageOneNew(startTime)}      //even we can use launch coroutine. But here we want to to return some value which launch cannot return
    val msgTwo : Deferred<String> = async(start = CoroutineStart.LAZY){getMessageTwoNew(startTime)}

    delay(2000)
    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

suspend fun getMessageOneNew(startTime: Long): String{
    delay(1000L)
    println("message One done at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
    return "Hello "
}

suspend fun getMessageTwoNew(startTime: Long): String{
    delay(1000)
    println("message Two done at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
    return "World!"
}

/*
OUTPUT:
main program starts at 0: main
Main program ends at 2022: main
 */
package com.example.testkotlincoroutines

import kotlinx.coroutines.CancellationException
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

    val result: String? = withTimeoutOrNull(2000){
            for (i in 0..1000) {
                print("$i.")
                delay(500)
            }
        "Done"
    }

    println("Result : $result")

    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}

/*
OUTPUT:
main program starts at 0: main
0.1.2.3.Exception in thread "main" kotlinx.coroutines.TimeoutCancellationException: Timed out waiting for 2000 ms
	at kotlinx.coroutines.TimeoutKt.TimeoutCancellationException(Timeout.kt:184)
	at kotlinx.coroutines.TimeoutCoroutine.run(Timeout.kt:154)
	at kotlinx.coroutines.EventLoopImplBase$DelayedRunnableTask.run(EventLoop.common.kt:502)
	at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:279)
	at kotlinx.coroutines.DefaultExecutor.run(DefaultExecutor.kt:108)
	at java.base/java.lang.Thread.run(Thread.java:833)

 */
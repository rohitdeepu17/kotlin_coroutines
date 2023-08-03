package com.example.testkotlincoroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {//Main Thread
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    println("runblocking context : $coroutineContext : ${Thread.currentThread().name}")

    //Without params : CONFINED
    launch{
        println("C1 : $coroutineContext : ${Thread.currentThread().name}")
        delay(1000)
        println("C1 after delay : $coroutineContext : ${Thread.currentThread().name}")

    }

    launch(Dispatchers.Default){        //similar to GlobalScope.launch
        println("C2 : $coroutineContext : ${Thread.currentThread().name}")
        delay(1000)
        println("C2 after delay : $coroutineContext : ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("C3 : $coroutineContext : ${Thread.currentThread().name}")
        delay(1000)
        println("C3 after delay : $coroutineContext : ${Thread.currentThread().name}")
    }

    launch(coroutineContext) {//Similar to confined coroutine
        println("C4 : $coroutineContext : ${Thread.currentThread().name}")
        delay(1000)
        println("C4 after delay : $coroutineContext : ${Thread.currentThread().name}")
    }

    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}


/*
OUTPUT:
main program starts at 0: main
runblocking context : [BlockingCoroutine{Active}@6c3708b3, BlockingEventLoop@6f1fba17] : main
C3 : [StandaloneCoroutine{Active}@7f13d6e, Dispatchers.Unconfined] : main
C2 : [StandaloneCoroutine{Active}@13ba5a75, Dispatchers.Default] : DefaultDispatcher-worker-1
Main program ends at 25: main
C1 : [StandaloneCoroutine{Active}@305fd85d, BlockingEventLoop@6f1fba17] : main
C4 : [StandaloneCoroutine{Active}@458c1321, BlockingEventLoop@6f1fba17] : main
C1 after delay : [StandaloneCoroutine{Active}@305fd85d, BlockingEventLoop@6f1fba17] : main
C3 after delay : [StandaloneCoroutine{Active}@7f13d6e, Dispatchers.Unconfined] : kotlinx.coroutines.DefaultExecutor
C4 after delay : [StandaloneCoroutine{Active}@458c1321, BlockingEventLoop@6f1fba17] : main
C2 after delay : [StandaloneCoroutine{Active}@13ba5a75, Dispatchers.Default] : DefaultDispatcher-worker-1
 */
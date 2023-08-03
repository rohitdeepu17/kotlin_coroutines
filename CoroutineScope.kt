package com.example.testkotlincoroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {//Main Thread
    val startTime = System.currentTimeMillis()
    println("main program starts at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")

    println("runblocking scope : $this")

    launch{
        println("launch scope : $this")

        launch{
            println("child launch scope : $this")
        }
    }

    async {
        println("async scope : $this")
    }

    println("Main program ends at ${System.currentTimeMillis()-startTime}: ${Thread.currentThread().name}")
}


/*
OUTPUT:
main program starts at 0: main
runblocking scope : BlockingCoroutine{Active}@520a3426
Main program ends at 9: main
launch scope : StandaloneCoroutine{Active}@6321e813
async scope : DeferredCoroutine{Active}@22a67b4
child launch scope : StandaloneCoroutine{Active}@3b084709
 */
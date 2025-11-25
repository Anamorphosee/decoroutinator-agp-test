package com.example.decoroutinatortest

import kotlinx.coroutines.delay

// Simple class with a suspend function
class MyClass {
    suspend fun doSomething(): String {
        delay(100)
        return "Hello from MyClass"
    }
    
    fun doSomethingSync(): String {
        return "Sync method"
    }
}

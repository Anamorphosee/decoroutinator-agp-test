package com.example.decoroutinatortest

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for MyClass.
 * 
 * Note: These fail with ClassNotFoundException when using Decoroutinator 2.5.8 + AGP 8.9.1
 */
class MyClassTest {
    
    @Test
    fun testDoSomethingSync() {
        val myClass = MyClass()
        val result = myClass.doSomethingSync()
        assertEquals("Sync method", result)
    }
    
    @Test
    fun testDoSomething() = runTest {
        val myClass = MyClass()
        val result = myClass.doSomething()
        assertEquals("Hello from MyClass", result)
    }
}


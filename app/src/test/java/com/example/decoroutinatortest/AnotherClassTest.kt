package com.example.decoroutinatortest

import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for AnotherClass (no suspend functions).
 * 
 * Interestingly, this also fails with ClassNotFoundException even though
 * AnotherClass isn't transformed by Decoroutinator. So it's not just about
 * the transformed classes - something about the classpath setup is off.
 */
class AnotherClassTest {
    
    @Test
    fun testGetValue() {
        val anotherClass = AnotherClass()
        val result = anotherClass.getValue()
        assertEquals(42, result)
    }
}


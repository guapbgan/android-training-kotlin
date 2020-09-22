package com.example.demolib.text

import com.example.demolib.LibraryMain
import org.junit.Assert.assertEquals
import org.junit.Test

class LibraryTest {
    @Test
    fun addTest(){
        assertEquals(4, LibraryMain.calculate(2, 2))
    }
}
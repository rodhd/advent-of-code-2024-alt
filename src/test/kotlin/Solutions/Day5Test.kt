package Solutions

import org.example.Solutions.Day5
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day5Test {
    val testDay5: Day5 = Day5()

    @Test
    fun testCalculateFirst() {
        val expected = 143
        val input = testDay5.readInputAsListOfLines()
        assertEquals(expected, testDay5.CalculateFirst(input))
    }

    fun testCalculateSecond() {
        val expected = 123
        val input = testDay5.readInputAsListOfLines()
        assertEquals(expected, testDay5.CalculateSecond(input))
    }
}
package Solutions

import org.example.Solutions.Day1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day1Test {
    private val testDay1: Day1 = Day1()

    @Test
    fun testCalculateFirst() {
        val expected = 11
        val input = testDay1.readInputAsListOfLines()
        assertEquals(expected, testDay1.CalculateFirst(input))
    }

    @Test
    fun testCalculateSecond() {
        val expected = 31
        val input = testDay1.readInputAsListOfLines()
        assertEquals(expected, testDay1.CalculateSecond(input))
    }
}
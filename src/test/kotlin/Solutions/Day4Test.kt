package Solutions

import org.example.Solutions.Day4
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day4Test {
    val day4Test: Day4 = Day4()

    @Test
    fun testCalculateFirst() {
        val expected = 18
        val input = day4Test.readInputAsListOfLines()
        assertEquals(expected, day4Test.CalculateFirst(input))
    }

    @Test
    fun testCalculateSecond() {
        val expected = 9
        val input = day4Test.readInputAsListOfLines()
        assertEquals(expected, day4Test.CalculateSecond(input))
    }
}
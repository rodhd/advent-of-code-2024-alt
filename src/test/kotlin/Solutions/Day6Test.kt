package Solutions

import org.example.Solutions.Day6
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day6Test {

    val day6Test: Day6 = Day6()

    @Test
    fun testCalculateFirst() {
        val expected = 41
        val input = day6Test.readInputAsListOfLines()
        assertEquals(expected, day6Test.CalculateFirst(input))
    }

    @Test
    fun testCalculateSecond() {
        val expected = 6
        val input = day6Test.readInputAsListOfLines()
        assertEquals(expected, day6Test.CalculateSecond(input))
    }
}
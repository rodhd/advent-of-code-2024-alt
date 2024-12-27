package Solutions

import org.example.Solutions.Day8
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day8Test {
    val day8Test: Day8 = Day8()

    @Test
    fun calculateFirst() {
        val expected = 14
        val input = day8Test.readInputAsListOfLines()
        assertEquals(expected, day8Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = 34
        val input = day8Test.readInputAsListOfLines()
        assertEquals(expected, day8Test.CalculateSecond(input))
    }
}
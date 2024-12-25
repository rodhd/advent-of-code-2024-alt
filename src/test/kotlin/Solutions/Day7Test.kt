package Solutions

import org.example.Solutions.Day6
import org.example.Solutions.Day7
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day7Test {
    val day7Test: Day7 = Day7()

    @Test
    fun calculateFirst() {
        val expected = "3749".toBigInteger()
        val input = day7Test.readInputAsListOfLines()
        assertEquals(expected, day7Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = "11387".toBigInteger()
        val input = day7Test.readInputAsListOfLines()
        assertEquals(expected, day7Test.CalculateSecond(input))
    }
}
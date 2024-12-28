package Solutions

import org.example.Solutions.Day10
import org.example.Solutions.Day9
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day10Test {

    val day10Test: Day10 = Day10()

    @Test
    fun calculateFirst() {
        val expected = 36
        val input = day10Test.readInputAsListOfLines()
        assertEquals(expected, day10Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = 82
        val input = day10Test.readInputAsListOfLines()
        //assertEquals(expected, day10Test.CalculateSecond(input))
    }
}
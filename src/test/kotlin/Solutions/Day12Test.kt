package Solutions

import org.example.Solutions.Day10
import org.example.Solutions.Day12
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day12Test {

    val day12Test: Day12 = Day12()

    @Test
    fun calculateFirst() {
        val expected = 1930
        val input = day12Test.readInputAsListOfLines()
        assertEquals(expected, day12Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = 1206
        val input = day12Test.readInputAsListOfLines()
        assertEquals(expected, day12Test.CalculateSecond(input))
    }
}
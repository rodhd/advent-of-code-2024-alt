package Solutions

import org.example.Solutions.Day9
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day9Test {

    val day9Test: Day9 = Day9()

    @Test
    fun calculateFirst() {
        val expected = "1928".toBigInteger()
        val input = day9Test.readInputAsString()
        assertEquals(expected, day9Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = "2858".toBigInteger()
        val input = day9Test.readInputAsString()
        assertEquals(expected, day9Test.CalculateSecond(input))
    }
}
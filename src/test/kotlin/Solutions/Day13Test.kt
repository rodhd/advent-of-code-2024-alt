package Solutions

import org.example.Solutions.Day13
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day13Test {

    val day13Test: Day13 = Day13()

    @Test
    fun calculateFirst() {
        val expected = 480
        val input = day13Test.readInputAsString()
        assertEquals(expected, day13Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = "100".toBigInteger()
        val input = day13Test.readInputAsString()
        assertEquals(expected, day13Test.CalculateSecond(input))
    }
}
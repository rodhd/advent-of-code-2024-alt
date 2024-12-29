package Solutions

import org.example.Solutions.Day11
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day11Test {

    val day11Test: Day11 = Day11()

    @Test
    fun calculateFirst() {
        val expected = "55312".toBigInteger()
        val input = day11Test.readInputAsString()
        assertEquals(expected, day11Test.CalculateFirst(input))
    }

    @Test
    fun calculateSecond() {
        val expected = 82
        val input = day11Test.readInputAsString()
        //assertEquals(expected, day11Test.CalculateSecond(input))
    }
}
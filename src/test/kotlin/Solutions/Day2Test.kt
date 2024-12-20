package Solutions

import org.example.Solutions.Day2
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
 class Day2Test {

  private val testDay2: Day2 = Day2()

  @Test
  fun testCalculateFirst() {
   val expected = 2
   val input = testDay2.readInputAsListOfLines()
   assertEquals(expected, testDay2.CalculateFirst(input))
  }

  @Test
  fun testCalculateSecond() {
   val expected = 4
   val input = testDay2.readInputAsListOfLines()
   assertEquals(expected, testDay2.CalculateSecond(input))
  }
}
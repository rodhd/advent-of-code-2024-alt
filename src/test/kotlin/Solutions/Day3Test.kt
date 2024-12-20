package Solutions

import org.example.Solutions.Day3
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

 class Day3Test {
  val day3Test: Day3 = Day3()
  @Test
  fun testCalculateFirst() {
   val expected = 161
   val input = day3Test.readInputAsString()
   assertEquals(expected, day3Test.CalculateFirst(input))
  }

  @Test
  fun testCalculateSecond() {
   val expected = 48
   val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
   assertEquals(expected, day3Test.CalculateSecond(input))
  }
 }
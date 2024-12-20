package org.example.Solutions

import org.example.Common.AoCSolution
import kotlin.math.abs

@Suppress("unused")
class Day1 : AoCSolution() {
    override val day = "1"
    override fun FirstSolution() {
        val input = readInputAsListOfLines()
        val result = CalculateFirst(input)
        print(result)
    }

    override fun SecondSolution() {
        val input = readInputAsListOfLines()
        val result = CalculateSecond(input)
        print(result)
    }

    fun CalculateFirst(input: List<String>): Int {
        val inputSeparated = input
            .map({ x -> x.split("   ") })

        val leftArray = inputSeparated.map({ x -> x[0].toInt() }).sorted()
        val rightArray = inputSeparated.map({ x -> x[1].toInt() }).sorted()

        val result = leftArray
            .zip(rightArray).sumOf({ x -> abs(x.first - x.second) })
        return result
    }

    fun CalculateSecond(input: List<String>): Int {
        val inputSeparated = input
            .map({ x -> x.split("   ") })


        val rightArray = inputSeparated.map({ x -> x[1].toInt() }).groupingBy { it }.eachCount()

        val result = inputSeparated.map({ x -> x[0].toInt() }).map({ y -> y * (rightArray[y] ?: 0) }).sum()
        return result
    }
}
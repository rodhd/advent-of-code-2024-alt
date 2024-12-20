package org.example.Solutions

import org.example.Common.AoCSolution

class Day3 : AoCSolution() {
    override val day: String
        get() = "3"

    override fun FirstSolution() {
        val input = readInputAsString()
        val result = CalculateFirst(input)
        print(result)
    }

    override fun SecondSolution() {
        val input = readInputAsString()
        val result = CalculateSecond(input)
        print(result)
    }

    fun CalculateFirst(input: String): Int {
        val multiplicationPattern = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
        return multiplicationPattern
            .findAll(input)
            .map{x -> _multiply(x.value)}
            .sum()
    }

    private fun _multiply(op: String): Int {
        val operationPattern = """mul\((?<first>\d{1,3}),(?<second>\d{1,3})\)""".toRegex()
        val matchResult = operationPattern.find(op)
        if (matchResult != null) {
            return (matchResult.groups["first"]?.value?.toInt() ?: 0) * (matchResult.groups["second"]?.value?.toInt() ?: 0)
        }
        return -1
    }

    fun CalculateSecond(input: String): Int {
        var (bufferString, rest) = input.split("don't()", limit = 2)
        while (rest.contains("don't()")) {
            val temp = rest.split("do()", limit = 2).last().split("don't()", limit = 2)
            bufferString += temp.first()
            rest = if(temp.size == 1) {
                ""
            } else {
                temp.last()
            }

        }
        return CalculateFirst(bufferString)
    }
}
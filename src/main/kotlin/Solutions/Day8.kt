package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.pow

class Day8 : AoCSolution() {
    override val day: String
        get() = "8"

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
        val antennas = _getAntennaLocations(input)
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        val antennaTypes = antennas.values.groupingBy { it }.eachCount().filter { c -> c.value > 1 }
        val antinodePositions = emptySet<String>().toMutableSet()
        for (t in antennaTypes.keys) {
            val relevantAntennas = antennas
                .filter { x -> x.value == t }
                .map { y -> y.key }
                .toList()

            for ((i, v) in relevantAntennas.withIndex()) {
                for ((j, w) in relevantAntennas.withIndex()) {
                    if (j != i) {
                        val p = _getAntinode(v, w, maxX, maxY)
                        if (p != null) {
                            antinodePositions.add(p)
                        }
                    }
                }
            }

        }
        return antinodePositions.size
    }

    fun CalculateSecond(input: List<String>): Int {
        val antennas = _getAntennaLocations(input)
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        val antennaTypes = antennas.values.groupingBy { it }.eachCount().filter { c -> c.value > 1 }
        val antinodePositions = emptySet<String>().toMutableSet()
        for (t in antennaTypes.keys) {
            val relevantAntennas = antennas
                .filter { x -> x.value == t }
                .map { y -> y.key }
                .toList()

            for ((i, v) in relevantAntennas.withIndex()) {
                for ((j, w) in relevantAntennas.withIndex()) {
                    if (j != i) {
                        val p = _getAntinodesAdvanced(v, w, maxX, maxY)
                        if (p.size > 0) {
                            antinodePositions.addAll(p)
                        }
                    }
                }
            }

        }
        return antinodePositions.size
    }

    private fun _getAntennaLocations(input: List<String>): Map<String, String> {
        val result = emptyMap<String, String>().toMutableMap()
        for ((i, r) in input.withIndex()) {
            for ((j, c) in r.withIndex()) {
                if ("""(\d|\w)""".toRegex().matches(c.toString())) {
                    result["${i},${j}"] = c.toString()
                }
            }
        }
        return result
    }

    private fun _getAntinode(locA: String, locB: String, maxX: Int, maxY: Int): String? {
        val pA = Point(locA.split(",").first().toInt(), locA.split(",").last().toInt())
        val pB = Point(locB.split(",").first().toInt(), locB.split(",").last().toInt())

        val distX = pA.x - pB.x
        val distY = pA.y - pB.y

        val antinode = Point(pA.x + distX, pA.y + distY)

        if (antinode.x in 0..maxX && antinode.y in 0..maxY) {
            return "${antinode.x},${antinode.y}"
        }
        return null
    }

    private fun _getAntinodesAdvanced(locA: String, locB: String, maxX: Int, maxY: Int): Set<String> {
        val pA = Point(locA.split(",").first().toInt(), locA.split(",").last().toInt())
        val pB = Point(locB.split(",").first().toInt(), locB.split(",").last().toInt())

        val distX = pA.x - pB.x
        val distY = pA.y - pB.y

        var antinode = Point(pA.x, pA.y)
        val result = mutableSetOf("${antinode.x},${antinode.y}")

        do {
            result.add("${antinode.x},${antinode.y}")
            antinode = Point(antinode.x + distX, antinode.y + distY)
        } while(antinode.x in 0..maxX && antinode.y in 0..maxY)
        return result
    }
}
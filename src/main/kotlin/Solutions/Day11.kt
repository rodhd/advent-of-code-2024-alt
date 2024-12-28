package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point
import java.math.BigInteger

class Day11 : AoCSolution() {
    override val day: String
        get() = "11"

    data class Stones(var order: List<String>)
    data class StonesAlt(var order: Map<String, BigInteger>)

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

    fun CalculateFirst(input: String): BigInteger {
        //split input and parse to int
        val stones = StonesAlt(input.split(" ").map { x -> Pair(x, "1".toBigInteger()) }.toMap())
        //iterate 25 times
        repeat(25) {
            _blinkAlt(stones)
        }
        return stones.order.values.sumOf{v -> v}
    }

    fun CalculateSecond(input: String): BigInteger {
        val stones = StonesAlt(input.split(" ").map { x -> Pair(x, "1".toBigInteger()) }.toMap())
        //iterate 25 times
        repeat(75) {
            _blinkAlt(stones)
        }
        return stones.order.values.sumOf{v -> v}
    }

    private fun _applyRules(engraving: String): List<String> {
        // rule 1 - replace 0 with 1
        val engravingInt = engraving.toBigInteger()
        if (engraving == "0") {
            return listOf("1")
        }
        //rule 2 - if even number
        if (engraving.length % 2 == 0) {
            return listOf(
                engraving.substring(0..<engraving.length / 2).toBigInteger().toString(),
                engraving.substring(engraving.length / 2..engraving.lastIndex).toBigInteger().toString()
            )
        }
        //
        return listOf((engravingInt * "2024".toBigInteger()).toString())
    }

    private fun _blink(stones: Stones) {
        stones.order = stones.order.map { s -> _applyRules(s) }.flatten()
    }

    private fun _blinkAlt(stones: StonesAlt) {
        val temp = emptyMap<String, BigInteger>().toMutableMap()
        for (s in stones.order) {
            val new = _applyRules(s.key)
            for (n in new) {
                if (!temp.containsKey(n)) {
                    temp[n] = "0".toBigInteger()
                }
                temp[n] = temp[n]!! + stones.order[s.key]!!
            }
        }
        stones.order = temp.toMap()
    }
}
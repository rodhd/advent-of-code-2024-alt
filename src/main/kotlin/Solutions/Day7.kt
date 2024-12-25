package org.example.Solutions

import org.example.Common.AoCSolution
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.pow

class Day7 : AoCSolution() {
    override val day: String
        get() = "7"

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

    fun CalculateFirst(input: List<String>): BigInteger {
        return input.sumOf{ x -> _evalutateTest(x)}
    }

    fun CalculateSecond(input: List<String>): BigInteger {
        return input.sumOf{ x -> _evaluateTestV2(x)}
    }

    private fun _evalutateTest(test: String): BigInteger {
        val result = test.split(": ").first().toBigInteger()
        val parameters = test.split(": ").last().split(" ").map { x -> x.toBigInteger() }
        val operators = _generateOperationCombinations(parameters.size)

        for(op in operators) {
            var r = "0".toBigInteger()
            for (i in 0..parameters.lastIndex) {
                if(op[i] == '+') {
                    r += parameters[i]
                } else {
                    r *= parameters[i]
                }
            }
            if(r == result) {
                return r
            }
        }
        return "0".toBigInteger()

    }

    private fun _generateOperationCombinations(operators: Int): List<String> {
        val combinations = 2.0.pow(operators).toInt()
        val result = (0..combinations)
            .map { x -> x
                .toString(radix = 2)
                .padStart(operators, '0')
                .replace("0", "+")
                .replace("1", "*") }

        return result

    }

    private fun _evaluateTestV2(test: String): BigInteger {
        val operators = listOf("+", "*", "||")
        val result = test.split(": ").first().toBigInteger()
        val parameters = test.split(": ").last().split(" ").map { x -> x.toBigInteger() }

        var possibleEvals = mutableListOf(mutableListOf("+"), mutableListOf("*"), mutableListOf("||"))

        for (i in 0..<parameters.lastIndex) {
            val newEvals = emptyList<MutableList<String>>().toMutableList()
            for((j, v) in possibleEvals.withIndex()) {
                val temp = _performOperation(parameters, v, i)
                if(temp <= result) {
                    for(o in operators) {
                        val t2 = v.toMutableList()
                        t2.add(o)
                        newEvals.add(t2)
                    }
                }
            }
            possibleEvals = newEvals.toMutableList()
        }
        if(possibleEvals.size == 0){
            return "0".toBigInteger()
        }
        if(possibleEvals.any { x -> _performOperation(parameters, x, parameters.lastIndex) == result }) {
            return result
        }
        return "0".toBigInteger()
    }

    private fun _performOperation(parameters: List<BigInteger>, operations: List<String>, maxIndex: Int): BigInteger {
        var result = parameters[0]
        for(i in 1..maxIndex) {
            when(operations[i-1]) {
                "+" -> result += parameters[i]
                "*" -> result *= parameters[i]
                "||" ->  result = (result.toString()+parameters[i].toString()).toBigInteger()
            }
        }
        return result
    }



}
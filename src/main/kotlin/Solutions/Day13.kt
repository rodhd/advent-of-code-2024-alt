package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point
import java.math.BigInteger
import kotlin.math.abs

class Day13 : AoCSolution() {
    override val day: String
        get() = "13"

    data class Equation(val XA: Int, val YA: Int, val XB: Int, val YB: Int, val XPrize: Int, val YPrize: Int)

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
        val equations = input.split("\n\n").map { e -> parseEquation(e) }
        return equations.sumOf{ e -> solveEquation(e)}
    }

    fun CalculateSecond(input: String): Long {
        val equations = input.split("\n\n").map { e -> parseEquation(e) }
        return equations.sumOf{ e -> solveEquationAlt(e)}
    }

    fun calculateGCD(a: Int, b: Int): Int {
        var num1 = a
        var num2 = b
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    fun calculateLCM(a: Int, b: Int): Int {
        return a * b / calculateGCD(a, b)
    }

    fun parseEquation(equation: String): Equation {
        val pattern =
            """Button A: X(?<XA>[+-]?\d+), Y(?<YA>[+-]?\d+)\nButton B: X(?<XB>[+-]?\d+), Y(?<YB>[+-]?\d+)\nPrize: X=(?<XP>\d+), Y=(?<YP>\d+)""".toRegex()
        val r = pattern.find(equation)
        return Equation(
            XA = r?.groups?.get("XA")?.value?.toInt() ?: 0,
            YA = r?.groups?.get("YA")?.value?.toInt() ?: 0,
            XB = r?.groups?.get("XB")?.value?.toInt() ?: 0,
            YB = r?.groups?.get("YB")?.value?.toInt() ?: 0,
            XPrize = r?.groups?.get("XP")?.value?.toInt() ?: 0,
            YPrize = r?.groups?.get("YP")?.value?.toInt() ?: 0
        )
    }

    fun solveEquation(equation: Equation): Int {
        // get LCM for X
        val lcm = calculateLCM(abs(equation.XA), abs(equation.YA))
        // solve for B presses
        val p = equation.XPrize * lcm / equation.XA - equation.YPrize * lcm / equation.YA
        val b = equation.XB * lcm / equation.XA - equation.YB * lcm / equation.YA
        if(p % b != 0) {
            return 0
        }
        val B = p / b
        // solve for A presses
        val A = (equation.XPrize - equation.XB * B) / (equation.XA)
        return A * 3 + B
    }

    fun solveEquationAlt(equation: Equation): Long {
        val newXPrize = equation.XPrize + 10000000000000
        val newYPrize = equation.YPrize + 10000000000000
        // get LCM for X
        val lcm = calculateLCM(abs(equation.XA), abs(equation.YA))
        // solve for B presses
        val p = newXPrize * lcm / equation.XA - newYPrize * lcm / equation.YA
        val b = equation.XB * lcm / equation.XA - equation.YB * lcm / equation.YA
        if(p % b != 0L) {
            return 0
        }
        val B = p / b
        // solve for A presses
        if((newXPrize - equation.XB * B)%(equation.XA) != 0L) {
            return 0
        }
        val A = (newXPrize - equation.XB * B) / (equation.XA)
        return A * 3L + B
    }
}
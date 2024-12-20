package org.example.Solutions

import org.example.Common.AoCSolution
import kotlin.math.abs

class Day2 : AoCSolution() {
    override val day = "2"

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
        val reports = input.map({ x -> x.split(' ').map { y -> y.toInt() } })

        val result = reports.map({x -> checkReport(x)}).filter { x -> x }.count()

        return result
    }

    fun CalculateSecond(input: List<String>): Int {
        val reports = input.map({ x -> x.split(' ').map { y -> y.toInt() } })

        val valid_one = reports.filter{ x -> checkReport(x) }

        val valid_two = reports.filter{x -> !checkReport(x)}.filter{y -> checkReportV2(y)}

        return valid_one.count() + valid_two.count()
    }

    private fun checkReport(report: List<Int>): Boolean {
        val temp: List<Int> = List(report.size - 1, { report[it + 1] - report[it] })

        return temp.all({ x -> abs(x) in 1..3 }) && (temp.all({ x -> x > 0 }) || temp.all({ x -> x < 0 }))
    }

    private fun checkReportV2(report: List<Int>): Boolean {
        for ((i, v) in report.withIndex()) {
            if(checkReport(report.filterIndexed { index, _ -> index != i })) {
                return true
            }
        }
        return false
    }

}
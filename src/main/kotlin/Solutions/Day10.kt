package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point
import java.math.BigInteger

class Day10 : AoCSolution() {
    override val day: String
        get() = "10"

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
        val parsedInput = input.map{ r -> r.map{ c -> c.toString().toInt()}}
        val trailheads = _getTrailheadCoordinates(parsedInput)

        return trailheads.sumOf { t -> _pathReachesTop(parsedInput, t).count() }
    }

    fun CalculateSecond(input: List<String>): Int {
        val parsedInput = input.map{ r -> r.map{ c -> c.toString().toInt()}}
        val trailheads = _getTrailheadCoordinates(parsedInput)
        val paths = trailheads.map{t -> _pathReachesTopAlt(parsedInput, listOf(t))}
        return paths.sumOf{t -> t.filter{p -> p.size == 10}.count()}
    }

    private fun _getTrailheadCoordinates(input: List<List<Int>>): List<Point> {
        val result = emptyList<Point>().toMutableList()
        for((i,r) in input.withIndex()) {
            for((j,c) in r.withIndex()) {
                if(c == 0) {
                    result.add(Point(i,j))
                }
            }
        }
        return result
    }

    private fun _pathReachesTop(input: List<List<Int>>, position: Point): Set<Point> {
        val currentHeight = input[position.x][position.y]
        if(currentHeight < 9)  {
            val possibleSteps = _findPossibleNextSteps(input, position)
            if(possibleSteps.size == 0) {
                return emptySet<Point>()
            }
            return possibleSteps.map { p -> _pathReachesTop(input, p) }.flatten().toSet()
        } else {
            return setOf(position)
        }
    }
    private fun _pathReachesTopAlt(input: List<List<Int>>, path: List<Point>): Set<List<Point>> {
        val currentHeight = input[path.last().x][path.last().y]
        if(currentHeight < 9)  {
            val possibleSteps = _findPossibleNextSteps(input, path.last())
            if(possibleSteps.size == 0) {
                return setOf(path)
            }
            return possibleSteps.map { p -> _pathReachesTopAlt(input, path + p) }.flatten().toSet()
        } else {
            return setOf(path)
        }
    }

    private fun _findPossibleNextSteps(input: List<List<Int>>, position: Point): List<Point> {
        val currentHeight = input[position.x][position.y]
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        val result = emptyList<Point>().toMutableList()
        if(position.x-1 >= 0 && input[position.x-1][position.y] - currentHeight == 1) {
            result.add(Point(position.x-1, position.y))
        }
        if(position.x+1 <= maxX && input[position.x+1][position.y] - currentHeight == 1) {
            result.add(Point(position.x+1, position.y))
        }
        if(position.y-1 >= 0 && input[position.x][position.y-1] - currentHeight == 1) {
            result.add(Point(position.x, position.y-1))
        }
        if(position.y+1 <= maxY && input[position.x][position.y+1] - currentHeight == 1) {
            result.add(Point(position.x, position.y+1))
        }
        return result
    }
}
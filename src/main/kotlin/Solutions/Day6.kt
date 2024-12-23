package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point
import java.io.InvalidObjectException

class Day6 : AoCSolution() {
    override val day: String
        get() = "6"

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
        var copy = input.toList()
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        copy = _runSimulation(copy, maxX, maxY)
        return copy.sumOf { x -> x.count { y -> y == '-' || y == '|' } }
    }

    fun CalculateSecond(input: List<String>): Int {
        val copy = input.toList()
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        val result = _findLoops(copy, maxX, maxY)
        return result
    }

    private fun _getCurrentLoc(input: List<String>): Point {
        for (i in input.indices) {
            for (j in input[i].indices) {
                when (input[i][j]) {
                    '^' -> return Point(i, j)
                    '>' -> return Point(i, j)
                    'v' -> return Point(i, j)
                    '<' -> return Point(i, j)
                    else -> continue
                }
            }
        }
        return Point(9999, 9999)
    }

    private fun _getNextStep(input: List<String>, loc: Point): Point {
        val currentDir = input[loc.x][loc.y]
        return when (currentDir) {
            '^' -> Point(loc.x - 1, loc.y)
            '>' -> Point(loc.x, loc.y + 1)
            'v' -> Point(loc.x + 1, loc.y)
            '<' -> Point(loc.x, loc.y - 1)
            else -> loc
        }
    }

    private fun _isNextStepBlocked(input: List<String>, loc: Point, maxX: Int, maxY: Int): Boolean {
        if (!(loc.x in 0..maxX && loc.y in 0..maxY)) {
            return false
        }
        return input[loc.x][loc.y] == '#'
    }

    private fun _rotateGuard(input: List<String>, loc: Point): List<String> {
        val currentDir = input[loc.x][loc.y]
        val copy = input.toMutableList()
        when (currentDir) {
            '^' -> copy[loc.x] = updateCharacterInString(copy[loc.x], loc.y, '>')
            '>' -> copy[loc.x] = updateCharacterInString(copy[loc.x], loc.y, 'v')
            'v' -> copy[loc.x] = updateCharacterInString(copy[loc.x], loc.y, '<')
            '<' -> copy[loc.x] = updateCharacterInString(copy[loc.x], loc.y, '^')
        }
        return copy
    }

    private fun _updateMap(
        input: List<String>,
        currentStep: Point,
        nextStep: Point,
        maxX: Int,
        maxY: Int
    ): List<String> {
        val copy = input.toMutableList()
        val currentDir = copy[currentStep.x][currentStep.y]
        when (currentDir) {
            '^' -> copy[currentStep.x] = updateCharacterInString(copy[currentStep.x], currentStep.y, '|')
            '>' -> copy[currentStep.x] = updateCharacterInString(copy[currentStep.x], currentStep.y, '-')
            'v' -> copy[currentStep.x] = updateCharacterInString(copy[currentStep.x], currentStep.y, '|')
            '<' -> copy[currentStep.x] = updateCharacterInString(copy[currentStep.x], currentStep.y, '-')
        }
        if (nextStep.x in 0..maxX && nextStep.y in 0..maxY) {
            copy[nextStep.x] = updateCharacterInString(copy[nextStep.x], nextStep.y, currentDir)
        }

        return copy

    }

    private fun _runSimulation(input: List<String>, maxX: Int, maxY: Int): List<String> {
        var copy = input.toList()
        var guard = _getCurrentLoc(copy)
        do {
            val nextStep = _getNextStep(copy, guard)
            if (_isNextStepBlocked(input, nextStep, maxX, maxY)) {
                copy = _rotateGuard(copy, guard).toList()
            } else {
                copy = _updateMap(copy, guard, nextStep, maxX, maxY).toList()
                guard = _getCurrentLoc(copy)
            }
        } while (guard.x in 0..maxX && guard.y in 0..maxY)
        return copy
    }

    private fun _findLoops(input: List<String>, maxX: Int, maxY: Int): Int {
        var counter = 0
        val start = _getCurrentLoc(input)
        val copy = _runSimulation(input, maxX, maxY)
        val path = emptySet<Point>().toMutableSet()
        for (i in copy.indices) {
            for (j in copy[i].indices) {
                if (copy[i][j] == '|' || copy[i][j] == '-') {
                    path.add(Point(i, j))
                }
            }
        }
        path.remove(Point(start.x-1, start.y))
        path.remove(Point(start.x, start.y))
        for (p in path) {
            val newMap = input.toMutableList()
            newMap[p.x] = updateCharacterInString(newMap[p.x], p.y, '#')
            if(_runLoopSimulation(newMap, maxX, maxY)) {
                counter++
            }
            if(counter % 100 == 0) {
                print("counter:${counter}\n")
            }
        }
        return counter
    }

    private fun _runLoopSimulation(input: List<String>, maxX: Int, maxY: Int): Boolean {
        var iter = 0
        var copy = input.toList()
        var guard = _getCurrentLoc(copy)
        while (iter < 130 * 130) {
            val nextStep = _getNextStep(copy, guard)
            if (_isNextStepBlocked(input, nextStep, maxX, maxY)) {
                copy = _rotateGuard(copy, guard).toList()
            } else {
                copy = _updateMap(copy, guard, nextStep, maxX, maxY).toList()
                guard = _getCurrentLoc(copy)
                if(!(guard.x in 0..maxX && guard.y in 0..maxY)) {
                    return false
                }
            }
            iter++
        }
        return true
    }


    fun updateCharacterInString(original: String, index: Int, newChar: Char): String {
        // Ensure the index is valid
        if (index < 0 || index >= original.length) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for length ${original.length}")
        }

        // Convert to a char array, update the character, and create a new string
        val charArray = original.toCharArray()
        charArray[index] = newChar

        // Return the new modified string
        return String(charArray)
    }
}
package org.example.Solutions

import org.example.Common.AoCSolution

class Day6 : AoCSolution() {
    override val day: String
        get() = "6"

    enum class Direction {
        NORTH {
            override fun value() = Pair(-1,0)
            override fun next() = EAST
        },
        SOUTH {
            override fun value() = Pair(1,0)
            override fun next() = WEST
        },
        WEST {
            override fun value() = Pair(0,-1)
            override fun next() = NORTH
        },
        EAST {
            override fun value() = Pair(0,1)
            override fun next() = SOUTH
        };
        abstract fun value(): Pair<Int, Int>
        abstract fun next(): Direction
    }

    class PosDir constructor(row: Int, col: Int, dir: Direction) {
        val row = row
        val col = col
        val dir = dir

        fun getPosition(): Pair<Int, Int> = Pair(row, col)

        fun rotate() = PosDir(row, col, dir.next())

        fun vector() = "r${row}c${col}d${dir.name}"

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PosDir) return false

            // Compares properties for structural equality
            return this.row == other.row && this.col == other.col && this.dir == other.dir
        }
    }

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
        val obstacles = _getCoordinates(input, '#').map{x -> "r${x.first}c${x.second}"}.toSet()
        val guard = _getCoordinates(input, '^').map{x -> PosDir(x.first, x.second, Direction.NORTH)}
        val guardPath = _runSimulation(input, obstacles, guard)
        return guardPath.groupBy { it.getPosition() }.size - 1
    }

    fun CalculateSecond(input: List<String>): Int {
        val obstacles = _getCoordinates(input, '#').map{x -> "r${x.first}c${x.second}"}.toSet()
        val guard = _getCoordinates(input, '^').map{x -> PosDir(x.first, x.second, Direction.NORTH)}
        val guardPath = _runSimulation(input, obstacles, guard).toMutableList()
        var temp = guardPath.removeLast()
        val encounters = emptySet<String>().toMutableSet()
        while(guardPath.size > 1) {
            if(encounters.size % 10 == 0) {
                print("${guardPath.size}\n")
            }
            if(_isNewObstacleALoop(input, obstacles, guardPath, temp.getPosition())) {
                encounters.add("r${temp.row}c${temp.col}")
            }
            temp = guardPath.removeLast()
        }
        return encounters.size
    }

    private fun _getCoordinates(input: List<String>, target: Char): List<Pair<Int, Int>> {
        var result = mutableListOf<Pair<Int, Int>>()
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == target) {
                    result.add(Pair(i, j))
                }
            }
        }
        return result
    }

    private fun _runSimulation(
        input: List<String>,
        obstacles: Set<String>,
        guard: List<PosDir>
    ): List<PosDir> {
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        var nextPosition: PosDir

        val guardPath = guard.toMutableList()
        do {
            nextPosition = _calculateNextPosition(guardPath.last())
            while (_isNextPositionAnObstacle(nextPosition, obstacles)) {
                val temp = guardPath.last().rotate()
                guardPath.dropLast(1)
                guardPath.add(temp)
                nextPosition = _calculateNextPosition(guardPath.last())
            }
            guardPath.add(nextPosition)
        } while ((guardPath.last().row in 0..maxX && guardPath.last().col in 0..maxY))
        return guardPath
    }

    private fun _isNewObstacleALoop(
        input: List<String>,
        obstacles: Set<String>,
        guard: List<PosDir>,
        newObstacle: Pair<Int, Int>
    ): Boolean {
        val maxX = input.lastIndex
        val maxY = input.first().lastIndex
        val newObstacles = obstacles.toMutableSet()
        newObstacles.add("r${newObstacle.first}c${newObstacle.second}")
        var nextPosition: PosDir
        val guardPath = guard.toMutableList()
        do {
            nextPosition = _calculateNextPosition(guardPath.last())
            while (_isNextPositionAnObstacle(nextPosition, newObstacles)) {
                val temp = guardPath.last().rotate()
                guardPath.removeLast()
                guardPath.add(temp)
                nextPosition = _calculateNextPosition(guardPath.last())
            }
            if (guardPath.map{x -> x.vector()}.toSet().contains(nextPosition.vector()) || guardPath.size > 130*130) {
                return true
            }
            guardPath.add(nextPosition)
        } while ((guardPath.last().row in 0..maxX && guardPath.last().col in 0..maxY))
        return false
    }

    private fun _calculateNextPosition(posDir: PosDir): PosDir {
        return PosDir(posDir.row + posDir.dir.value().first, posDir.col + posDir.dir.value().second, posDir.dir)
    }

    private fun _isNextPositionAnObstacle(position: PosDir, obstacles: Set<String>): Boolean {
        return obstacles.contains("r${position.row}c${position.col}")
    }
}
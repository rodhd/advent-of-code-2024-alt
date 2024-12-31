package org.example.Common

import java.awt.Point

abstract class AoCSolution (){

    abstract val day: String
    abstract fun FirstSolution()

    abstract fun SecondSolution()

    fun readInputAsString(): String {
        val fileName = "/inputs/input-$day.txt"
        return this.javaClass.getResource(fileName)?.readText(Charsets.UTF_8) ?: "not found"
    }

    fun readInputAsListOfLines(): List<String>
            = this.javaClass.getResourceAsStream("/inputs/input-$day.txt")?.bufferedReader()?.readLines() ?: listOf("not used")

    val UP = Point(-1,0)
    val DOWN = Point(1,0)
    val RIGHT = Point(0,1)
    val LEFT = Point(0, -1)
    val DIRECTIONS = listOf(UP, DOWN, RIGHT, LEFT)

    fun Point.add(point: Point): Point {
        return Point(this.x + point.x, this.y + point.y)
    }

    fun isValidPositionString(input: List<String>, position: Point): Boolean {
        return position.x in 0..input.lastIndex && position.y in 0..input.first().lastIndex
    }

    fun isValidPosition(input: List<List<Any>>, position: Point): Boolean {
        return position.x in 0..input.lastIndex && position.y in 0..input.first().lastIndex
    }
}
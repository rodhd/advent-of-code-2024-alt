package org.example.Solutions

import org.example.Common.AoCSolution
import java.awt.Point

class Day12 : AoCSolution() {
    override val day: String
        get() = "12"

    data class Region(
        val unvisitedLocations: MutableSet<Point>,
        val input: List<String>,
        val fields: MutableList<Pair<String, Set<Point>>> = emptyList<Pair<String,Set<Point>>>().toMutableList()
    )

    private fun _getValue(region: Region, position: Point): String {
        return region.input[position.x][position.y].toString()
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
        val region = _initRegion(input)
        while (region.unvisitedLocations.size > 0) {
            val position = region.unvisitedLocations.first()
            val positionValue = _getValue(region, position)
            val field = _exploreRegion(region, position, positionValue)
            region.fields.add(Pair(positionValue, field))

        }
        return region.fields.sumOf { f -> _calculateCost(f.second) }
    }

    fun CalculateSecond(input: List<String>): Int {
        val region = _initRegion(input)
        while (region.unvisitedLocations.size > 0) {
            val position = region.unvisitedLocations.first()
            val positionValue = _getValue(region, position)
            val field = _exploreRegion(region, position, positionValue)
            region.fields.add(Pair(positionValue, field))

        }
        return region.fields.sumOf { f -> _calculateCostAlt(f.second) }
    }

    private fun _initRegion(input: List<String>): Region {
        return Region(
            (0..input.lastIndex).map { r -> (0..input.first().lastIndex).map { c -> Point(r, c) } }.flatten()
                .toMutableSet(), input
        )
    }

    private fun _exploreRegion(region: Region, position: Point, fieldType: String): Set<Point> {
        val result = mutableSetOf(position)
        region.unvisitedLocations.remove(position)
        for(d in DIRECTIONS) {
            val newPos = position.add(d)
            if(isValidPositionString(region.input, newPos)) {
                val newValue = _getValue(region, newPos)
                if (newValue == fieldType && region.unvisitedLocations.contains(newPos)) {
                    result.addAll(_exploreRegion(region, newPos, fieldType))
                }
            }
        }
        return result
    }

    private fun _calculateCost(field: Set<Point>): Int {
        val area = field.size
        var perimeter = 0
        for(p in field) {
            for(d in DIRECTIONS) {
                if(!field.contains(Point(p.x +d.x, p.y+d.y))) {
                    perimeter++
                }
            }
        }
        return area*perimeter
    }

    private fun _calculateCostAlt(field: Set<Point>): Int {
        val area = field.size
        var corners = 0
        for(p in field) {
            corners += listOf(UP, RIGHT, DOWN, LEFT, UP)
                .zipWithNext()
                .map{
                    (d1, d2) -> listOf(
                        p.add(d1),
                        p.add(d2),
                        p.add(d1).add(d2)
                    )
                }
                .count{(s1, s2, corner) ->
                    (!field.contains(s1) && !field.contains(s2)) || (field.containsAll(listOf(s1, s2)) && !field.contains(corner))
                }
        }
        return area*corners
    }
}
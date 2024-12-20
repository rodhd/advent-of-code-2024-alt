package org.example
import org.example.Common.SolutionFactory.generateSolution

fun main(args: Array<String>) {
    println("Please enter the day:")
    val day = readln()
    val solution = generateSolution(day)
    println("\nFirst response:")
    solution.FirstSolution()
    println("\nSecond response:")
    solution.SecondSolution()
}
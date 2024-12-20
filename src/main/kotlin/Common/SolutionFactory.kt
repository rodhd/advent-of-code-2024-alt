package org.example.Common

object SolutionFactory {
    fun generateSolution(day: String): AoCSolution {
        return Class.forName("org.example.Solutions.Day$day").getConstructor().newInstance() as AoCSolution
    }
}
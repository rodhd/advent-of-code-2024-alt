package org.example.Solutions

import org.example.Common.AoCSolution

class Day4 : AoCSolution() {
    override val day: String
        get() = "4"

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
        var counter = 0
        for (i in input.indices) {
            for (l in input[i].indices) {
                if (input[i][l] == 'X') {
                    // left to right
                    if (((l + 3) <= input[i].lastIndex)) {
                        if(arrayOf(input[i].slice(l..l + 3)).joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                    // right to left
                    if (((l -3) >= 0)) {
                        if(arrayOf(input[i].slice(l-3..l)).joinToString(separator = "") == "SAMX") {
                            counter += 1
                        }
                    }
                    // top to bottom
                    if (((i+3) <= input.lastIndex)) {
                        if(input.slice(i..i + 3).map { x -> x[l] }.joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                    // bottom to top
                    if (((i - 3) >= 0)) {
                        if(input.slice(i - 3..i).map { x -> x[l] }.joinToString(separator = "") == "SAMX") {
                            counter += 1
                        }
                    }
                    //diagonal up left to down right
                    if (((l + 3) <= input[i].lastIndex) && ((i+3) <= input.lastIndex)) {
                        if(arrayOf(input[i][l], input[i+1][l+1], input[i+2][l+2], input[i+3][l+3]).joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                    //diaganal up right to down left
                    if (((l - 3) >= 0) && ((i+3) <= input.lastIndex)) {
                        if(arrayOf(input[i][l], input[i+1][l-1], input[i+2][l-2], input[i+3][l-3]).joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                    //diagonal down right to up left
                    if (((l + 3) <= input[i].lastIndex) && ((i-3) >= 0)) {
                        if(arrayOf(input[i][l], input[i-1][l+1], input[i-2][l+2], input[i-3][l+3]).joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                    //diagonal down left to up right
                    if (((l - 3) >= 0) && ((i-3) >= 0)) {
                        if(arrayOf(input[i][l], input[i-1][l-1], input[i-2][l-2], input[i-3][l-3]).joinToString(separator = "") == "XMAS") {
                            counter += 1
                        }
                    }
                }

            }
        }
        return counter
    }
    fun CalculateSecond(input: List<String>): Int {
        var counter = 0
        for (i in 1..input.lastIndex-1) {
            for (l in 1..input[i].lastIndex-1) {
                if(input[i][l] == 'A') {
                    //M.M
                    //.A.
                    //S.S
                    if(
                        input[i-1][l-1] == 'M'
                        && input[i-1][l+1] == 'M'
                        && input[i+1][l-1] == 'S'
                        && input[i+1][l+1] == 'S'
                    ) {
                        counter += 1
                    }
                    //M.S
                    //.A.
                    //M.S
                    if(
                        input[i-1][l-1] == 'M'
                        && input[i-1][l+1] == 'S'
                        && input[i+1][l-1] == 'M'
                        && input[i+1][l+1] == 'S'
                    ) {
                        counter += 1
                    }
                    //S.M
                    //.A.
                    //S.M
                    if(
                        input[i-1][l-1] == 'S'
                        && input[i-1][l+1] == 'M'
                        && input[i+1][l-1] == 'S'
                        && input[i+1][l+1] == 'M'
                    ) {
                        counter += 1
                    }
                    //S.S
                    //.A.
                    //M.M
                    if(
                        input[i-1][l-1] == 'S'
                        && input[i-1][l+1] == 'S'
                        && input[i+1][l-1] == 'M'
                        && input[i+1][l+1] == 'M'
                    ) {
                        counter += 1
                    }
                    //S.S
                    //.A.
                    //M.M
                }
            }
        }
        return counter
    }
}

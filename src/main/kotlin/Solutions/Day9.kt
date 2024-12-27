package org.example.Solutions

import org.example.Common.AoCSolution
import java.math.BigInteger

class Day9 : AoCSolution() {
    override val day: String
        get() = "9"

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

    fun CalculateFirst(input: String): BigInteger {
        val diskMap = _decodeDiskMap(input)
        val regorganizedFiles = _reorganizeFiles(diskMap)
        return _calculateSumFile(regorganizedFiles)
    }

    fun CalculateSecond(input: String): BigInteger {
        val diskMap = _decodeDiskAlt(input)
        val reorganizedFiles = _reoorganizeFilesAlt(diskMap)

        return _calculateSumCheck(reorganizedFiles)
    }

    private fun _decodeDiskMap(diskMap: String): List<String> {
        val buffer = emptyList<String>().toMutableList()
        var isFile = true
        var fileId = 0
        for ((i, c) in diskMap.withIndex()) {
            val space = c.toString().toInt()
            if (isFile) {
                repeat(space) {
                    buffer.add(fileId.toString())
                }
                fileId++
            } else {
                repeat(space) {
                    buffer.add(".")
                }
            }
            isFile = !isFile
        }
        return buffer
    }

    private fun _reorganizeFiles(files: List<String>): List<String> {
        var index = 0
        val filesCopy = files.toMutableList()
        while (index <= filesCopy.lastIndex) {
            if (filesCopy[index] == ".") {
                while (filesCopy.last() == ".") {
                    filesCopy.removeLast()
                }
                if (index > filesCopy.lastIndex) {
                    return filesCopy
                }
                filesCopy[index] = filesCopy.last()
                filesCopy.removeLast()
            }
            index++
        }
        return filesCopy
    }

    private fun _calculateSumFile(reorganizedFiles: List<String>): BigInteger {
        return reorganizedFiles.mapIndexed { index, c -> c.toBigInteger() * index.toBigInteger() }.sumOf { it }
    }

    private fun _decodeDiskAlt(diskMap: String): List<Pair<String, Int>> {
        val buffer = emptyList<Pair<String, Int>>().toMutableList()
        var isFile = true
        var fileId = 0
        for ((i, c) in diskMap.withIndex()) {
            val space = c.toString().toInt()
            if (isFile) {
                buffer.add(Pair(fileId.toString(), c.toString().toInt()))
                fileId++
            } else {
                buffer.add(Pair(".", c.toString().toInt()))
            }
            isFile = !isFile
        }
        return buffer
    }

    private fun _reoorganizeFilesAlt(files: List<Pair<String, Int>>): List<Pair<String, Int>> {
        var filesCopy = files.toMutableList()
        var i = filesCopy.lastIndex
        while (i > 0) {
            if (filesCopy[i].first != ".") {
                var j = 0
                val iValue = filesCopy[i]
                while (j < i) {
                    val jValue = filesCopy[j]
                    if (jValue.first == "." && jValue.second == iValue.second) {
                        filesCopy[j] = iValue
                        filesCopy[i] = Pair(".", jValue.second)
                        break
                    }
                    if (jValue.first == "." && jValue.second > iValue.second) {
                        filesCopy[j] = iValue
                        filesCopy[i] = Pair(".", iValue.second)
                        filesCopy.add(j+1, Pair(".", jValue.second - iValue.second))
                        break
                    }
                    j++
                }
            }
            filesCopy = _aggregateEmptySpace(filesCopy).toMutableList()
            if(i-1 > filesCopy.lastIndex) {
                i = filesCopy.lastIndex
            }
            else {
                i--
            }
        }
        filesCopy = _aggregateEmptySpace(filesCopy).toMutableList()
        return filesCopy
    }

    private fun _calculateSumCheck(files: List<Pair<String, Int>>): BigInteger {
        var result = "0".toBigInteger()
        var index = 0
        for ((i, v) in files.withIndex()) {
            if (v.first != ".") {
                repeat(v.second) {
                    result += v.first.toBigInteger() * index.toBigInteger()
                    index++
                }
            } else {
                repeat(v.second) {
                    index++
                }
            }
        }
        return result
    }

    private fun _aggregateEmptySpace(files: List<Pair<String, Int>>): List<Pair<String, Int>> {
        val result = emptyList<Pair<String, Int>>().toMutableList()
        var temp: Pair<String, Int>? = null
        for (f in files) {
            if(f.first!=".") {
                if(temp != null) {
                    result.add(temp)
                    temp = null
                }
                result.add(f)
            }
            else {
                temp = Pair(".", (temp?.second ?: 0) + f.second)
            }
        }
        return result
    }
}
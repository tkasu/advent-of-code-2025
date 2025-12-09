package github.tkasu.aoc25.challenges.day1

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day1.Part2.solveFromResource
import kotlin.math.abs

object Part2 {
    fun solve(numbers: List<Int>): Int {
        var dialPoint = 50  // starting point is 50
        var zeroClicksCounter = 0
        for (number in numbers) {
            val startDial = dialPoint
            val fullRotations = abs(number) / 100

            val partialTurnsSigned = number % 100
            val partialCrossing = when {
                partialTurnsSigned == 0 || startDial == 0 -> 0
                partialTurnsSigned > 0 -> if (startDial + partialTurnsSigned >= 100) 1 else 0
                else -> if (startDial + partialTurnsSigned <= 0) 1 else 0
            }

            // handle negative modulo
            dialPoint = ((dialPoint + number) % 100 + 100) % 100
            zeroClicksCounter += fullRotations + partialCrossing
        }
        return zeroClicksCounter
    }

    fun solveFromResource(): Int {
        val input = files.readInput("/day1/input.txt")
        val numbers = Part1.parse(input)
        return solve(numbers)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

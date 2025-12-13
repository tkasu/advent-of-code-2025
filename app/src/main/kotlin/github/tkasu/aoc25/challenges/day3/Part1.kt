package github.tkasu.aoc25.challenges.day3

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day3.Part1.solveFromResource

object Part1 {

    fun solve(banks: List<List<Int>>): Long {
        var sum = 0L
        for (joltages in banks) {
            val maxFirstJoltage = joltages.max()
            val maxFirstJoltageDropLast = joltages.dropLast(1).max()
            val maxJoltagePositions = joltages
                .mapIndexed { index, joltage -> if (joltage == maxFirstJoltageDropLast) index else null }
                .filterNotNull()
            val maxJoltage = if (maxFirstJoltage == maxFirstJoltageDropLast && maxJoltagePositions.size > 1) {
                10 * maxFirstJoltageDropLast + maxFirstJoltageDropLast
            } else {
                val maxSecondJoltage = joltages.drop(maxJoltagePositions.first() + 1).max()
                10 * maxFirstJoltageDropLast + maxSecondJoltage
            }
            sum += maxJoltage
        }
        return sum
    }

    fun parse(input: List<String>): List<List<Int>> {
        return input.map { row ->
            row.toList().map { it.digitToInt() }
        }
    }

    fun solveFromResource(): Long {
        val input = files.readInput("/day3/input.txt")
        val banks = parse(input)
        return solve(banks)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

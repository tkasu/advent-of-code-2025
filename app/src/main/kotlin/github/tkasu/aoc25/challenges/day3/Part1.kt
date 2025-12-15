package github.tkasu.aoc25.challenges.day3

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day3.Part1.solveFromResource

object Part1 {

    fun solve(banks: List<List<Int>>): Long {
        return Part2.solve(banks, 2)
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

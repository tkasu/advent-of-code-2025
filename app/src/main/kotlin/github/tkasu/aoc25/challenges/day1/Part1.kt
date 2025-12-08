package github.tkasu.aoc25.challenges.day1

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day1.Part1.solveFromResource

object Part1 {
    fun solve(input: List<String>): Int {
        return 42
    }

    fun solveFromResource(): Int {
        val input = files.readInput("/day1/input.txt")
        return solve(input)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}
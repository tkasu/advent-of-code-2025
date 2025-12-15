package github.tkasu.aoc25.challenges.day3

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day3.Part2.solveFromResource
import kotlin.math.pow

object Part2 {

    private fun maxJoltage(bank: List<Int>, n: Int, accum: Long): Long {
        val nextJoltage = bank.dropLast(n - 1).max()
        val newAccum = accum + 10.toDouble().pow(n - 1).toLong() * nextJoltage
        return if (n == 1) {
            newAccum
        } else {
            val possibleNextIndices = bank.dropLast(n - 1)
                .mapIndexed { index, joltage -> if (joltage == nextJoltage) index else null }
                .filterNotNull()
            possibleNextIndices
                .map { idx -> maxJoltage(bank.drop(idx + 1), n - 1, newAccum) }
                .max()
        }
    }

    fun solve(banks: List<List<Int>>, n: Int): Long {
        return banks.parallelStream().map { bank ->
            maxJoltage(bank, n, 0L)
        }.reduce(0, Long::plus)
    }

    fun solveFromResource(): Long {
        val input = files.readInput("/day3/input.txt")
        val banks = Part1.parse(input)
        return solve(banks, 12)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

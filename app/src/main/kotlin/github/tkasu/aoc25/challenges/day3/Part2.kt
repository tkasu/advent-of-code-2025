package github.tkasu.aoc25.challenges.day3

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day3.Part2.solveFromResource
import kotlin.math.pow

object Part2 {

    private tailrec fun maxJoltage(bank: List<Int>, n: Int, accum: Long = 0L): Long {
        val (nextJoltageIndex, nextJoltage) = bank.dropLast(n - 1)
            .mapIndexed { index, c -> Pair(index, c) }
            .reduce { acc, next -> if (next.second > acc.second) next else acc }
        val newAccum = accum + 10.toDouble().pow(n - 1).toLong() * nextJoltage
        return if (n == 1) {
            newAccum
        } else {
            maxJoltage(bank.drop(nextJoltageIndex + 1), n - 1, newAccum)
        }
    }

    fun solve(banks: List<List<Int>>, n: Int): Long {
        return banks.sumOf { bank ->
            maxJoltage(bank, n)
        }
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

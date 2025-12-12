package github.tkasu.aoc25.challenges.day2

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day2.Part1.solveFromResource

object Part1 {
    data class Range(val start: Long, val end: Long) {
        fun iter(): LongRange {
            return (start..end)
        }
    }

    fun solve(ranges: List<Range>): Long {
        var sum = 0L
        for (range in ranges) {
            for (num in range.iter()) {
                val numString = num.toString()
                if (numString.length % 2 == 0) {
                    val middle = numString.length / 2
                    val part1 = numString.take(middle)
                    val part2 = numString.takeLast(middle)
                    if (part1 == part2) {
                        sum += num
                    }
                }
            }
        }
        return sum
    }

    private fun parseRange(input: String): Range {
        val rangeDigits = input.split("-")
        return Range(rangeDigits[0].toLong(), rangeDigits[1].toLong())
    }

    fun parse(input: String): List<Range> {
        return input.split(",").map { rangeString ->
            parseRange(rangeString)
        }
    }

    fun solveFromResource(): Long {
        val input = files.readInput("/day2/input.txt")
        val stringDigits = parse(input.first())
        return solve(stringDigits)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

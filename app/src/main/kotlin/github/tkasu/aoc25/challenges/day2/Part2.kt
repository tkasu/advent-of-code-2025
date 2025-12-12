package github.tkasu.aoc25.challenges.day2

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day2.Part1.Range
import github.tkasu.aoc25.challenges.day2.Part2.solveFromResource

object Part2 {

    fun solve(ranges: List<Range>): Long {
        var sum = 0L
        for (range in ranges) {
            for (num in range.iter()) {
                val numString = num.toString()
                for (chunkSize in numString.length / 2 downTo 1) {
                    if (numString.length % chunkSize != 0) {
                        continue
                    }
                    val uniqueChunks = numString.chunked(chunkSize).toSet()
                    if (uniqueChunks.size == 1) {
                        sum += num
                        break
                    }
                }
            }
        }
        return sum
    }

    fun solveFromResource(): Long {
        val input = files.readInput("/day2/input.txt")
        val stringDigits = Part1.parse(input.first())
        return solve(stringDigits)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

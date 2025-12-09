package github.tkasu.aoc25.challenges.day1

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day1.Part1.solveFromResource

object Part1 {
    fun solve(numbers: List<Int>): Int {
        var dialPoint = 50  // starting point is 50
        var zeroCounter = 0
        for (number in numbers) {
            // handle negative modulo
            dialPoint = ((dialPoint + number) % 100 + 100) % 100
            if (dialPoint == 0) {
                zeroCounter++
            }
        }
        return zeroCounter
    }

    private fun parseLine(line: String): Int {
        val direction = line.first()
        val unsignedDigit = line.substring(1).toInt()

        val sign = if (direction == 'L') -1 else 1
        val digit = sign * unsignedDigit
        return digit
    }

    fun parse(input: List<String>): List<Int> {
        return input.map { line ->
            parseLine(line)
        }
    }

    fun solveFromResource(): Int {
        val input = files.readInput("/day1/input.txt")
        val numbers = parse(input)
        return solve(numbers)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

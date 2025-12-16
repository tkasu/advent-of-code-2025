package github.tkasu.aoc25.challenges.day4

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day4.Part2.solveFromResource

object Part2{

    fun solve(grid: Grid): Int {
        return grid.forkliftIterativelyMutable()
    }

    fun parse(input: List<String>): Grid {
        return Grid.fromInput(input)
    }

    fun solveFromResource(): Int {
        val input = files.readInput("/day4/input.txt")
        val grid = parse(input)
        return solve(grid)
    }
}

fun main() {
    val result = solveFromResource()
    println(result)
}

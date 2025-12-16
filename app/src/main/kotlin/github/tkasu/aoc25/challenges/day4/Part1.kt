package github.tkasu.aoc25.challenges.day4

import github.tkasu.aoc25.utils.files
import github.tkasu.aoc25.challenges.day4.Part1.solveFromResource

object Part1 {
    data class Grid(val objects: List<List<GridObject>>) {
        val gridSizeY = objects.size
        val gridSizeX = objects.getOrNull(0)?.size ?: 0

        fun gridObjectAt(x: Int, y: Int): GridObject? {
            return objects.getOrNull(y)?.getOrNull(x)?.let { return it }
        }

        fun adjacentGridObjectAt(x: Int, y: Int, shift: Direction): GridObject? {
            val (adjacentX, adjacentY) = shift.adjustByDirector(x, y)
            return gridObjectAt(adjacentX, adjacentY)
        }

        fun adjacentsGridObjectsCount(x: Int, y: Int, kind: GridObject): Int {
            return Direction.entries.map { dir ->
                when (adjacentGridObjectAt(x, y, dir)) {
                    kind -> 1
                    else -> 0
                }
            }.sum()
        }

        fun papersAccessibleByForklift(): Int {
            val canBeMoved = (0..<gridSizeY).map { y ->
                (0..<gridSizeX).map { x ->
                    val objectKind = gridObjectAt(x, y)
                    when (objectKind) {
                        GridObject.PAPER -> {
                            adjacentsGridObjectsCount(x, y, GridObject.PAPER) < 4
                        }

                        else -> false
                    }
                }
            }
            return canBeMoved.flatten().filter { it }.size
        }

        companion object {
            fun fromInput(input: List<String>): Grid {
                val gridObjects = input.map { line ->
                    parseLine(line)
                }
                return Grid(gridObjects)
            }

            private fun parseLine(line: String): List<GridObject> {
                return line.toList().map { c ->
                    when (c) {
                        '.' -> GridObject.EMPTY
                        '@' -> GridObject.PAPER
                        else -> throw IllegalArgumentException("Invalid character: $c")
                    }
                }
            }
        }
    }

    enum class GridObject {
        EMPTY, PAPER
    }

    enum class Direction {
        LEFT, RIGHT, UP, DOWN, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT;

        fun adjustByDirector(x: Int, y: Int): Pair<Int, Int> {
            return when (this) {
                UP -> Pair(x, y - 1)
                DOWN -> Pair(x, y + 1)
                LEFT -> Pair(x - 1, y)
                RIGHT -> Pair(x + 1, y)
                UPLEFT -> Pair(x - 1, y - 1)
                UPRIGHT -> Pair(x + 1, y - 1)
                DOWNLEFT -> Pair(x - 1, y + 1)
                DOWNRIGHT -> Pair(x + 1, y + 1)
            }
        }
    }

    fun solve(grid: Grid): Int {
        return grid.papersAccessibleByForklift()
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

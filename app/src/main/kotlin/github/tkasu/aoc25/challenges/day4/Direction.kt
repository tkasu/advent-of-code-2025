package github.tkasu.aoc25.challenges.day4

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
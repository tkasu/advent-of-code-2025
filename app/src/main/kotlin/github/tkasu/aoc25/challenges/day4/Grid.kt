package github.tkasu.aoc25.challenges.day4

data class Grid(var objects: List<List<GridObject>>) {
    val gridSizeY = objects.size
    val gridSizeX = objects.getOrNull(0)?.size ?: 0

    fun countObjects(kind: GridObject): Int {
        return (0..<gridSizeY).map { y ->
            (0..<gridSizeX).map { x ->
                when (gridObjectAt(x, y)) {
                    kind -> true
                    else -> null
                }
            }
        }.flatten().filterNotNull().size
    }

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

    fun forkliftGrid(): Grid {
        return Grid((0..<gridSizeY).map { y ->
            (0..<gridSizeX).map { x ->
                when (val objectKind = gridObjectAt(x, y)) {
                    null -> throw IllegalStateException()
                    GridObject.PAPER -> {
                        if (adjacentsGridObjectsCount(x, y, GridObject.PAPER) < 4) {
                            GridObject.FORKLIFT
                        } else {
                            objectKind
                        }
                    }

                    else -> objectKind
                }
            }
        })
    }

    fun forklift(): Pair<Grid, Int> {
        val newGrid = forkliftGrid()
        val forkliftedCount = countObjects(GridObject.PAPER) - newGrid.countObjects(GridObject.PAPER)
        return Pair(newGrid, forkliftedCount)
    }

    fun forkliftIterativelyMutable(): Int {
        var forkliftedCount = 0
        while (true) {
            val (newGrid, recentForkliftedCount) = forklift()
            if (recentForkliftedCount == 0) break
            forkliftedCount += recentForkliftedCount
            this.objects = newGrid.objects
        }
        return forkliftedCount
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
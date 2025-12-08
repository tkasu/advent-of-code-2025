package github.tkasu.aoc25.utils

object files {
    fun readInput(resourcesFilePath: String): List<String> {
        val maybeFile = this::class.java.getResourceAsStream(resourcesFilePath)?.bufferedReader()?.readLines()
        return maybeFile ?: throw IllegalArgumentException("Cannot read file $resourcesFilePath")
    }
}
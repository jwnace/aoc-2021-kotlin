import java.io.File

fun main() {
    val input = File("input/day11.txt").readLines()

    fun run(part: Int) {
        val grid = input.map { str -> str.map { c -> Octopus(c.digitToInt()) } }
        var count = 0

        for (step in 1..Int.MAX_VALUE) {
            grid.flatten().forEach { it.energyLevel++ }

            while (grid.flatten().any { it.energyLevel > 9 }) {
                for (i in grid.indices) {
                    for (j in grid[i].indices) {
                        if (grid[i][j].energyLevel > 9) {
                            count++

                            grid[i][j].energyLevel = 0

                            val neighbors = listOf(
                                Pair(i - 1, j - 1),
                                Pair(i - 1, j),
                                Pair(i - 1, j + 1),
                                Pair(i, j - 1),
                                Pair(i, j + 1),
                                Pair(i + 1, j - 1),
                                Pair(i + 1, j),
                                Pair(i + 1, j + 1)
                            )

                            neighbors.forEach {
                                val neighbor = grid.elementAtOrNull(it.first)?.elementAtOrNull(it.second)

                                if (neighbor != null && neighbor.energyLevel != 0) {
                                    neighbor.energyLevel++
                                }
                            }
                        }
                    }
                }
            }

            if (part == 1 && step == 100) {
                println("Day 11, Part 1: $count")
                return
            }

            if (part == 2 && grid.flatten().all { it.energyLevel == 0 }) {
                println("Day 11, Part 2: $step")
                return
            }
        }
    }

    fun part1() {
        run(1)
    }

    fun part2() {
        run(2)
    }

    part1()
    part2()
}

class Octopus(var energyLevel: Int)
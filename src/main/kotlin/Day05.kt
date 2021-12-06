import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val input = File("input/day05.txt").readLines()

    fun solve(part: Int): Int {
        val points = mutableListOf<Pair<Int, Int>>()

        for (line in input) {
            val values = line.split(" -> ")
            val start = values[0].split(',').map { it.toInt() }
            val end = values[1].split(',').map { it.toInt() }
            val x1 = start[0]
            val y1 = start[1]
            val x2 = end[0]
            val y2 = end[1]
            val minX = min(x1, x2)
            val maxX = max(x1, x2)
            val minY = min(y1, y2)
            val maxY = max(y1, y2)

            if (x1 == x2 || y1 == y2) {
                // horizontal and vertical lines
                for (i in minX..maxX) {
                    for (j in minY..maxY) {
                        points.add(Pair(i, j))
                    }
                }
            } else {
                // diagonal lines
                if (part == 2) {
                    for (i in 0..(maxX - minX)) {
                        val xDir = if (x2 - x1 > 0) 1 else -1
                        val yDir = if (y2 - y1 > 0) 1 else -1
                        val x = x1 + i * xDir
                        val y = y1 + i * yDir
                        points.add(Pair(x, y))
                    }
                }
            }
        }

        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }

    fun part1() {
        println("Day 05, Part 1: ${solve(1)}")
    }

    fun part2() {
        println("Day 05, Part 2: ${solve(2)}")
    }

    part1()
    part2()
}

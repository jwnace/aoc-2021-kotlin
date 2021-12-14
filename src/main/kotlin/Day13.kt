import java.io.File

fun main() {
    val input = File("input/day13.txt").readLines()

    fun run(part: Int) {
        val points = mutableListOf<Point>()
        val folds = mutableListOf<Point>()

        for (line in input) {
            if (line.contains(',')) {
                val values = line.split(',')
                val x = values[0].toInt()
                val y = values[1].toInt()
                points.add(Point(x, y))
            } else if (line.contains("fold")) {
                val values = line.split('=')
                val axis = values[0].last()
                val position = values[1].toInt()
                val fold = if (axis == 'x') Point(position, 0) else Point(0, position)
                folds.add(fold)
            }
        }

        for (i in folds.indices) {
            val fold = folds[i]

            if (fold.x > 0) {
                points.filter { it.x > fold.x }.forEach { it.x = fold.x - (it.x - fold.x) }
            } else {
                points.filter { it.y > fold.y }.forEach { it.y = fold.y - (it.y - fold.y) }
            }

            if (part == 1 && i == 0) {
                val count = points.groupBy { it }.count()
                println("Day 13, Part 1: $count")
                return
            }
        }

        println("Day 13, Part 2: ")
        for (i in points.minOf { it.y }..points.maxOf { it.y }) {
            for (j in points.minOf { it.x }..points.maxOf { it.x }) {
                if (points.any { it.x == j && it.y == i }) {
                    print("#")
                } else {
                    print(" ")
                }
            }
            println()
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

data class Point(var x: Int, var y: Int)
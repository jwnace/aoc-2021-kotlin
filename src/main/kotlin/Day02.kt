import java.io.File

fun main() {
    val input = File("input/day02.txt").readLines()

    fun part1() {
        var x = 0
        var y = 0

        for (line in input) {
            val values = line.split(" ")
            val direction = values[0]
            val magnitude = values[1].toInt()

            when (direction) {
                "forward" -> x += magnitude
                "up" -> y -= magnitude
                "down" -> y += magnitude
            }
        }

        println("Day 02, Part 1: x: $x, y: $y, answer: ${x*y}")
    }

    fun part2() {
        var x = 0
        var y = 0
        var aim = 0

        for (line in input) {
            val values = line.split(" ")
            val direction = values[0]
            val magnitude = values[1].toInt()

            when (direction) {
                "forward" -> {
                    x += magnitude
                    y += aim * magnitude
                }
                "up" -> aim -= magnitude
                "down" -> aim += magnitude
            }
        }

        println("Day 02, Part 2: x: $x, y: $y, answer: ${x*y}")
    }

    part1()
    part2()
}
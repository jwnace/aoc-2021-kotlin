import java.io.File

fun main() {
    val input = File("input/day01.txt").readLines().map { it.toInt() }

    fun part1() {
        var count = 0

        for (i in 1 until input.size) {
            count += if (input[i] > input[i-1]) 1 else 0
        }

        println("Day 01, Part 1: $count")
    }

    fun part2() {
        var count = 0

        for (i in 2 until input.size-1) {
            val sumA = input[i-2] + input[i-1] + input[i]
            val sumB = input[i-1] + input[i] + input[i+1]

            count += if (sumB > sumA) 1 else 0
        }

        println("Day 01, Part 2: $count")
    }

    part1()
    part2()
}

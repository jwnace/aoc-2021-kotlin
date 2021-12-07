import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("input/day07.txt").readLines()

    fun part1() {
        val crabs = input[0].split(',').map { it.toInt() }
        val min = crabs.minOrNull() ?: 0
        val max = crabs.maxOrNull() ?: 0
        var fuel = Int.MAX_VALUE

        for (i in min..max) {
            val sum = crabs.sumOf { abs(it - i) }
            fuel = if (sum < fuel) sum else fuel
        }

        println("Day 07, Part 1: $fuel")
    }

    fun part2() {
        val crabs = input[0].split(',').map { it.toInt() }
        val min = crabs.minOrNull() ?: 0
        val max = crabs.maxOrNull() ?: 0
        var fuel = Int.MAX_VALUE

        for (i in min..max) {
            var sum = 0

            for (j in crabs) {
                val steps = abs(j - i)

                for (k in 1..steps) {
                    sum += k
                }
            }

            fuel = if (sum < fuel) sum else fuel
        }

        println("Day 07, Part 2: $fuel")
    }

    part1()
    part2()
}

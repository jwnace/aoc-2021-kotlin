import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("input/day07.txt").readLines()

    fun solve(part: Int): Int {
        val crabs = input[0].split(',').map { it.toInt() }
        val min = crabs.minOf { it }
        val max = crabs.maxOf { it }

        return if (part == 1) {
            (min..max).minOf { x -> crabs.sumOf { abs(it - x) } }
        } else {
            (min..max).minOf { x -> crabs.sumOf { c -> abs(c - x).let { it * (it + 1) / 2 } } }
        }
    }

    fun part1() {
        println("Day 07, Part 1: ${solve(1)}")
    }

    fun part2() {
        println("Day 07, Part 2: ${solve(2)}")
    }

    part1()
    part2()
}

import java.io.File

fun main() {
    val input = File("input/day06.txt").readLines()

    fun solve(days: Int): Long {
        val dict = mutableMapOf<Int, Long>(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 0, 5 to 0, 6 to 0, 7 to 0, 8 to 0, 9 to 0)

        input[0].split(',').map { it.toInt() }.groupingBy { it }.eachCount().forEach { dict[it.key] = it.value.toLong() }

        for (i in 0 until days) {
            dict[9] = dict.getValue(9) + dict.getValue(0)
            dict[7] = dict.getValue(7) + dict.getValue(0)
            dict[0] = 0

            for (j in 1..9) {
                dict[j - 1] = dict.getValue(j)
                dict[j] = 0
            }
        }

        return dict.values.sum()
    }

    fun part1() {
        println("Day 06, Part 1: ${solve(80)}")
    }

    fun part2() {
        println("Day 06, Part 2: ${solve(256)}")
    }

    part1()
    part2()
}

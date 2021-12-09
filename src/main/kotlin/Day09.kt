import java.io.File

fun main() {
    val input = File("input/day09.txt").readLines()

    fun part1() {
        var sum = 0;

        for (i in input.indices) {
            for (j in input[i].indices) {
                val h = input[i][j].digitToInt()
                val n1 = if (i - 1 >= 0) input[i - 1][j].digitToInt() else Int.MAX_VALUE
                val n2 = if (i + 1 < input.size) input[i + 1][j].digitToInt() else Int.MAX_VALUE
                val n3 = if (j - 1 >= 0) input[i][j - 1].digitToInt() else Int.MAX_VALUE
                val n4 = if (j + 1 < input[i].length) input[i][j + 1].digitToInt() else Int.MAX_VALUE

                if (h < n1 && h < n2 && h < n3 && h < n4) {
                    sum += h + 1
                }
            }
        }

        println("Day 09, Part 1: $sum")
    }

    fun calculateBasinSize(i: Int, j: Int, alreadyChecked: MutableList<Pair<Int, Int>> = mutableListOf()): Int {
        if (i < 0 || i >= input.size || j < 0 || j >= input[i].length)
            return 0

        if (input[i][j].digitToInt() == 9)
            return 0

        if (alreadyChecked.contains(Pair(i, j)))
            return 0

        alreadyChecked.add(Pair(i, j))

        return 1 + calculateBasinSize(i - 1, j, alreadyChecked) + calculateBasinSize(i + 1, j, alreadyChecked) +
                calculateBasinSize(i, j - 1, alreadyChecked) + calculateBasinSize(i, j + 1, alreadyChecked)
    }

    fun part2() {
        val sizes = mutableListOf<Int>()

        for (i in input.indices) {
            for (j in input[i].indices) {
                val h = input[i][j].digitToInt()
                val n1 = if (i - 1 >= 0) input[i - 1][j].digitToInt() else Int.MAX_VALUE
                val n2 = if (i + 1 < input.size) input[i + 1][j].digitToInt() else Int.MAX_VALUE
                val n3 = if (j - 1 >= 0) input[i][j - 1].digitToInt() else Int.MAX_VALUE
                val n4 = if (j + 1 < input[i].length) input[i][j + 1].digitToInt() else Int.MAX_VALUE

                if (h < n1 && h < n2 && h < n3 && h < n4) {
                    sizes.add(calculateBasinSize(i, j))
                }
            }
        }

        val top3 = sizes.sortedDescending().take(3)
        println("Day 09, Part 2: ${top3[0] * top3[1] * top3[2]}")
    }

    part1()
    part2()
}

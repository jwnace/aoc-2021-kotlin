import java.io.File

fun main() {
    val input = File("input/day08.txt").readLines()

    fun part1() {
        var sum = 0

        for (line in input) {
            sum += line.split(" | ")[1].split(' ').count { listOf(2, 3, 4, 7).contains(it.length) }
        }

        println("Day 08, Part 1: $sum")
    }

    fun String.intersect(str: String): Int = this.toSet().intersect(str.toSet()).size
    fun String.sort(): String = this.toSortedSet().joinToString("")

    fun part2() {
        var sum = 0

        for (line in input) {
            val values = line.split(" | ")
            val patterns = values[0].split(' ').map { it.sort() }
            val digits = values[1].split(' ').map { it.sort() }
            val dict = mutableMapOf<String, Int>()

            val one = patterns.single { it.length == 2 }
            dict[one] = 1
            val seven = patterns.single { it.length == 3 }
            dict[seven] = 7
            val four = patterns.single { it.length == 4 }
            dict[four] = 4
            val eight = patterns.single { it.length == 7 }
            dict[eight] = 8
            val three = patterns.single { it.length == 5 && it.intersect(one) == 2 }
            dict[three] = 3;
            val two = patterns.single { it.length == 5 && it.intersect(four) == 2 }
            dict[two] = 2;
            val five = patterns.single { it.length == 5 && !dict.containsKey(it) }
            dict[five] = 5;
            val six = patterns.single { it.length == 6 && it.intersect(one) != 2 }
            dict[six] = 6;
            val nine = patterns.single { it.length == 6 && it.intersect(four) == 4 }
            dict[nine] = 9;
            val zero = patterns.single { !dict.containsKey(it) }
            dict[zero] = 0;

            var number = ""

            for (digit in digits) {
                number += dict[digit] ?: 0
            }

            sum += number.toInt()
        }

        println("Day 08, Part 2: $sum")
    }

    part1()
    part2()
}

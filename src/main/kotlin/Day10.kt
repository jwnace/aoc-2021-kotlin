import java.io.File
import java.util.*

fun main() {
    val input = File("input/day10.txt").readLines()

    fun part1() {
        val map = mapOf<Char, Char>('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val points = mapOf<Char, Int>(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        val illegalChars = mutableListOf<Char>()

        for (line in input) {
            val stack = Stack<Char>()

            for (i in line.indices) {
                if (map.containsKey(line[i])) {
                    stack.push(line[i])
                } else {
                    val temp = stack.pop()

                    if (map[temp] != line[i]) {
                        illegalChars.add(line[i])
                        break
                    }
                }
            }
        }

        val score = illegalChars.sumOf { points[it] ?: 0 }

        println("Day 10, Part 1: $score")
    }

    fun part2() {
        val map = mapOf<Char, Char>('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val points = mapOf<Char, Int>('(' to 1, '[' to 2, '{' to 3, '<' to 4)
        val scores = mutableListOf<Long>()

        for (line in input) {
            val stack = Stack<Char>()
            var corrupted = false
            var score = 0L

            for (i in line.indices) {
                if (map.containsKey(line[i])) {
                    stack.push(line[i])
                } else {
                    val temp = stack.pop()

                    if (map[temp] != line[i]) {
                        corrupted = true
                        break
                    }
                }
            }

            if (!corrupted) {
                while (stack.size > 0) {
                    val temp = stack.pop()

                    score *= 5
                    score += points[temp] ?: 0
                }

                scores.add(score)
            }
        }

        val answer = scores.sorted()[scores.size / 2]

        println("Day 10, Part 2: $answer")
    }

    part1()
    part2()
}

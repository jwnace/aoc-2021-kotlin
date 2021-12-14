import java.io.File

fun main() {
    val input = File("input/day14.txt").readLines()

    fun run(steps: Int): Long {
        val template = input[0]
        val rules = mutableMapOf<Pair<Char, Char>, Char>()
        var pairs = mutableMapOf<Pair<Char, Char>, Long>()

        for (i in 2 until input.size) {
            val line = input[i]
            val values = line.split(" -> ")
            val pair = values[0][0] to values[0][1]
            val insert = values[1][0]
            rules[pair] = insert
        }

        for (i in 0 until template.length - 1) {
            val pair = template[i] to template[i + 1]

            if (pairs.containsKey(pair)) {
                pairs[pair] = pairs[pair]!! + 1
            } else {
                pairs[pair] = 1
            }
        }

        for (step in 0 until steps) {
            val newPairs = mutableMapOf<Pair<Char, Char>, Long>()

            for (pair in pairs) {
                val insert = rules[pair.key]!!
                val p1 = pair.key.first to insert
                val p2 = insert to pair.key.second

                if (newPairs.containsKey(p1)) {
                    newPairs[p1] = newPairs[p1]!! + pairs[pair.key]!!
                } else {
                    newPairs[p1] = pairs[pair.key]!!
                }

                if (newPairs.containsKey(p2)) {
                    newPairs[p2] = newPairs[p2]!! + pairs[pair.key]!!
                } else {
                    newPairs[p2] = pairs[pair.key]!!
                }
            }

            pairs = newPairs
        }

        val query = (pairs.map { it.key.first to it.value } + pairs.map { it.key.second to it.value })
            .groupBy { it.first }
            .map { Pair(it.key, (it.value.sumOf { it.second } + 1) / 2) }

        val max = query.maxOf { it.second }
        val min = query.minOf { it.second }

        return max - min
    }

    fun part1() {
        println("Day 14, Part 1: ${run(10)}")
    }

    fun part2() {
        println("Day 14, Part 2: ${run(40)}")
    }

    part1()
    part2()
}

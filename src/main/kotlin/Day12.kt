import java.io.File

fun main() {
    val input = File("input/day12.txt").readLines()

    fun traverse(part: Int, start: Cave, path: MutableList<Cave> = mutableListOf()): Int {
        path.add(start)

        if (start.name == "end") {
            return 1
        }

        var result = 0

        for (next in start.connections) {
            if (next.name == "start") {
                continue
            }

            if (part == 1 &&
                next.name.lowercase() == next.name &&
                path.any { it.name == next.name }
            ) {
                continue
            }

            if (part == 2 &&
                next.name.lowercase() == next.name &&
                path.any { it.name == next.name } &&
                path.filter { it.name.lowercase() == it.name }
                    .groupingBy { it.name }
                    .eachCount()
                    .any { it.value == 2 }
            ) {
                continue
            }

            result += traverse(part, next, path.toMutableList())
        }

        return result
    }

    fun run(part: Int): Int {
        val caves = mutableListOf<Cave>()

        for (line in input) {
            val values = line.split('-')
            val nameA = values[0]
            val nameB = values[1]
            var caveA = caves.find { it.name == nameA }
            var caveB = caves.find { it.name == nameB }

            if (caveA == null) {
                caveA = Cave(nameA)
                caves.add(caveA)
            }

            if (caveB == null) {
                caveB = Cave(nameB)
                caves.add(caveB)
            }

            caveA.connections.add(caveB)
            caveB.connections.add(caveA)
        }

        val start = caves.single { it.name == "start" }

        return traverse(part, start)
    }

    fun part1() {
        println("Day 12, Part 1: ${run(1)}")
    }

    fun part2() {
        println("Day 12, Part 2: ${run(2)}")
    }

    part1()
    part2()
}

class Cave(val name: String, val connections: MutableList<Cave> = mutableListOf())
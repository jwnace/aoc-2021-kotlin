import java.io.File

fun main() {
    val input = File("input/day03.txt").readLines()
    val length = input[0].length

    fun part1() {
        var gamma = ""
        var epsilon = ""

        for (i in 0 until length) {
            val count0 = input.count { it[i] == '0' }
            val count1 = input.count { it[i] == '1' }

            gamma += if (count0 > count1) '0' else '1'
            epsilon += if (count0 > count1) '1' else '0'
        }

        val g = gamma.toInt(2)
        val e = epsilon.toInt(2)

        println("Day 03, Part 1: gamma: $g, epsilon: $e, answer: ${g * e}")
    }

    fun part2() {
        fun calculateOxygenGeneratorRating(): Int {
            val values = input.toMutableList()

            for (i in 0 until length) {
                val count0 = values.count { it[i] == '0' }
                val count1 = values.count { it[i] == '1' }

                if (count0 > count1) {
                    values.retainAll { it[i] == '0' }
                }
                else {
                    values.retainAll { it[i] == '1' }
                }

                if (values.size == 1) {
                    break
                }
            }

            return values.single().toInt(2)
        }

        fun calculateCO2ScrubberRating(): Int {
            val values = input.toMutableList()

            for (i in 0 until length) {
                val count0 = values.count { it[i] == '0' }
                val count1 = values.count { it[i] == '1' }

                if (count0 > count1) {
                    values.retainAll { it[i] == '1' }
                }
                else {
                    values.retainAll { it[i] == '0' }
                }

                if (values.size == 1) {
                    break
                }
            }

            return values.single().toInt(2)
        }

        val oxygen = calculateOxygenGeneratorRating()
        val co2 = calculateCO2ScrubberRating()

        println("Day 03, Part 2: oxygen: $oxygen, c02: $co2, answer: ${oxygen * co2}")
    }

    part1()
    part2()
}

import java.io.File

fun main() {
    data class Cell(var number: Int = 0, var marked: Boolean = false)
    data class Board(
        val cells: MutableList<MutableList<Cell>> = mutableListOf<MutableList<Cell>>(),
        var hasWon: Boolean = false
    )

    val input = File("input/day04.txt").readLines()

    fun solve(part: Int): Int {
        val numbers = input[0].split(',').map { it.toInt() }
        val boards = mutableListOf<Board>()
        var lastToWin = Board()
        var lastNumber = 0

        // populate the bingo cards from the input file
        for (i in 1 until input.size) {
            if (input[i].isBlank()) {
                val board = Board()
                boards.add(board)
            } else {
                val values = input[i].split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
                val board = boards.last()
                board.cells.add(mutableListOf<Cell>())

                for (value in values) {
                    board.cells.last().add(Cell(value, false))
                }
            }
        }

        // draw each number
        for (number in numbers) {
            // mark all the cells that match
            boards.filter { !it.hasWon }
                .flatMap { it.cells }
                .flatten()
                .filter { it.number == number }
                .forEach { it.marked = true }

            // check for a winner
            for (board in boards.filter { !it.hasWon }) {
                for (i in 0..4) {
                    if (
                        (board.cells[i][0].marked && board.cells[i][1].marked && board.cells[i][2].marked && board.cells[i][3].marked && board.cells[i][4].marked)
                        || (board.cells[0][i].marked && board.cells[1][i].marked && board.cells[2][i].marked && board.cells[3][i].marked && board.cells[4][i].marked)
                    ) {
                        board.hasWon = true
                        lastToWin = board
                        lastNumber = number

                        // for part 1, we want the first winner
                        if (part == 1) {
                            val sum = board.cells.flatten().filter { !it.marked }.sumOf { it.number }
                            return sum * number
                        }
                    }
                }
            }
        }

        // for part 2, we want the last winner
        val sum = lastToWin.cells.flatten().filter { !it.marked }.sumOf { it.number }
        return sum * lastNumber
    }

    fun part1() {
        println("Day 04, Part 1: ${solve(1)}")
    }

    fun part2() {
        println("Day 04, Part 2: ${solve(2)}")
    }

    part1()
    part2()
}

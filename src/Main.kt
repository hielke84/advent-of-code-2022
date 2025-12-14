import java.io.File

fun main() {
    try {
        val solutions = listOf<Any>(
            day01.solve(),
            day02.solve(),
            day03.solve(),
            day04.solve(),
            day05.solve(),
            day06.solve(),
            day07.solve(),
            day08.solve(),
            day09.solve(),
            day10.solve(),
            day11.solve(),
            day12.solve(),
            day13.solve(),
            day14.solve()
        )
        println("Advent of Code 2022")
        solutions.forEachIndexed { index, solution ->
            println(
                "Day " + (index + 1).toString().padStart(2, '0') + ": " + solution
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

data class Solution<T>(val actual: T, val expected: T) {
    override fun toString(): String = actual.toString() + " " + icon()
    private fun icon(): String = if (actual == expected) "⭐" else "❌"
}

data class Solutions<T>(val part1: Solution<T>, val part2: Solution<T>) {
    override fun toString(): String = buildString {
        append(part1.toString().padStart(15, ' '))
        append(
            if (part2.toString().contains("\n"))
                "  " + part2.toString().replace("\n", "\n" + " ".repeat(26))
            else
                part2.toString().padStart(15, ' ')
        )
    }

}

fun input(day: Int) =
    File("src/Day${day.toString().padStart(2, '0')}", "input.txt")
        .readLines()

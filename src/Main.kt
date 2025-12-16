import java.io.File

fun main() {
    val bar = "-".repeat(54)
    println(bar)
    println("Advent of Code 2022")
    println(bar)
    listOf<() -> Solutions<*>>(
        { day01.solve() },
        { day02.solve() },
        { day03.solve() },
        { day04.solve() },
        { day05.solve() },
        { day06.solve() },
        { day07.solve() },
        { day08.solve() },
        { day09.solve() },
        { day10.solve() },
        { day11.solve() },
        { day12.solve() },
        { day13.solve() },
        { day14.solve() },
    ).forEachIndexed { index, solution ->
        val t0 = System.currentTimeMillis()
        val v = try {
            solution.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val t = System.currentTimeMillis() - t0
        println(
            "Day " + (index + 1).toString().padStart(2, '0')
                    + ": " + v
                    + t.toString().padStart(10, ' ') + " ms"
        )
    }
    println(bar)
}

data class Solution<T>(val actual: T, val expected: T) {
    override fun toString(): String = actual.toString() + " " + icon()
    private fun icon(): String = if (actual == expected) "⭐" else "❌"
}

data class Solutions<T>(val part1: Solution<T>, val part2: Solution<T>) {
    override fun toString(): String = buildString {
        append(part1.toString().padStart(15, ' '))
        append(part2.toString().padStart(15, ' '))
    }
}

fun input(day: Int) =
    File("src/Day${day.toString().padStart(2, '0')}", "input.txt")
        .readLines()

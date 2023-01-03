fun main() {
    val input = input("Day10")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    return cycles(input)
        .mapIndexed { index, value -> (index + 1) * value }
        .filterIndexed { index, _ -> (index - 19) % 40 == 0 }
        .sum()
}

private fun part2(input: List<String>): String {
    return cycles(input)
        .mapIndexed { index, cycle -> if (index % 40 in cycle - 1..cycle + 1) "█" else " " }
        .joinToString(separator = "")
        .chunked(40)
        .joinToString(separator = "\n")
}

private fun cycles(input: List<String>): MutableList<Int> {
    var registry = 1
    val cycles = mutableListOf(registry)
    input
        .map { it.words() }
        .map {
            when (it[0]) {
                "addx" -> {
                    registry += it[1].toInt()
                    listOf(registry - it[1].toInt(), registry)
                }
                "noop" -> listOf(registry)
                else -> emptyList()
            }
        }
        .forEach { cycles.addAll(it) }
    return cycles
}

private fun String.words(): List<String> = this.split(" ")
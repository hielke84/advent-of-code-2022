fun main() {
    val input = input(1)
    test({ part1(input) }, 71502)
    test({ part2(input) }, 208191)
}

private fun part1(input: List<String>): Int =
    totals(input).max()

private fun part2(input: List<String>): Int =
    totals(input)
        .sortedDescending()
        .take(3)
        .sum()

private fun totals(input: List<String>): List<Int> =
    edges(input)
        .windowed(size = 2, step = 2) { (from, to) ->
            input
                .slice(from..to)
                .map(String::toInt)
        }
        .map(List<Int>::sum)

private fun edges(input: List<String>) =
    input.flatMapIndexed { index, line ->
        when {
            index == 0 || index == input.lastIndex -> listOf(index)
            line.isEmpty() -> listOf(index - 1, index + 1)
            else -> emptyList()
        }
    }

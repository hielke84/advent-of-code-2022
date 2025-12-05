fun main() {
    val input = input("Day05")
    test({ part1(input) }, "ZWHVFWQWW")
    test({ part2(input) }, "HZFZCCWWV")
}

private fun part1(input: List<String>): String {
    val separator = input.indexOf("")
    var stacks = parseStacks(input, separator)
    val instructions = parseInstructions(input, separator)
    instructions.forEach {
            stacks = move1(stacks, it[0], it[1], it[2])
    }
    return topCrates(stacks)
}

private fun part2(input: List<String>): String {
    val separator = input.indexOf("")
    var stacks = parseStacks(input, separator)
    val instructions = parseInstructions(input, separator)
    instructions.forEach {
        stacks = move2(stacks, it[0], it[1], it[2])
    }
    return topCrates(stacks)
}

private fun move1(stacks: List<List<Char>>, moves: Int, from: Int, to: Int): List<List<Char>> {
    val result = stacks.toMutableList()
    repeat(moves) {
        val fromStack = result[from - 1]
        val toStack = result[to - 1]
        result[to - 1] = toStack.plus(fromStack.last())
        result[from - 1] = fromStack.dropLast(1)
    }
    return result
}

private fun move2(stacks: List<List<Char>>, moves: Int, from: Int, to: Int): List<List<Char>> {
    val result = stacks.toMutableList()
    val fromStack = result[from - 1]
    val toStack = result[to - 1]
    result[to - 1] = toStack.plus(fromStack.subList(fromStack.size - moves, fromStack.size))
    result[from - 1] = fromStack.dropLast(moves)
    return result
}

private fun topCrates(stacks: List<List<Char>>) =
    stacks.map { if(it.isNotEmpty()) it.last() else " " }.joinToString(separator = "")

private fun parseStacks(input: List<String>, separator: Int): List<List<Char>> {
    val crateLines = input.subList(0, separator - 1)
    val maxLength = crateLines.maxOf { it.length }
    val crates = crateLines
        .map { it.padEnd(maxLength, ' ') }
        .map(::sanitize)
        .reversed()
        .map { it.toCharArray().toList() }
    return transpose(crates)
}

private fun sanitize(input: String) =
    input
        .replace("    ", " [.]")
        .replace(" ", "")
        .replace(".", " ")
        .replace("[", "")
        .replace("]", "")

private fun transpose(matrix: List<List<Char>>): List<List<Char>> {
    val maxSize = matrix.maxOf { it.size }
    return List(maxSize) { i ->
        matrix
            .map { it[i] }
            .filter { it != ' ' }
            .toMutableList()
    }
}

private fun parseInstructions(input: List<String>, separator: Int) =
    input.subList(separator + 1, input.lastIndex + 1)
        .map(::codify)

private fun codify(it: String): List<Int> =
    """move (\d+) from (\d+) to (\d+)""".toRegex()
        .find(it)
        ?.groupValues
        ?.drop(1)
        ?.map(String::toInt)
        ?: emptyList()

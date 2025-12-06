fun main() {
    val input = input(7)
    test({ part1(input) }, 1908462)
    test({ part2(input) }, 3979145)
}

private fun part1(input: List<String>): Int =
    dirSizes(input)
        .map { it.value }
        .filter { it <= 100_000 }
        .sum()

private fun part2(input: List<String>): Int {
    val dirSizes = dirSizes(input)
    val used = dirSizes["/"] ?: 0
    val unused = 70_000_000 - used
    val overflow = 30_000_000 - unused
    return dirSizes
        .map { it.value }
        .filter { it >= overflow }
        .minOf { it }
}

private fun dirSizes(input: List<String>): Map<String, Int> {
    val dirStack = mutableListOf<String>()
    val dirSizes = mutableMapOf<String, Int>()

    input.forEach { command ->
        when {
            command.startsWith("$ cd ..") -> dirStack.removeLast()
            command.startsWith("$ cd") -> dirStack.add(command.words()[2])
            command.startsWith("$ ls") -> Unit
            command.startsWith("dir") -> Unit
            else -> {
                dirStack.indices
                    .map { path(dirStack, it) }
                    .forEach { dirSizes[it] = (dirSizes[it] ?: 0) + command.words()[0].toInt() }
            }
        }
    }

    return dirSizes.toMap()
}

private fun path(dirStack: List<String>, index: Int) =
    dirStack.first() + dirStack.subList(1, index + 1).joinToString("/")

private fun String.words(): List<String> = this.split(" ")

fun main() {
    val input = input("Day03")
    test(part1(input), 8123)
    test(part2(input), 2620)
}

private fun part1(input: List<String>): Int =
    input
        .map(::compartments)
        .map(::sharedItem)
        .map(::priority)
        .sum()

private fun part2(input: List<String>): Int =
    input
        .chunked(3)
        .map(::sharedItem)
        .map(::priority)
        .sum()

private fun compartments(rucksack: String): List<String> =
    listOf(
        rucksack.substring(0, rucksack.length / 2),
        rucksack.substring(rucksack.length / 2)
    )

private fun sharedItem(rucksacks: List<String>): Char =
    rucksacks
        .map(String::toSet)
        .reduce(Set<Char>::intersect)
        .first()

private fun priority(char: Char): Int =
    when (char.code) {
        in 97..122 -> char.code - 96
        in 65..90 -> char.code - 38
        else -> 0
    }
fun main() {
    val input = input("Day08").toIntMatrix()
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<List<Int>>): Int {
    return input.indices.sumOf { y ->
        input[y].indices.count { x ->
            isVisible(input.row(y), x) || isVisible(input.column(x), y)
        }
    }
}

private fun part2(input: List<List<Int>>): Int {
    return input.indices.maxOf { y ->
        input[y].indices.maxOf { x ->
            score(input.row(y), x) * score(input.column(x), y)
        }
    }
}

private fun List<String>.toIntMatrix(): List<List<Int>> = this.map { it.toCharArray().map(Char::digitToInt) }

private fun List<List<Int>>.row(i: Int) = this[i]

private fun List<List<Int>>.column(i: Int) = this.map { it[i] }

private fun isVisible(row: List<Int>, index: Int): Boolean = isVisibleLeft(row, index) || isVisibleRight(row, index)

private fun isVisibleLeft(row: List<Int>, index: Int): Boolean {
    if (index == 0) return true
    return row[index] > row.subList(0, index).max()
}

private fun isVisibleRight(row: List<Int>, index: Int): Boolean {
    if (index == row.lastIndex) return true
    return row[index] > row.subList(index + 1, row.size).max()
}

private fun score(row: List<Int>, index: Int): Int = scoreLeft(row, index) * scoreRight(row, index)

private fun scoreLeft(row: List<Int>, thresholdIndex: Int): Int {
    if (thresholdIndex == 0) return 0
    val left = row.subList(0, thresholdIndex)
    val edgeIndex = left.indexOfLastOrNull { it >= row[thresholdIndex] } ?: 0
    return thresholdIndex - edgeIndex
}

private fun scoreRight(row: List<Int>, thresholdIndex: Int): Int {
    if (thresholdIndex == row.lastIndex) return 0
    val right = row.subList(thresholdIndex + 1, row.size)
    val edgeIndex = thresholdIndex + 1 + (right.indexOfFirstOrNull { it >= row[thresholdIndex] } ?: right.lastIndex)
    return edgeIndex - thresholdIndex
}

private fun List<Int>.indexOfFirstOrNull(predicate: (Int) -> Boolean): Int? {
    val index = this.indexOfFirst(predicate)
    return if (index > -1) index else null
}

private fun List<Int>.indexOfLastOrNull(predicate: (Int) -> Boolean): Int? {
    val index = this.indexOfLast(predicate)
    return if (index > -1) index else null
}
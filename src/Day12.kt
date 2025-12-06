private const val START_CHAR = '`' // one step lower than 'a'
private const val END_CHAR = '{' // one step higher than 'z'

fun main() {
    val input = input(12)
        .map {
            it.replace('S', START_CHAR)
                .replace('E', END_CHAR)
        }
    test({ part1(input) }, 370)
    test({ part2(input) }, 363)
}

private fun part1(input: List<String>): Int {
    val heightMap = input.toArrays()
    val start = heightMap.indexesOf(START_CHAR).first()
    return minDist(heightMap, start)
}

private fun part2(input: List<String>): Int {
    val newStartChar = 'a'
    val newInput = input.map { it.replace(START_CHAR, newStartChar) }
    val heightMap = newInput.toArrays()
    val starts = heightMap.indexesOf(newStartChar)
    return starts.minOfOrNull { minDist(heightMap, it) } ?: -1
}

private fun minDist(heightMap: Array<Array<Char>>, start: Pair<Int, Int>): Int {
    val distMap: Array<Array<Int>> = Array(heightMap.size) { Array(heightMap[0].size) { Int.MAX_VALUE } }
    distMap[start.first][start.second] = 0

    return minDist(heightMap, distMap, start)
}

private fun minDist(heightMap: Array<Array<Char>>, distMap: Array<Array<Int>>, start: Pair<Int, Int>): Int {
    val (x, y) = start
    val dist = distMap[x][y]
    val height = heightMap[x][y].code

    if (heightMap[x][y] == END_CHAR) return dist

    return listOf(x to y - 1, x to y + 1, x - 1 to y, x + 1 to y)
        .asSequence()
        .filter {
            (heightMap.getOrNull(it)?.code ?: Int.MAX_VALUE) <= height + 1
                    && (distMap.getOrNull(it) ?: 0) > dist + 1
        }
        .onEach { distMap[it.first][it.second] = dist + 1 }
        .minOfOrNull { minDist(heightMap, distMap, it) }
        ?: Int.MAX_VALUE
}

private fun List<String>.toArrays(): Array<Array<Char>> =
    Array(this.size) { x -> Array(this[0].length) { y -> this[x][y] } }

private fun Array<Array<Char>>.indexesOf(char: Char): List<Pair<Int, Int>> = this
    .mapIndexed { index, line -> index to line.indexOf(char) }
    .filter { pair -> pair.second > -1 }

private fun <T> Array<Array<T>>.getOrNull(p: Pair<Int, Int>): T? = this.getOrNull(p.first)?.getOrNull(p.second)


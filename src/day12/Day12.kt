package day12

import input
import test

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
    val heightMap = input.toGrid()
    val start = heightMap.indexesOf(START_CHAR).first()
    return minDist(heightMap, start)
}

private fun part2(input: List<String>): Int {
    val newStartChar = 'a'
    val newInput = input.map { it.replace(START_CHAR, newStartChar) }
    val heightMap = newInput.toGrid()
    val starts = heightMap.indexesOf(newStartChar)
    return starts.minOfOrNull { minDist(heightMap, it) } ?: -1
}

private fun minDist(heightMap: Grid<Char>, start: Point): Int {
    val distMap: Grid<Int> = Array(heightMap.size) { Array(heightMap[0].size) { Int.MAX_VALUE } }
    distMap[start] = 0
    return minDist(heightMap, distMap, start)
}

private fun minDist(heightMap: Grid<Char>, distMap: Grid<Int>, start: Point): Int {
    val dist = distMap[start]
    val height = heightMap[start].code

    if (heightMap[start] == END_CHAR) return dist

    return listOf(start.up(), start.down(), start.left(), start.right()).asSequence()
        .filter {
            (heightMap.getOrNull(it)?.code ?: Int.MAX_VALUE) <= height + 1
                    && (distMap.getOrNull(it) ?: 0) > dist + 1
        }
        .onEach { distMap[it] = dist + 1 }
        .minOfOrNull { minDist(heightMap, distMap, it) }
        ?: Int.MAX_VALUE
}

private data class Point(val x: Int, val y: Int) {
    fun up() = copy(y = y - 1)
    fun down() = copy(y = y + 1)
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
}

private typealias Grid<T> = Array<Array<T>>

private fun List<String>.toGrid(): Grid<Char> =
    this.map { it.toCharArray().toTypedArray() }.toTypedArray()

private operator fun <T> Grid<T>.get(point: Point) = this[point.x][point.y]

private operator fun <T> Grid<T>.set(point: Point, value: T) {
    this[point.x][point.y] = value
}

private fun <T> Grid<T>.getOrNull(point: Point): T? =
    this.getOrNull(point.x)?.getOrNull(point.y)

private fun <T> Grid<T>.indexesOf(value: T): List<Point> =
    this
        .mapIndexed { index, line -> Point(index, line.indexOf(value)) }
        .filter { point -> point.y > -1 }

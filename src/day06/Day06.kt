package day06

import Solution
import Solutions
import input

fun solve(): Solutions<Int> {
    val input = input(6)[0].toCharArray().toList()
    return Solutions(
        Solution(part1(input), 1582),
        Solution(part2(input), 3588)
    )
}

private fun part1(input: List<Char>): Int = minSizeContainingMarker(input, 4)

private fun part2(input: List<Char>): Int = minSizeContainingMarker(input, 14)

private fun minSizeContainingMarker(input: List<Char>, markerSize: Int) =
    (markerSize..input.size)
        .map { input.subList(it - markerSize, it).toSet().size }
        .indexOfFirst { it == markerSize } + markerSize

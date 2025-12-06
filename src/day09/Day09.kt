package day09

import input
import test
import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val input = input(9)
    test({ part1(input) }, 6464)
    test({ part2(input) }, 2604)
}

private fun part1(input: List<String>): Int {
    var head = Position(0, 0)
    var tail = Position(0, 0)
    val visits = mutableSetOf(tail)
    input.map { Instruction(Direction.valueOf(it.words()[0]), it.words()[1].toInt()) }
        .forEach { instruction ->
            repeat(instruction.distance) {
                head = moveHead(head, instruction.direction)
                tail = moveTail(tail, head)
                visits.add(tail)
            }
        }
    return visits.size
}

private fun part2(input: List<String>): Int {
    val knots = MutableList(10) { Position(0, 0) }
    val visits = mutableSetOf(knots.last())
    input.map { Instruction(Direction.valueOf(it.words()[0]), it.words()[1].toInt()) }
        .forEach { instruction ->
            repeat(instruction.distance) {
                knots[0] = moveHead(knots[0], instruction.direction)
                (1 until knots.size).forEach { i ->
                    knots[i] = moveTail(knots[i], knots[i - 1])
                }
                visits.add(knots.last())
            }
        }
    return visits.size
}

private fun moveHead(head: Position, direction: Direction): Position =
    when (direction) {
        Direction.U -> head.up()
        Direction.R -> head.right()
        Direction.D -> head.down()
        Direction.L -> head.left()
    }

private fun moveTail(tail: Position, head: Position): Position =
    if (abs(head.y - tail.y) == 2 || abs(head.x - tail.x) == 2)
        Position(tail.x + (head.x - tail.x).sign, tail.y + (head.y - tail.y).sign)
    else tail

private data class Position(val x: Int, val y: Int) {
    fun up() = copy(y = y - 1)
    fun down() = copy(y = y + 1)
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
}

private data class Instruction(val direction: Direction, val distance: Int)

private enum class Direction { U, R, D, L }

private fun String.words(): List<String> = this.split(" ")

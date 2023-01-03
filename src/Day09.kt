import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val input = input("Day09")
    println(part1(input))
    println(part2(input))
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
        Direction.U -> head.copy(y = head.y + 1)
        Direction.R -> head.copy(x = head.x + 1)
        Direction.D -> head.copy(y = head.y - 1)
        Direction.L -> head.copy(x = head.x - 1)
    }

private fun moveTail(tail: Position, head: Position): Position =
    if (abs(head.y - tail.y) == 2 || abs(head.x - tail.x) == 2)
        Position(tail.x + (head.x - tail.x).sign, tail.y + (head.y - tail.y).sign)
    else tail

private data class Position(val x: Int, val y: Int)

private data class Instruction(val direction: Direction, val distance: Int)

private enum class Direction { U, R, D, L }

private fun String.words(): List<String> = this.split(" ")
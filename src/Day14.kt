import kotlin.math.max
import kotlin.math.min

fun main() {
    val input = input(14)
        .flatMap { line ->
            line.split(" -> ")
                .map { coordinate ->
                    val (x, y) = coordinate.split(",")
                    Point(x.toInt(), y.toInt())
                }
                .zipWithNext()
        }.toSet()

    test({ part1(input) }, 625)
    test({ part2(input) }, 25193)
}

private fun part1(vertices: Set<Vertex>): Int {
    val rocks = placeRocks(vertices)
    val yMax = rocks.maxBy { it.y }.y
    return placeSand(rocks, yMax, yMax + 99)
}

private fun part2(vertices: Set<Vertex>): Int {
    val rocks = placeRocks(vertices)
    val yMax = rocks.maxBy { it.y }.y
    return placeSand(rocks, yMax + 99, yMax + 2)
}

private fun placeRocks(vertices: Set<Vertex>): Set<Point> =
    vertices.flatMap { vertex ->
        when {
            vertex.first.x == vertex.second.x -> {
                // vertical
                val yMin = min(vertex.first.y, vertex.second.y)
                val yMax = max(vertex.first.y, vertex.second.y)
                (yMin..yMax)
                    .map { y -> Point(vertex.first.x, y) }
                    .toSet()
            }

            vertex.first.y == vertex.second.y -> {
                // horizontal
                val xMin = min(vertex.first.x, vertex.second.x)
                val xMax = max(vertex.first.x, vertex.second.x)
                (xMin..xMax)
                    .map { x -> Point(x, vertex.first.y) }
                    .toSet()
            }

            else -> emptySet()
        }
    }.toSet()

private fun placeSand(rocks: Set<Point>, yCutoff: Int, yFloor: Int): Int {
    val sand = hashSetOf<Point>()

    do {
        val grain = placeGrain(Point(500, 0), rocks, sand, yCutoff, yFloor)
        grain?.let { sand.add(it) }
    } while (grain != null)

    return sand.size
}

private fun placeGrain(grain: Point, rocks: Set<Point>, sand: Set<Point>, yCutoff: Int, yFloor: Int): Point? {
    if (grain.y > yCutoff || sand.contains(Point(500, 0)))
        return null

    val down = grain.down()
    return if (!rocks.contains(down) && !sand.contains(down) && down.y < yFloor) {
        return placeGrain(down, rocks, sand, yCutoff, yFloor)
    } else {
        val left = down.left()
        if (!rocks.contains(left) && !sand.contains(left) && left.y < yFloor) {
            placeGrain(left, rocks, sand, yCutoff, yFloor)
        } else {
            val right = down.right()
            if (!rocks.contains(right) && !sand.contains(right) && right.y < yFloor) {
                placeGrain(right, rocks, sand, yCutoff, yFloor)
            } else {
                grain
            }
        }
    }
}

private data class Point(val x: Int, val y: Int) {
    fun down() = copy(y = y + 1)
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
}

private typealias Vertex = Pair<Point, Point>

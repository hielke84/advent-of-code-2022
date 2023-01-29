import java.io.File

fun input(filename: String) = File("src", "$filename.txt").readLines()

fun test(actual: Long, expected: Long) = test(actual.toString(), expected.toString())

fun test(actual: String, expected: String) {
    val test = if(actual != expected) " (expected: $expected)" else ""
    println("$actual$test")
}
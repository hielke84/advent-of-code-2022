import java.io.File

fun input(filename: String) = File("src", "$filename.txt").readLines()

fun test(actual: Int, expected: Int) = test(actual.toString(), expected.toString())

fun test(actual: Long, expected: Long) = test(actual.toString(), expected.toString())

fun test(actual: String, expected: String) {
    val test = if(actual.trim() != expected.trim()) " (expected: $expected)" else ""
    println("$actual$test")
}
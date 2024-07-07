import java.io.File

fun input(filename: String) = File("src", "$filename.txt").readLines()

fun test(actual: Int, expected: Int) = test(actual.toString(), expected.toString())

fun test(actual: Long, expected: Long) = test(actual.toString(), expected.toString())

fun test(actual: String, expected: String) {
    println(
        if (actual.trim() == expected.trim())
            "$actual \u001B[32m[PASS]\u001B[0m"
        else
            "$actual \u001B[31m[FAIL]\u001b[0m expected: $expected"
    )
}
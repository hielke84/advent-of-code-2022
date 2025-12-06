import java.io.File

fun input(day: Int) =
    File("src", "Day${day.toString().padStart(2, '0')}.txt")
        .readLines()

fun <T> test(actual: () -> T, expected: T) =
    try {
        test(actual.invoke(), expected)
    } catch (e: Exception) {
        e.printStackTrace()
    }

private fun <T> test(actual: T, expected: T) =
    println(
        failPass(actual.toString().trim() == expected.toString().trim())
                + " expected ${expected.toString()}, was ${actual.toString()}"
    )

private fun failPass(test: Boolean): String =
    if (test)
        "\u001B[32m[PASS]\u001B[0m"
    else
        "\u001B[31m[FAIL]\u001b[0m"

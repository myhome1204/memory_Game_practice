val A: Int = 10
val B: Int = 20
var C: Boolean = if (A == B) true else false
var num1: Int = 10
var num2: Int = num1 * 2
fun test(score: Int): Char {
    when (score) {
        in 90..100 -> return 'A'
        in 80..89 -> return 'B'
        in 70..79 -> return 'C'
        else -> return 'F'
    }
}

fun scoreChange(count: Int): Int {
    return count * 5
}

fun nullCheck(number1: Int?, number2: Int?): Int {
    if (number1 == null && number2 == null) {
        return 0
    } else if (number1 == null) {
        return number2!!
    } else if (number2 == null) {
        return number1!!
    }
    return number1!! + number2!!
}

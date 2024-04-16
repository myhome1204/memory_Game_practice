val number: Int? = 10
val number2 : Int? = 3+5
val sum : Int? = number!! + number2!!
var numbers:Array<Int> = arrayOf(1,2,3,4)
// !!로 Null이 아님을 보장시킬수잇다
if(null ==null){
    println(numbers[3])
}else{
    println("false")
}


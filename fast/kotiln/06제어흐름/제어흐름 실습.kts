val num2: Int = 20
val num1: Int = if (num2 + 10 > 29) 30 else 50
println(num1)
//표현과 식
//표현 (어떤것을 표현하기위해 사용되는 문법적인 요소 val,var ,Int ,Short)
//식 (값을만들어냄 값임 10+30,10+20)
val number1: Int = 5
when(number1){
    5 -> {
        println("5입니다")
    }
    6 ->{
        println("6입니다")
    }
    is Boolean ->
    else->{
        println("5과 6둘다 아닙니다")
    }
}
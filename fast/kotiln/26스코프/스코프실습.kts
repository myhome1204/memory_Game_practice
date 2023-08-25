class Person(var name : String? = null,var age :Int? = null){

}
//apply
//-적용하다
// -객체를 초기화 할때 사용하면 좋다
val gildong = Person().apply{
    name = "길동"
    age = 20
}
//also
//유효성검사 (이미 객체가 만들어진것임 it은)
//수신된 객체의 속성을 변경하지 않고 사용할 때
val gildong2 = Person("victor").also {
    nameIsGildong(it.name)
}
//rum
//기본적으로 apply와 동이하다
//스코프의 마지막줄을 리턴한다 > 특정계산결과가 필요한경우
val ageAfter10year = Person("gildong",10).run{
    age !!+10
}
//with는 nullable 타입을 받지 못한다
val ageAfter10year2 = with(Person("gildong",10)){
    age !!+10
}

//let
//기본적으로 also와 동일하다
//스코프의 마지막줄을 리턴한다 > 특정계산결과가 필요한경우
val gildong3 = Person("victor").let {

}
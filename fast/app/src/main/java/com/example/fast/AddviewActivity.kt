package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class AddviewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addview)
        var carList = mutableListOf<Car>()
        for(i in 0..10){
            carList.add(Car(""+i+"번째 자동차",""+i+"번째 엔진"))
        }
        val container = findViewById<LinearLayoutCompat>(R.id.container)
        //inflater은 xml을  부풀려서 (사용자눈에 보이게끔) 가져온다 fragment강의때사용함
        val inflater = LayoutInflater.from(this@AddviewActivity)
        carList.forEach {
            //내가 따로 만든 xml을 inflater로 가져온다
            val carItemView = inflater.inflate(R.layout.car_item,null)
            val carImage =  carItemView.findViewById<ImageView>(R.id.carImage)
            val nthCar =  carItemView.findViewById<TextView>(R.id.nthCar)
            val nthEngine =  carItemView.findViewById<TextView>(R.id.nthEngine)
            carImage.setImageDrawable(resources.getDrawable(R.drawable.bluebackground,this.theme))
            nthCar.text = it.nthCar
            nthEngine.text= it.nthEngine
            //inflater로 가져온 xml을 container에 붙이는작업이 아래 코드임
            container.addView(carItemView)
        }
        //스크롤이 없는데 scroll 뷰를 만들어줘야함  마우스 꾹 누르고 내리면 내려짐
    }
}
class Car(val nthCar :String ,val nthEngine :String){

}
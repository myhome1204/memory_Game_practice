package com.example.fast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class HomeWorkAddViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work_add_view)
        //처음설계가 이 액티비티에 liner뷰를 만들고 (부모역할을하는뷰) 지금내 액티비티의 xml을 부풀려서 다른xml을 달 수있는 형태로 바꿔주는느낌? 준비하는느낌
        //거기에 다른 xml을 다는형태로 접근
        //list뷰에서 한칸을 아이템이라고 많이한다 그리고 상세페이지를 Detail이란 키워드를넣는다
        val inflater = LayoutInflater.from(this@HomeWorkAddViewActivity)
        val humanList = mutableListOf<Human>()
        val container = findViewById<LinearLayoutCompat>(R.id.container)
        for(i in 1..10){
            humanList.add(Human(""+i+"번째사람","010-1111-111"+i))
        }
        humanList.forEach {
            val c = inflater.inflate(R.layout.car_item,null)
            val image = c.findViewById<ImageView>(R.id.carImage)
            val count = c.findViewById<TextView>(R.id.nthCar)
            val number = c.findViewById<TextView>(R.id.nthEngine)
            image.setImageDrawable(resources.getDrawable(R.drawable.bluebackground,this.theme))
            count.text = it.count
            number.text = it.Phonenumber
            val data1  = it.count.toString()
            val data2 = it.Phonenumber.toString()
            c.setOnClickListener {
                val intent  = Intent(this@HomeWorkAddViewActivity, AddviewchangeActivity::class.java)
                Log.d("testt",""+data1)
                intent.putExtra("count",data1)
                intent.putExtra("number",data2)
                startActivity(intent)
            }
            //더이상 부모가없는 최상위뷰를 addView안에넣어야함
            container.addView(c)
        }
    }
}
class Human(val count:String,val Phonenumber:String)
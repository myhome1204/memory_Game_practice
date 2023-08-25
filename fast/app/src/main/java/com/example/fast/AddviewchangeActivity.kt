package com.example.fast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.fast.R
import org.w3c.dom.Text

class AddviewchangeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addviewchange)
        //클릭되면 화면ㅇ ㅣ넘어간다(액티비티전환) 그 전환된 액티비티에는 그 눌러진 사람의 번호ㅎ와몇번째 사람인지 표시
        val intent = getIntent()
        //val data :String? = intent.extras?.getString("extra-data")
        val count1:String?  = intent.extras?.getString("count")
        val number1:String?  = intent.extras?.getString("number")
        Log.d("testt","data1"+count1)
        val container = findViewById<LinearLayoutCompat>(R.id.container1)
        val inflater = LayoutInflater.from(this@AddviewchangeActivity)
        val c = inflater.inflate(R.layout.car_item,null)
        val data1 = c.findViewById<ImageView>(R.id.carImage)
        val data2 = c.findViewById<TextView>(R.id.nthCar)
        val data3 = c.findViewById<TextView>(R.id.nthEngine)
        data1.setImageDrawable(resources.getDrawable(R.drawable.bluebackground,this.theme))
        data2.text = count1
        data3.text = number1
        container.addView(c)

    }
}


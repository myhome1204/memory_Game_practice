package com.example.fast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import org.w3c.dom.Text

class ApplicationActivity01 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application01)
        findViewById<TextView>(R.id.changeActivity).setOnClickListener {
            val intent : Intent = Intent(this@ApplicationActivity01,ApplicationActivity02::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.testMethod).setOnClickListener {
            // applicationContext를 그냥가져오면 안되고 우리는 MasterApplication를 쓰고싶은거니까 as로 타입을바꿔줌(타입캐스팅)
            (applicationContext as MasterApplication).methodFromApplication()
            val userId = (applicationContext as MasterApplication).userId
            Log.d("testt",""+userId)
        }


    }
}
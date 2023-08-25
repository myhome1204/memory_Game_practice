package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class ViewControl_02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_control02)
        //뷰(컴포넌트)를 activity로 가져오는 방법
        val textViewOne: TextView = findViewById(R.id.textViewOne)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        Log.d("textt", textViewOne.text.toString())
        //Listener 사용방법
        buttonOne.setOnClickListener {
            //버튼이 클릭되었을때 동작할 코드를 넣기
            Log.d("textt", "버튼클릭!!")
        }
        //축약버전(익명함수)
        buttonOne.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
                Log.d("textt", "버튼클릭!!")
            }
        })
    }
}
package com.example.fast

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class Intent02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent02)
        //Intent01에서 보내준 데이터를 꺼내서 사용하기
        //뒤에있는 보라색 intent는 Intent01에서온 intent이고 이름은 무조건 내가 Intent01에서 변수를 뭐라고설정했든간에 intent라고 쓰는거같음
        val intent = intent
        //만약 다른 액티비티파일에서도 데이터(즉 intent를 보냇다면, 예를들어 Intent03에서도 Intent02로 데이터를 보냇다면)를보내면 나를호출한 액티비티에서 보낸 intent가  intent가됨)
        //Intent01에서 데이터를보냇을때(호출했을때) Intent02가 열리고 그때 intent(보라색)는 01에서보낸 intent이고 Intent03에서 데이터를 보냇을때(호출) Intent02가얼리고 그때 intent는 03에서보낸것)
        //Intent02를 호출한 Activity의 intent이다
        val data :String? = intent.extras?.getString("extra-data")
        if(data != null){
            Log.d("dataa",data)
        }
        (findViewById<TextView>(R.id.fin)).apply{
            this.setOnClickListener{
                val intent : Intent = Intent()
                intent.putExtra("result","success")
                setResult(RESULT_OK,intent)
                finish()
            }
        }
        val imageView = findViewById<ImageView>(R.id.image)
        val uri =Uri.parse(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())
        imageView.setImageURI(uri)
    }
}
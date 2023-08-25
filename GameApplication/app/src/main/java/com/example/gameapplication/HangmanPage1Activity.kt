package com.example.gameapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView

class HangmanPage1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman_page1)
        //난이도 선택을 위해 TextView버튼을 3개 만들었다
        //난이도별 이미지리스트 선택을 위한 level과 난이도별 목숨갯수인 chance를 intent로 다음페이지에보내준다
        findViewById<TextView>(R.id.easy).setOnClickListener {
            val intent = Intent(this@HangmanPage1Activity, HangmanPage2Activity::class.java)
            intent.putExtra("level", 1)
            intent.putExtra("chance", 9)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.usually).setOnClickListener {
            val intent = Intent(this@HangmanPage1Activity, HangmanPage2Activity::class.java)
            intent.putExtra("level", 2)
            intent.putExtra("chance", 7)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.hard).setOnClickListener {
            val intent = Intent(this@HangmanPage1Activity, HangmanPage2Activity::class.java)
            intent.putExtra("level", 3)
            intent.putExtra("chance", 4)
            startActivity(intent)
        }

    }
}
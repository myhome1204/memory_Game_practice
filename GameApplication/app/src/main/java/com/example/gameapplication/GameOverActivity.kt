package com.example.gameapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        //시작하자마자 애니메이션
        overridePendingTransition(R.anim.gameover, R.anim.fade)
        val level = intent.getIntExtra("level", 0)
        val chance = intent.getIntExtra("chance", 0)
        findViewById<TextView>(R.id.retry).setOnClickListener {
            startGameAgain(level,chance)
        }
    }

    private fun startGameAgain(level : Int, chance:Int) {
        // 다시 게임을 시작하는 함수 그냥 다시 HangmanPage2Activity로 전환해주면서 필요한 intent만보내면된다
        val intent = Intent(this@GameOverActivity, HangmanPage2Activity::class.java)
        intent.putExtra("level", level)
        intent.putExtra("chance", chance)
        startActivity(intent)
        finish() // 현재 액티비티를 종료하여 뒤로가기 시 이 화면으로 돌아오지 않도록 함
    }
}
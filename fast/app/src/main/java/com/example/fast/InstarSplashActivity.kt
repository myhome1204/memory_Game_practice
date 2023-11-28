package com.example.fast

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InstarSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_splash)
        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","empty")
        when(token){
            "empty" ->{
                //로그인이 안되어있는경우
                startActivity(Intent(this@InstarSplashActivity,InstarLoginActivity::class.java))
            }
            else -> {
                //로구인이 되어있는경우
                startActivity(Intent(this@InstarSplashActivity,InstarMainActivity::class.java))
            }
        }
    }
}
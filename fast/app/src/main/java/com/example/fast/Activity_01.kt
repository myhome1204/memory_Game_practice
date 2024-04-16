package com.example.fast

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Activity_01 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_01)
        Log.d("LifeCycle","OnCreate")
        val switch = findViewById<TextView>(R.id.temm)
        val intent : Intent  = Intent(this@Activity_01,Intent02::class.java)
        switch.setOnClickListener {
            startActivity(intent)
            finish()
        }
    }
    override fun onStart() {
        Log.d("LifeCycle","onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("LifeCycle","onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("LifeCycle","onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("LifeCycle","onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("LifeCycle","onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("LifeCycle","onRestart")
        super.onRestart()
    }
}

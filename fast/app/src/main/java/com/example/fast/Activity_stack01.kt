package com.example.fast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Activity_stack01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack01)
        (findViewById<TextView>(R.id.one)).setOnClickListener {
            val intent : Intent = Intent(this@Activity_stack01,Activity_stack01::class.java)
            startActivity(intent)
        }
        (findViewById<TextView>(R.id.two)).setOnClickListener {
            val intent : Intent = Intent(this@Activity_stack01,Activity_stack02::class.java)
            startActivity(intent)
        }
        (findViewById<TextView>(R.id.three)).setOnClickListener {
            val intent : Intent = Intent(this@Activity_stack01,Activity_stack03::class.java)
            startActivity(intent)
        }
    }
}
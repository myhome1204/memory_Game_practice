package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

class ResourceActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)
        findViewById<TextView>(R.id.hh).setOnClickListener {
            (it as TextView).text = resources.getText(R.string.app_name)
            //it.background = resources.getDrawable(R.drawable.arrows)
            //it.background = resources.getDrawable(R.drawable.arrows, null) 아래코드랑 똑같은코드임
            it.background = ContextCompat.getDrawable(this,R.drawable.arrows)
            //it.background = ResourcesCompat.getDrawable(resources,R.drawable.arrows,null)
        }

    }
}
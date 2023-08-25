package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        var imageview = findViewById<ImageView>(R.id.image)
        Glide.with(this)
            .load("https://picsum.photos/id/237/200/300")
            .fitCenter()
            .into(imageview)
    }
}
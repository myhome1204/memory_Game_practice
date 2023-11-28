package com.example.fast

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


class MusicActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        findViewById<TextView>(R.id.playbtn).setOnClickListener {
            val serviceIntent = Intent(this, MusicService::class.java)
            startService(serviceIntent)
        }

    }

}



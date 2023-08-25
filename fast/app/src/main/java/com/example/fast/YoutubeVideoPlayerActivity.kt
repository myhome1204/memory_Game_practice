package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class YoutubeVideoPlayerActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video_player)
        val viedoUrl = intent.getStringExtra("video_url")
        val mediaController = MediaController(this)
        findViewById<VideoView>(R.id.youtubeVideoView).apply {
            this.setVideoPath(viedoUrl)
            this.requestFocus()
            this.start()
            mediaController.show()
        }
    }
    //Exoplayer
    //기능 다양하고 mediaController는 어렵고 별로임
    //사용이쉽다
    //DRM
    //Streaming
}
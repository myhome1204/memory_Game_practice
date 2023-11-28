package com.example.fast

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {
    private var mediaPlayer : MediaPlayer? = null
    override fun onCreate() {
        super.onCreate()
        Log.d("MusicService", "Service created")
        mediaPlayer = MediaPlayer.create(this, R.raw.asd)
        Log.d("MusicService",mediaPlayer.toString())
        mediaPlayer?.isLooping = false // 반복 재생 여부 설정
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mediaPlayer?.isPlaying == false) {
            Log.d("MusicService", "Service started")
            mediaPlayer?.start()
        }
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
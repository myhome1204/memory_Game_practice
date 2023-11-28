package com.example.gameapplication

import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MusicViewModel() : ViewModel() {
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private val isPlayingLiveData = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> get() = isPlayingLiveData
    init {
        mediaPlayer.setOnCompletionListener {
            isPlayingLiveData.postValue(false)
        }
    }
    fun playMusic(musicFilePath : String){
        mediaPlayer.reset()
        mediaPlayer.setDataSource(musicFilePath)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }
    fun pauseMusic(){
        //일시정지임 다시 시작가능
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlayingLiveData.postValue(false)
        }
    }
    fun restartMusic() {
        mediaPlayer.start()
    }
}
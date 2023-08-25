package com.example.fast

import android.content.Intent
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

class MelonDetailActivity : AppCompatActivity() {

    lateinit var playPauseButton: ImageView
    lateinit var mediaPlayer: MediaPlayer

    var is_playing: Boolean = true
        set(value) {
            if (value == true) {
                playPauseButton.setImageDrawable(
                    resources.getDrawable(
                        R.drawable.pause,
                        this.theme
                    )
                )
            } else {
                playPauseButton.setImageDrawable(resources.getDrawable(R.drawable.play, this.theme))
            }
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon_detail)
        playPauseButton = findViewById(R.id.play)
        var melonItemList = intent.getSerializableExtra("melon_item_list") as? ArrayList<MelonItem>
        var position = intent.getIntExtra("position", 0)

        if (melonItemList != null) {
            playMelon(melonItemList.get(position))
            changethumbnail(melonItemList.get(position))
        }
        findViewById<ImageView>(R.id.play).setOnClickListener {
            if (is_playing == false) {
                is_playing = true
                if (melonItemList != null) {
                    playMelon(melonItemList.get(position))
                }

            } else {
                is_playing = false
                mediaPlayer.stop()
            }
        }
        findViewById<ImageView>(R.id.back).setOnClickListener {
            mediaPlayer.stop()
            if (melonItemList != null) {
                position--
                if (position < 0) {
                    position = melonItemList.size + position
                }
                playMelon(melonItemList.get(position))
                changethumbnail(melonItemList.get(position))
            }
        }
        findViewById<ImageView>(R.id.next).setOnClickListener {
            mediaPlayer.stop()
            if (melonItemList != null) {
                position++
                position %= melonItemList.size
                playMelon(melonItemList.get(position))
                changethumbnail(melonItemList.get(position))
            }
        }
    }
    fun playMelon(melonItem: MelonItem) {
        mediaPlayer = MediaPlayer.create(
            this,
            Uri.parse(melonItem.song)
        )
        mediaPlayer.start()
    }
    fun changethumbnail(melonItem: MelonItem) {
        findViewById<ImageView>(R.id.thumnail).apply {
            val glide = Glide.with(this@MelonDetailActivity)
            glide.load(melonItem.thumbnail).into(this)
        }
    }
    //exoplayer 사용하면 더좋음
}



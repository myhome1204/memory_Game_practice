package com.example.fast;

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log
import android.widget.TextView;
import org.w3c.dom.Text
import java.io.IOException

class MVPExampleActivity : AppCompatActivity(),MyView {
    private lateinit var presenter: MyPresenter
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvpexample)
        try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource("/C:/Users/myhom/Desktop/asd.mp3")
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: IOException) {
            Log.e("MyApp", "예외 발생: ${e.message}")
            e.printStackTrace()
        }
//        presenter = MyPresenter()
//        presenter.attachView(this)
//        val btn = findViewById<TextView>(R.id.MVP_btn)
//        btn.setOnClickListener {
//            presenter.OnButtonClick()
//        }

    }
    override fun showData(data: String) {
        val text = findViewById<TextView>(R.id.MVP_Text)
        text.text = data
    }

}
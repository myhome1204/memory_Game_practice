package com.example.fast

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

    }
}
//class BackgroundAsyncTask(
//    val progressbar : ProgressBar,
//    val progressText : TextView
//)://AsyncTask<Int,Int,Int>()
//        //deprecated > 더 이상 사용을 권장하지않는다
//        //코루틴 > 멀티테스킹,동기/비동기처리
//        //Parms,Progress,Result
//        //Parms - doInBackground에서 사용할 타입
//        //Progress - OnprogressUpdate에서 사용할 타입
//        //Result - OnPostExcute에서 사용할 타입
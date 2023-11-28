package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuspendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suspend)
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("testt","b")
            test()
            Log.d("testt","d")
        }
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("testt","b2")
            test()
            Log.d("testt","d2")
        }
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("testt","b3")

            Log.d("testt","d3")
        }
    }
}
suspend fun test(){
    Log.d("testt","c")
    delay(5000)
    Log.d("testt","c2")
}
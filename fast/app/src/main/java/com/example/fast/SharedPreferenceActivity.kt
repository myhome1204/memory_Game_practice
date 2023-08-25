package com.example.fast

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SharedPreferenceActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)
        //sharedPreference사용법
        findViewById<TextView>(R.id.create).setOnClickListener {
            //Create과정
            //아래에있는 name은 table의이름 느낌이다
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            //MODE_PRIVATE -> 현재 앱에서만 사용하겟다 >> 다른 앱과 데이터를 공유하지않겟다
            //데이터도 sharedPreference에 바로저장할수 없다 editor을 얻어와야한다
            //데이터의 변경을 가할때는 editor로 해야만한다
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key1","hello1")
            editor.putString("key2","hello2")
            editor.commit()
        }
        findViewById<TextView>(R.id.read).setOnClickListener {
            //Read
            //데이터를 만들었으니 이제 읽기
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            //defValue는 키값이 틀렷을 때 나올값
            val valueOne = sharedPreferences.getString("key1","Wrong")
            val valueTwo = sharedPreferences.getString("key2","Wrong")
            Log.d("testt",""+valueOne)
            Log.d("testt",""+valueTwo)
        }
        findViewById<TextView>(R.id.update).setOnClickListener {
            //update
            //덮어쓰기
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key1","hello hello")//기존에 사용했던 키와 동일한 키를 사용해서 데이터를 덮어쓰기(저장)
            editor.commit()
        }
        findViewById<TextView>(R.id.delete).setOnClickListener {
            //delete
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.commit()
        }
    }
}
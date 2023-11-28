package com.example.fast

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstarJoinActivity : AppCompatActivity() {
    var username = ""
    var userpassword1 = ""
    var userpassword2 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            //서버와 통신을할ㄸ ㅐ중간에 시리얼라이져 작업을하는데 그떄 마지막 최종값을 무엇으로 받을건지 ()안에 넣어줘야한다
            //JSON형태를 원하는데 그작업을 해주는친구가 Gson이다 GsonConverterFactory를 적어야한다
            //서버 ->읽을수 없는 데이터 -> JSON -> Gson
            //Gson > 읽을수 없는데이터를 코틀린 객체로 바꿔준다
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        findViewById<TextView>(R.id.instarlogin).setOnClickListener {
            val intent = Intent(this@InstarJoinActivity, InstarLoginActivity::class.java)
            startActivity(intent)
        }
        findViewById<EditText>(R.id.id_input).doAfterTextChanged {
            username = it.toString()
        }
        findViewById<EditText>(R.id.pwd_input1).doAfterTextChanged {
            userpassword1 = it.toString()
        }
        findViewById<EditText>(R.id.pwd_input2).doAfterTextChanged {
            userpassword2 = it.toString()
        }
        findViewById<TextView>(R.id.join_btn).setOnClickListener {
            val user = HashMap<String, Any>()
            user.put("username", username)
            user.put("password1", userpassword1)
            user.put("password2", userpassword2)
            retrofitService.instarJoin(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    if (user != null) {
                        val sharedPreferences =
                            getSharedPreferences("user_info", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("token", user.token)
                        editor.putString("user_id", user.id.toString())
                        editor.commit()
                        startActivity(Intent(this@InstarJoinActivity,InstarLoginActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("testt", "${t.message}")
                }
            })
        }
    }
}
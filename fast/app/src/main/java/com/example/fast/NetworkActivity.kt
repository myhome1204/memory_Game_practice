package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService  = retrofit.create(RetrofitService::class.java)
        retrofitService.getStudentList().enqueue(object : Callback<ArrayList<StudentFromService>>{
            override fun onResponse(
                call: Call<ArrayList<StudentFromService>>,
                response: Response<ArrayList<StudentFromService>>
            ) {
                if(response.isSuccessful){
                    val studentList = response.body()
                    studentList!!.forEach {
                        Log.d("testt", ""+it.name)
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<StudentFromService>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        findViewById<TextView>(R.id.cratestudent).setOnClickListener {
            val student = HashMap<String ,Any>()
            student.put("name", "단백질")
            student.put("age",100)
            student.put("intro","식물성")
            retrofitService.createStudent(student).enqueue(object : Callback<StudentFromService>{
                override fun onResponse(
                    call: Call<StudentFromService>,
                    response: Response<StudentFromService>
                ) {
                    if(response.isSuccessful){
                        val student = response.body()
                        Log.d("testt","등록한 학생"+student!!.name)
                    }
                }
                override fun onFailure(call: Call<StudentFromService>, t: Throwable) {
                    Log.d("testt","실패")
                }
            })
        }
        findViewById<TextView>(R.id.cratestudenteasyversion).setOnClickListener {
            val student = StudentFromService(name = "서울", age = 200, intro = "well come to seoul")
            retrofitService.easyCreateStudent(student)
                .enqueue(object : Callback<StudentFromService> {
                    override fun onResponse(
                        call: Call<StudentFromService>,
                        response: Response<StudentFromService>
                    ) {
                        if (response.isSuccessful) {
                            val student = response.body()
                            Log.d("testt", "등록한 학생" + student!!.name)
                        }
                    }
                    override fun onFailure(call: Call<StudentFromService>, t: Throwable) {
                        Log.d("testt", "실패")
                    }
                })
        }
    }


}
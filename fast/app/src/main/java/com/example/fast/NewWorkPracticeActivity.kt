package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewWorkPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_work_practice)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            //서버와 통신을할ㄸ ㅐ중간에 시리얼라이져 작업을하는데 그떄 마지막 최종값을 무엇으로 받을건지 ()안에 넣어줘야한다
            //JSON형태를 원하는데 그작업을 해주는친구가 Gson이다 GsonConverterFactory를 적어야한다
            //서버 ->읽을수 없는 데이터 -> JSON -> Gson
            //Gson > 읽을수 없는데이터를 코틀린 객체로 바꿔준다
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getStudentList().enqueue(object : Callback<ArrayList<StudentFromService>>{
            override fun onResponse(
                call: Call<ArrayList<StudentFromService>>,
                response: Response<ArrayList<StudentFromService>>
            ) {

                val response_studentlist = response.body()
                if (response_studentlist != null) {
                    findViewById<RecyclerView>(R.id.studentrecycleview).apply {
                        this.adapter = StudentReCyclerViewAdapter(
                            response_studentlist,
                            LayoutInflater.from(this@NewWorkPracticeActivity)
                        )
                        this.layoutManager = LinearLayoutManager(this@NewWorkPracticeActivity)
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<StudentFromService>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}
class StudentReCyclerViewAdapter(
    var studentlist : ArrayList<StudentFromService>,
    var inflater : LayoutInflater
): RecyclerView.Adapter<StudentReCyclerViewAdapter.StudentHolder>(){
    class StudentHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var studentName : TextView
        var studentAge : TextView
        init {
            studentName = itemView.findViewById(R.id.studentname)
            studentAge = itemView.findViewById(R.id.studentage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = inflater.inflate(R.layout.studentsexample,parent,false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.studentAge.text = studentlist.get(position).age.toString()
        holder.studentName.text = studentlist.get(position).name
    }

    override fun getItemCount(): Int {
       return studentlist.size
    }
}
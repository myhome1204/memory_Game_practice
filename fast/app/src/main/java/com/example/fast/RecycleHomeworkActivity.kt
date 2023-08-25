package com.example.fast

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleHomeworkActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_homework)
        val communication1 = mutableListOf<commu>()
        val communication2 = mutableListOf<commu>()
        communication1.add(commu("안녕하세요",true))
        communication1.add(commu("네안녕하세요",false))
        communication1.add(commu("반가워요",true))
        communication1.add(commu("네반가워요요",false))
        communication1.add(commu("안녕히계세요",true))
        communication1.add(commu("네안녕히계세요",false))
        val recyclerview1 = findViewById<RecyclerView>(R.id.recycleview2)
//        val recyclerview2 = findViewById<RecyclerView>(R.id.recycleview3)
        val adapter1 = RecycleHomeworkAdapter(communication1, LayoutInflater.from(this), context = this)
//        val adapter2 = RecycleHomeworkAdapter(communication2, LayoutInflater.from(this), context = this)
        recyclerview1.adapter=adapter1
//        recyclerview2.adapter=adapter2
        recyclerview1.layoutManager =LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

//        recyclerview2.layoutManager = LinearLayoutManager(this)
    }
}

class commu(
    val quest: String,
    val check : Boolean
)
class RecycleHomeworkAdapter(
    val commulist: MutableList<commu>,
    val inflater: LayoutInflater,
    val context : Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class leftRecycleHomeworkHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val leftchatimage: ImageView
        val leftcontext1: TextView
        init{
            leftchatimage = itemView.findViewById(R.id.leftchatimage)
            leftcontext1 = itemView.findViewById(R.id.leftcontext1)
        }
    }
    inner class rightRecycleHomeworkHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rightchatimage: ImageView
        val rightleftcontext1: TextView
        init{
            rightchatimage = itemView.findViewById(R.id.rightchatimage)
            rightleftcontext1 = itemView.findViewById(R.id.rightcontext1)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //얘는 뷰홀더를 실제로 만드는 메소드
        val layoutRes = if (viewType == 1) R.layout.homeworkitem1 else R.layout.homeworkitem2
        val view = inflater.inflate(layoutRes,parent,false)
        if (viewType == 1){

            return leftRecycleHomeworkHolder(view)
        }else{

            return rightRecycleHomeworkHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val commu = commulist.get(position)
        if(commu.check){
            (holder as leftRecycleHomeworkHolder).leftcontext1.text = commulist.get(position).quest
            holder.leftchatimage.setImageDrawable(context.resources.getDrawable(R.drawable.bluebackground,context.theme))
        }else{
            (holder as rightRecycleHomeworkHolder).rightleftcontext1.text = commulist.get(position).quest
            holder.rightchatimage.setImageDrawable(context.resources.getDrawable(R.drawable.bluebackground,context.theme))
        }

    }

    override fun getItemCount(): Int {
        return commulist.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position%2 == 0){
            return 1
        }else{
            return 2
        }
    }
}
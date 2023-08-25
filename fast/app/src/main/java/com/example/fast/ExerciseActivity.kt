package com.example.fast

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        val namelist = mutableListOf<EName>()
        namelist.add(EName("bench press"))
        namelist.add(EName("dumbbell fly"))
        namelist.add(EName("flat dumbbell"))
        namelist.add(EName("incline bench press"))
        namelist.add(EName("incline dumbbell"))
        namelist.add(EName("declinebench press"))
        namelist.add(EName("cable fly"))
        val recyclerview = findViewById<RecyclerView>(R.id.exerrecycleview)
        val adapter = ExRecycleViewAdapter(namelist, LayoutInflater.from(this), context = this)
        recyclerview.adapter=adapter
        recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }
}
class EName(
    val name :String
)
class ExRecycleViewAdapter(
    var enamelist: MutableList<EName>,
    var inflater: LayoutInflater,
    var context: Context
) : RecyclerView.Adapter<ExRecycleViewAdapter.ExReViewHolder>() {
    //아래 이너클래스 도 상속해준거임 RecyclerView의 뷰홀더를 상속해야만 뷰홀더역할이됨
    inner class ExReViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //아이템뷰의 상세 뷰 컴포넌트를 홀드한다
        //inner클래스는 outer클래스 의 값들에 접근할수있다 그래서 inner클래스로 정의를해줘야함
        val imageList : List<Int> = listOf(
            R.drawable.chest1,
            R.drawable.chest2,
            R.drawable.chest3,
            R.drawable.chest4,
            R.drawable.chest5,
            R.drawable.chest6,
            R.drawable.chest7,
        )
        val exerimage : ImageView
        val exertext : TextView
        init{
            exerimage = itemView.findViewById(R.id.exerimage)
            exertext = itemView.findViewById(R.id.exertext)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExReViewHolder {
        //아이템뷰를 리턴
        val view = inflater.inflate(R.layout.exeritem,parent,false)
        return ExReViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExReViewHolder, position: Int) {

        val image = holder.imageList[position]
        holder.exerimage.setImageResource(image)
        holder.exertext.text = enamelist.get(position).name

    }
    override fun getItemCount(): Int {
        return enamelist.size
    }
}
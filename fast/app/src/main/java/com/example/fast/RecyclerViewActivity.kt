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

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val carlist = mutableListOf<Car>()
        for (i in 0..100) {
            carlist.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }
        val recyclerView  =findViewById<RecyclerView>(R.id.recycleview)
        recyclerView.adapter = RecycleViewAdapter(carlist, LayoutInflater.from(this), context = this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //세로가로 조정등등 레이아웃매니저로 조정가능함 linear매니저가 세로방향임
//        recyclerView.layoutManager = GridLayoutManager(this,3)
        //이상하게 recyclerView에는 setOnItemClickListener가없다 그래서 홀더부분에서 직접달아줘야함
    }
}
//recycler뷰는 뷰 홀더를  리사이클러뷰 안쪽에 넣어준다 리사이클러뷰를 상속받을때 그안에서사용할 뷰홀더의 타입을 알려줘야함
//뷰홀더사용이 강제된다
class RecycleViewAdapter(
    var carlist: MutableList<Car>,
    var inflater: LayoutInflater,
    var context: Context
) : RecyclerView.Adapter<RecycleViewAdapter.ReViewHolder>() {
    //아래 이너클래스 도 상속해준거임 RecyclerView의 뷰홀더를 상속해야만 뷰홀더역할이됨
    inner class ReViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //아이템뷰의 상세 뷰 컴포넌트를 홀드한다
        //inner클래스는 outer클래스 의 값들에 접근할수있다 그래서 inner클래스로 정의를해줘야함
        val carImage : ImageView
        val nthCar : TextView
        val nthEngine : TextView
        init{
            carImage = itemView.findViewById(R.id.carImage)
            nthCar = itemView.findViewById(R.id.nthCar)
            nthEngine = itemView.findViewById(R.id.nthEngine)
            carImage.setOnClickListener{
                val position : Int  = adapterPosition
                var car = carlist.get(position)
                Log.d("testt",""+car.nthCar)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReViewHolder {
        //아이템뷰를 리턴
        val view = inflater.inflate(R.layout.car_item,parent,false)
        return ReViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReViewHolder, position: Int) {
        //데이터를 아이템뷰의 뷰 컴포넌트와 묶는다(뷰를 채워준다)
        holder.carImage.setImageDrawable(context.resources.getDrawable(R.drawable.bluebackground,context.theme))
        holder.nthCar.text = carlist.get(position).nthCar
        holder.nthEngine.text = carlist.get(position).nthEngine

    }
    override fun getItemCount(): Int {
        return carlist.size
    }
}
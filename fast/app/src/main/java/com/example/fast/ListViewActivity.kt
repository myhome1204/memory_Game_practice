package com.example.fast

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.newFixedThreadPoolContext

class ListViewActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view3)
        var carList = mutableListOf<Car>()
        for(i in 0..100){
            carList.add(Car(""+i+"번쨰 자동차",""+i+"번째 엔진"))
        }
        val adapter = ListViewAdapter(carList, LayoutInflater.from(this),this)
        val listview = findViewById<ListView>(R.id.listview)
        listview.adapter = adapter
        //리스너장착방법
        listview.setOnItemClickListener { parent, view, position, id ->
            val car = adapter.carlist.get(position)
            val nthcar = car.nthCar
            val nthengine = car.nthEngine
            Toast.makeText(
                this,nthcar+" "+ nthengine,Toast.LENGTH_LONG
            ).show()
        }
        //데이터 갱신방법
        findViewById<TextView>(R.id.addCar).setOnClickListener {
            adapter.carlist.add(
                Car("안녕나는차야","안녕나는엔진")
            )
            adapter.notifyDataSetChanged()
        }
    }
}
class ListViewAdapter(
    val carlist : MutableList<Car>,
    val layoutInflater: LayoutInflater,
    val context : Context
):BaseAdapter(){
    override fun getCount(): Int {
        //전체데이터의 크기(개수)를 리턴
        return carlist.size
    }
    override fun getItem(position: Int): Any {
       //전체 데이터 중에서 해당번째(position)의 데이터를 리턴
        return carlist.get(position)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : CarViewHolder
        if(convertView == null){
            //재활용 불가능함
            view = layoutInflater.inflate(R.layout.car_item,null)
            holder = CarViewHolder()
            holder.carImage = view.findViewById<ImageView>(R.id.carImage)
            holder.nthCar = view.findViewById<TextView>(R.id.nthCar)
            holder.nthEngine = view.findViewById<TextView>(R.id.nthEngine)
            //뷰에다가 태그를 걸어두면 재활용될때 convertView로 들어오고 거기서 tag를 꺼낼수있음 그리고 우리가만들엇던타입으로 바꿀수있음
            view.tag = holder
        }else{//재활용가능
            holder = convertView.tag as CarViewHolder
            view = convertView
        }
        val car = carlist.get(position)
        holder.carImage?.setImageDrawable(context.resources.getDrawable(R.drawable.bluebackground,context.theme))
        holder.nthCar?.text = car.nthCar
        holder.nthEngine?.text = car.nthEngine
//        val view = layoutInflater.inflate(R.layout.car_item,null)
//        val carimage = view.findViewById<ImageView>(R.id.carImage)
//        val nthcar = view.findViewById<TextView>(R.id.nthCar)
//        val nthengine = view.findViewById<TextView>(R.id.nthEngine)
//        val car = carlist.get(position)
//        carimage.setImageDrawable(context.resources.getDrawable(R.drawable.bluebackground,context.theme))
//        nthcar.text = car.nthCar
//        nthengine.text = car.nthEngine
//        return view
        return view
    }
}
class CarViewHolder{
    var carImage : ImageView? = null
    var nthCar : TextView? = null
    var nthEngine : TextView? = null
}
package com.example.fast

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            //서버와 통신을할ㄸ ㅐ중간에 시리얼라이져 작업을하는데 그떄 마지막 최종값을 무엇으로 받을건지 ()안에 넣어줘야한다
            //JSON형태를 원하는데 그작업을 해주는친구가 Gson이다 GsonConverterFactory를 적어야한다
            //서버 ->읽을수 없는 데이터 -> JSON -> Gson
            //Gson > 읽을수 없는데이터를 코틀린 객체로 바꿔준다
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getMelonItemList().enqueue(object : Callback<ArrayList<MelonItem>> {
            override fun onResponse(
                call: Call<ArrayList<MelonItem>>,
                response: Response<ArrayList<MelonItem>>
            ) {
                val MelonItemList = response.body()
                val glide = Glide.with(this@MelonActivity)
                if (MelonItemList != null) {
                    findViewById<RecyclerView>(R.id.melon_list_view).apply {
                        this.adapter =
                            MelonAdapter(
                                MelonItemList,
                                LayoutInflater.from(this@MelonActivity),
                                glide,
                                context = this@MelonActivity
                            )
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<MelonItem>>, t: Throwable) {
                Log.d("testt", "Error: ${t.message}")
            }
        })
    }
}

class MelonAdapter(
    val MelonItemList: ArrayList<MelonItem>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val context: Context
) : RecyclerView.Adapter<MelonAdapter.MelonHolder>() {
    inner class MelonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView
        val title: TextView
        val play: ImageView

        init {
            thumbnail = itemView.findViewById(R.id.thumnail)
            title = itemView.findViewById(R.id.title)
            play = itemView.findViewById(R.id.play)
            play.setOnClickListener {
                val intent = Intent(context, MelonDetailActivity::class.java)
                //디테일 엑티비티로 넘어가서 음악재생을할때 이전곡 다음곡 버튼이 있어서 디테일엑티비티에도
                // MelonItemList 가 있어야함 하지만 객체를 intent로 넘길수없으니 객체를 분해해서 보내고
                //받는액티비티에서 다시객체로 합쳐야하는작업을 해야한다
                //그 작업을 직접할수도있지만 serialize가 대신해준다
                //방법은 MelonItem class에 가서 뒤에 :Serializable를해준다
                intent.putExtra("melon_item_list",MelonItemList as Serializable)
                intent.putExtra("position",adapterPosition)
                //보낼때 Serializable로 분해해서 보냇으면 받을때 Serializable로 다시 합체해야한다
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MelonHolder {
        val view = inflater.inflate(R.layout.melonitem, parent, false)
        return MelonHolder(view)
    }

    override fun onBindViewHolder(holder: MelonHolder, position: Int) {
        holder.title.text = MelonItemList.get(position).title
        glide.load((MelonItemList.get(position).thumbnail)).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return MelonItemList.size
    }
}
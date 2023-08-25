package com.example.fast

import android.app.DownloadManager.Request
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            //서버와 통신을할ㄸ ㅐ중간에 시리얼라이져 작업을하는데 그떄 마지막 최종값을 무엇으로 받을건지 ()안에 넣어줘야한다
            //JSON형태를 원하는데 그작업을 해주는친구가 Gson이다 GsonConverterFactory를 적어야한다
            //서버 ->읽을수 없는 데이터 -> JSON -> Gson
            //Gson > 읽을수 없는데이터를 코틀린 객체로 바꿔준다
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getYoutubeItemList().enqueue(object : Callback<ArrayList<YoutubeItem>> {
            override fun onResponse(
                call: Call<ArrayList<YoutubeItem>>,
                response: Response<ArrayList<YoutubeItem>>
            ) {
                val youtubeList = response.body()
                val glide = Glide.with(this@YoutubeActivity)
                if (youtubeList != null) {
                    val adapter = YoutubeAdapter(
                        youtubeList,
                        LayoutInflater.from(this@YoutubeActivity),
                        glide,
                        context = this@YoutubeActivity
                    )
                    findViewById<RecyclerView>(R.id.youtuberecycleview).apply {
                        this.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<YoutubeItem>>, t: Throwable) {
                Log.d("Retrofit", "Error: ${t.message}")
            }
        })
    }
}
class YoutubeAdapter(
    val youtubeItemList: ArrayList<YoutubeItem>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val context: Context
) : RecyclerView.Adapter<YoutubeAdapter.youtubeholder>() {
    inner class youtubeholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumnail: ImageView
        val content: TextView

        init {
            title = itemView.findViewById(R.id.title)
            thumnail = itemView.findViewById(R.id.thumnail)
            content = itemView.findViewById(R.id.content)
            itemView.setOnClickListener{
                val intent = Intent(context,YoutubeVideoPlayerActivity::class.java)
                intent.putExtra("video_url",youtubeItemList.get(adapterPosition).video)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): youtubeholder {
        val view = inflater.inflate(R.layout.youtubeitem, parent, false)
        return youtubeholder(view)
    }

    override fun onBindViewHolder(holder: youtubeholder, position: Int) {
        holder.title.text = youtubeItemList.get(position).title
        holder.content.text = youtubeItemList.get(position).content
        glide.load((youtubeItemList.get(position).thumbnail)).centerCrop().into(holder.thumnail)
    }

    override fun getItemCount(): Int {
        return youtubeItemList.size
    }
}
package com.example.fast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstarFeedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.instar_feed_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            //서버와 통신을할ㄸ ㅐ중간에 시리얼라이져 작업을하는데 그떄 마지막 최종값을 무엇으로 받을건지 ()안에 넣어줘야한다
            //JSON형태를 원하는데 그작업을 해주는친구가 Gson이다 GsonConverterFactory를 적어야한다
            //서버 ->읽을수 없는 데이터 -> JSON -> Gson
            //Gson > 읽을수 없는데이터를 코틀린 객체로 바꿔준다
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        val FeedListView = view.findViewById<RecyclerView>(R.id.feedlist)
        retrofitService.getInstargramPosts().enqueue(object : Callback<ArrayList<InstarPost>> {
            override fun onResponse(
                call: Call<ArrayList<InstarPost>>,
                response: Response<ArrayList<InstarPost>>
            ) {

                val postList = response.body()
                if (postList != null) {
                    FeedListView.adapter = PostRecyclerViewAdapter(
                        postList, LayoutInflater.from(activity), Glide.with(activity!!)
                    )
                }
            }

            override fun onFailure(call: Call<ArrayList<InstarPost>>, t: Throwable) {

            }
        })
    }
}

class PostRecyclerViewAdapter(
    val postlist: ArrayList<InstarPost>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<PostRecyclerViewAdapter.postviewholder>() {
    inner class postviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val owner_img: ImageView
        val owner_username: TextView
        val post_img: ImageView
        val postContent: TextView

        init {
            owner_img = itemView.findViewById(R.id.profile_img)
            owner_username = itemView.findViewById(R.id.profile_username)
            post_img = itemView.findViewById(R.id.post_img)
            postContent = itemView.findViewById(R.id.post_content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postviewholder {
        return postviewholder(inflater.inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: postviewholder, position: Int) {
        val InstarPost = postlist.get(position)

        glide.load(InstarPost.image).centerCrop().into(holder.post_img)
        InstarPost.owner_profile.image?.let{
            glide.load(InstarPost.owner_profile.image).centerCrop().into(holder.owner_img)
        }
        holder.owner_username.text = InstarPost.owner_profile.username
        holder.postContent.text = InstarPost.content
    }

    override fun getItemCount(): Int {
        return postlist.size
    }
}

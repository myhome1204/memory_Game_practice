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
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import org.w3c.dom.Text

class Practicectivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practicectivity)
        val Exerlist = mutableListOf<Exercise>()
        for(i in 1..100){
            Exerlist.add(Exercise("" + i+"일차","근력 + "+i))
        }
        val listview = findViewById<ListView>(R.id.container5)
        val inflater  = LayoutInflater.from(this@Practicectivity)
        val adapter = autoadd(Exerlist,inflater,this)
        listview.adapter = adapter
    }
}
class Exercise(
    var dlfck: String,
    var rmsfur : String
        )
class autoadd(
    val exerlist : MutableList<Exercise>,
    val infl : LayoutInflater,
    val c : Context
):BaseAdapter(
) {
    override fun getCount(): Int {
        return exerlist.size
    }
    override fun getItem(position: Int): Any {
        return exerlist.get(position)
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : com.example.fast.Viewholder
        if (convertView == null){
            view = infl.inflate(R.layout.practice,null)
            holder = Viewholder()
            holder.exerImage = view.findViewById<ImageView>(R.id.eximage)
            holder.exerText1 = view.findViewById<TextView>(R.id.extext1)
            holder.exerText2 = view.findViewById<TextView>(R.id.extext2)
            view.tag = holder
        }else{
            holder = convertView.tag as Viewholder
            view = convertView
        }
        val e = exerlist.get(position)
        holder.exerImage?.setImageDrawable(c.resources.getDrawable(R.drawable.bluebackground,c.theme))
        holder.exerText1?.text = e.dlfck
        holder.exerText2?.text = e.rmsfur
        return view
    }
}
class Viewholder {
    var exerImage: ImageView? = null
    var exerText1: TextView? = null
    var exerText2: TextView? = null
}

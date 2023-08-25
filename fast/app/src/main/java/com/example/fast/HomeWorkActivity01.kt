package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class HomeWorkActivity01 : AppCompatActivity() {
    //lateinit 은 초기화를 미뤄줌

    lateinit var one : TextView
    lateinit var two : TextView
    lateinit var three : TextView
    lateinit var four : TextView
    lateinit var five : TextView
    lateinit var six : TextView
    lateinit var seven : TextView
    lateinit var eight : TextView
    lateinit var nine : TextView
    lateinit var zero : TextView
    lateinit var plus : TextView
    lateinit var equal : TextView
    lateinit var ca : TextView
    lateinit var output : TextView
    var input : String = ""
    var temp : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_work01)
        findViews()
        setNumberTextViewListener()
        ca.setOnClickListener{
            input = ""
            temp = ""
            output.text = "0"
        }
        plus.setOnClickListener {
            temp = output.text.toString()
            output.text = ""
            input= ""
        }
        equal.setOnClickListener {
            val result : String = (input.toInt()+temp.toInt()).toString()
            output.text = result
            temp = result
        }

    }
    fun findViews(){
        one = findViewById(R.id.text1)
        two = findViewById(R.id.text2)
        three = findViewById(R.id.text3)
        four = findViewById(R.id.text4)
        five = findViewById(R.id.text5)
        six = findViewById(R.id.text6)
        seven = findViewById(R.id.text7)
        eight = findViewById(R.id.text8)
        nine = findViewById(R.id.text9)
        zero = findViewById(R.id.text0)
        plus = findViewById(R.id.textplus)
        equal = findViewById(R.id.eq)
        ca = findViewById(R.id.textca)
        output = findViewById(R.id.output)

    }
    fun setNumberTextViewListener(){
        var array1 :Array<TextView> = arrayOf(zero,one,two,three,four,five,six,seven,eight,nine)
        for(i in 0..9 step(1)){
            array1[i].setOnClickListener{
                input += (it as TextView).text
                output.text = input
            }
        }
    }
}
package com.example.fast

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MVPFragment :Fragment(),MyView {
    private lateinit var presenter: MyPresenter
    private var Fbtn: TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflater : xml을 화면에 사용할 준비를 한다 (xml을 View로 만들어준다)
        //container : 프라그먼트에서 사용될 xml이 부모뷰(모든뷰는 부모나자식이여야해서 container를 부모로설정한것)
        //attachToRoot : 루트뷰에 붙일지말지를 말하는게아님 언제붙일거냐를물어보는거 true면 바로붙음
        val view =  inflater.inflate(R.layout.mvpfragmentexample,container,false)
//        (view.findViewById<TextView>(R.id.call_activity)).setOnClickListener {
//            view.findViewById<TextView>(R.id.call_activity).text = "ASd"
//            //fragment에서 activity의 함수를 쓸수잇다
//            //반대도가능
//            (activity as FragmentActivity).printTestLog()
//        }
        Fbtn = view?.findViewById(R.id.bbbtn)
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MyPresenter()
        presenter.attachView(this)


//        val data : String? = arguments?.getString("key")
//        if (data != null) {
//            Log.d("testt",data)
//        }
    }
//    fun printTestLog(){
//        Log.d("testt","print test log from fragment")
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Fbtn?.setOnClickListener {
            Log.d("testt","클릭은됨")
            presenter.OnButtonClick()
        }
    }
    override fun showData(data: String) {
        val text = view?.findViewById<TextView>(R.id.frgtext)
        text?.text = data
    }

}
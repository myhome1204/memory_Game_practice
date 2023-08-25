package com.example.fast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import org.w3c.dom.Text

class Fragment_First :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflater : xml을 화면에 사용할 준비를 한다 (xml을 View로 만들어준다)
        //container : 프라그먼트에서 사용될 xml이 부모뷰(모든뷰는 부모나자식이여야해서 container를 부모로설정한것)
        //attachToRoot : 루트뷰에 붙일지말지를 말하는게아님 언제붙일거냐를물어보는거 true면 바로붙음
        val view =  inflater.inflate(R.layout.first_fragment,container,false)
        (view.findViewById<TextView>(R.id.call_activity)).setOnClickListener {
            view.findViewById<TextView>(R.id.call_activity).text = "ASd"
            //fragment에서 activity의 함수를 쓸수잇다
            //반대도가능
            (activity as FragmentActivity).printTestLog()
        }
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data : String? = arguments?.getString("key")
        if (data != null) {
            Log.d("testt",data)
        }
    }
    fun printTestLog(){
        Log.d("testt","print test log from fragment")
    }

}
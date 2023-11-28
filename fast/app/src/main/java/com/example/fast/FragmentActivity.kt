package com.example.fast

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class FragmentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

//        val fragmentManager = supportFragmentManager
//        val fragmentFirst = Fragment_First()
//        //Transaction
//        //작업의단위 >>시작과 끝이 있다
//        //  A,B,C,D 작업을 언제할까??
//        //코드로 fragment만든느것도 일단 class만들고 fragment.xml만드는것까지는 똑같음
//        //내가붙이고싶은 Activity에 붙이는방법이 2개인거임 코드로 붙이냐 xml에서 붙이냐 그차이 재사용해서 사용한것임
//        (findViewById<TextView>(R.id.add)).setOnClickListener {
//            val transcation = fragmentManager.beginTransaction()//시작
//            //프래그먼트에 데이터를 전달 하는 방법
//            //번들객체를만들고 번들에 원하는 데이터를만들고 번들을 프라그먼트에 장착
//            val bundle = Bundle()
//            bundle.putString("key","hello")
//            fragmentFirst.arguments = bundle
//
//            transcation.replace(R.id.root,fragmentFirst,"fragment_first_tag")
//            transcation.commit()//끝
//        }
//        (findViewById<TextView>(R.id.remove)).setOnClickListener {
//            val transaction = fragmentManager.beginTransaction()
//            transaction.remove(fragmentFirst)
//            transaction.commit()
//        }
//        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener {
//            //xml로붙인 fragment를찾는방법
////            val fragmentFirst = supportFragmentManager.findFragmentById(R.id.fragment_first) as Fragment_First
////            fragmentFirst.printTestLog()
//            val fragmentFirst =supportFragmentManager.findFragmentByTag("fragment_first_tag") as Fragment_First
////            fragmentFirst.printTestLog()
//        }
        //commit
        //1> commit
        //2> commitAllowingStateLoss
        //3> commitNow
        //4> commitNowAllowingStatLoss
        //commit - commitAllowingStateLoss
        // - 상태손실을 허락한다
        // -상태손실 ?? >>
        // -onStop ,lifeCycle 또는 os에 의해서 앱이 상태를 저장해야 할 수잇다
        // -commit : 저장을 한 경우 실행을 할 수 없다(IllegalStateException)
        // -commitAllowigStateLoss : 저장을 한 경우 예외가 발생하지않고 그냥 손실S
        //commit  - commitNow
        //-commit >작업을 예약한다 (main Thread에 예약이된다)
        //-commitNow >바로 실행해
    }
    fun printTestLog(){
        Log.d("testt","print test Log")
    }
}
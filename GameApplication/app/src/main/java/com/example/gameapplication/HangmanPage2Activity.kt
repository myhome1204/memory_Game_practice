package com.example.gameapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapplication.databinding.ActivityHangmanPage2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HangmanPage2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHangmanPage2Binding
    private lateinit var viewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman_page2)
        //fragment를 사용할때 데이터를 bundle로 주고받는다 하지만 bundle은 중복사용이불가능하고 한번보낼때 commit으로 끝맺음을 해야해서
        //실시간으로 정보를 주고받는 형태에서 bundle로 정보를 주고받는거는 불편하고 쓰기 어려움
        //그래서 실시간정보를 주고받을수있는 viewModel이라는 사용함(Binding)작업이 필요함
        binding = DataBindingUtil.setContentView(
            this@HangmanPage2Activity,
            R.layout.activity_hangman_page2
        )
        viewModel = ViewModelProvider(this@HangmanPage2Activity).get(WordViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@HangmanPage2Activity
        //아까 페이제1에서보낸 레벨(이미지리스트를 고르기위함)과 chance(목숨)을받아줌
        val level = intent.getIntExtra("level", 0)
        val chance = intent.getIntExtra("chance", 0)
        //database함수를 만들때 코루틴 형태로 정의를해서 데이터를받아올때도 코루틴 쓰레드를통해서 작업해야함
        //코루틴에서 바로 해당액티비티의 메인(UI)쓰레드에 접근할수없어서 launch(Dispatchers.Main)를 열어서 UI를 사용해야함
        // 코루틴을스코프를통해 데이터를 가져오고 랜덤으로 선택하기위해 random변수를 만들어둠
        CoroutineScope(Dispatchers.IO).launch {
            val appDatabase = AppDatabase.getInstance(applicationContext)
            val myDataDao = appDatabase.myDataDao()
            val random = Random(System.currentTimeMillis())
            val ramdomNumber = random.nextInt(1, 6)
            val data = myDataDao.getMyDataById(ramdomNumber)
            launch(Dispatchers.Main) {
                var userAnswer = ""
                //editview로 userAnswer을 저장해둠
                findViewById<EditText>(R.id.answer).doAfterTextChanged {
                    userAnswer = it.toString()
                }
                //fragment(class로 정의한)를 액티비티에 붙이기위해서는 Transaction을 이용해야함
                //fragment한테 보내는정보는 실시간으로 사용할필요없는 level과
                //정답인 data 그리고 chance(목숨)은 bundle로 보낸다
                val fragmentTransaction1 = supportFragmentManager.beginTransaction()
                val wordFragment = WordFragment()
                val bundle = Bundle()
                bundle.putInt("level", level)
                bundle.putInt("chance", chance)
                bundle.putString("word", data?.answer)
                wordFragment.arguments = bundle
                fragmentTransaction1.replace(R.id.fragmentview, wordFragment)
                fragmentTransaction1.commit()
                findViewById<TextView>(R.id.check).setOnClickListener {
                    viewModel.updateWord(userAnswer)
                }

            }
        }

    }

}






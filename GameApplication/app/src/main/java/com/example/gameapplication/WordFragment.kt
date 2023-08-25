package com.example.gameapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapplication.databinding.WordfragmentBinding

class WordFragment : Fragment() {
    //fragment의 액티비티와의 실시간 정보교환을 위한 viewModel을 정의한다
    private lateinit var viewModel: WordViewModel
    //onViewCreated밖에서 카운트비교를위해서 변수 count의 범위를 넓게잡기위해 선언해준다
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: WordfragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.wordfragment,
            container,
            false
        )
        viewModel = ViewModelProvider(requireActivity()).get(WordViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 액티비티에서 fragment에 bundle로보낸 정보들을 받는다
        var level: Int? = arguments?.getInt("level")
        var life: Int? = arguments?.getInt("chance")
        var word: String? = arguments?.getString("word")
        //부드러운 화면전환을 위해서 recyclerview를 만들어두었다.
        val fragmentrecycleview = view.findViewById<RecyclerView>(R.id.Fragmentrecycleview)
        fragmentrecycleview.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val imageList = level?.let { ImageManager.getImagesByType(it) }
        fragmentrecycleview.adapter = imageList?.let { ImageAdapter(it) }
        if (word != null) {
            Log.d("testt", "" + word)
            //displayWord는 화면에 _ _ _ _ _ 를 나타내는 함수이다
            displayWord(word)
        }
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            if (word != null) {
                if (life != null) {
                    val alphabet = viewModel.word.value
                    if (alphabet != null) {
                        if (level != null) {
                            checkUserInput(word, viewModel.word, life,level)
                        }
                    }
                }
            }
        })
    }
    //파라미터로받은 정답을 이용해서 정답을 _ _ _ 으로 바꿔주는함수이다
    private fun displayWord(word: String) {
        if (word != null) {
            view?.findViewById<TextView>(R.id.hint)?.text =
                word.map { if (it == ' ') " " else "_" }.joinToString(" ")
        }

    }
    //activity에서 보내준 알파벳이 정답에 포함되어있으면 _을 해당 알파벳으로 바꿔주는함수이다
    //또한 사용자가 없는 알파벳을 보내줄 때 마다 Recyclerview에있는 이미지를 바꿔주는 역할도한다
    private fun checkUserInput(
        answer: String,
        userInput: MutableLiveData<String>,
        life: Int,
        level : Int
    ) {
        val updatedWord = StringBuilder(view?.findViewById<TextView>(R.id.hint)?.text.toString())
        val input = userInput.value ?: ""
        for (i in answer.indices) {
            if (answer[i] == input[0]) {
                updatedWord[i * 2] = answer[i]
            }
        }
        view?.findViewById<TextView>(R.id.hint)?.text = updatedWord.toString()
        view?.findViewById<RecyclerView>(R.id.Fragmentrecycleview)?.apply {
            if (!answer.contains(input)) {
                count++
                this.layoutManager?.scrollToPosition(count)
                //해당 if는 사용자가 죽엇을때 실행되는 코드이다 count == (life + 1)가 죽는조건이다
                if (count == (life + 1)) {
                    //다른 액티비티로 넘어가서 게임종료를 보여준다
                    //intent로 level과 chance를 보내는 이유는 게임오버 액티비티에서 다시 새롭게 게임을 시작하는버튼을 눌럿을때
                    // GameOverActivity에서 다시 HangmanPage2Activity로 넘어갈때 HangmanPage2Activity한테 보내줘야하는
                    //값을 보내주기 위해서 보내는거다
                    // level과 chance를 HangmanPage2Activity에서 Fragment로 보내고 그걸다시 GameOverActivity로 보내고
                    //다시하기버튼을 누르면 다시 GameOverActivity에서 HangmanPage2Activity로 보내는거다
                    //HangmanPage2Activity는 시작할때 intent로 2개의 값을받으니까
                    val intent = Intent(requireContext(), GameOverActivity::class.java)
                    intent.putExtra("level",level)
                    intent.putExtra("chance",life)
                    startActivity(intent)
                    activity?.overridePendingTransition(R.anim.fade, R.anim.fade)
                    activity?.finish() //액티비티 이동시에 이 액티비티를 죽여서 regame시에 다시활용안시킴
                }
            }
        }

    }
}



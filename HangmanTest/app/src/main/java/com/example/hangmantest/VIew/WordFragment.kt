package com.example.hangmantest.VIew

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hangmantest.ViewModel.GameViewModel
import com.example.hangmantest.ViewModel.GameViewModelFactory
import com.example.hangmantest.database.animals_database
import com.example.hangmantest.databinding.WordfragmentBinding
import com.example.hangmantest.repository.Repository
import java.time.LocalDate

class WordFragment : Fragment() {
    private lateinit var binding: WordfragmentBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var db : animals_database
    private lateinit var myViewModelFactory: GameViewModelFactory
    private lateinit var repository: Repository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WordfragmentBinding.inflate(inflater)
        db = context?.let {animals_database.getInstance(it.applicationContext)}!!
        repository = Repository(db.animalDao())
        myViewModelFactory = GameViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory).get(GameViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.animal_length.observe(viewLifecycleOwner){value ->
            val word_length = viewModel.animal_length.value
            binding.wordHint.columnCount = word_length!!
            binding.wordHint.rowCount =1
            for(i : Int in 0..value-1 ) {
                val new_view = TextView(requireActivity())
                new_view.text = "?"
                new_view.setTextSize(20f)
                new_view.setTextColor(Color.WHITE)
                new_view.setBackgroundColor(Color.BLACK)
                new_view.gravity = Gravity.CENTER
                new_view.id = View.generateViewId()
                val params = GridLayout.LayoutParams()
                params.width = GridLayout.LayoutParams.WRAP_CONTENT
                params.height = GridLayout.LayoutParams.WRAP_CONTENT
                params.setMargins(10, 10, 10, 10)
                params.setGravity(Gravity.CENTER)
                binding.wordHint.addView(new_view, params)
            }
        }
        viewModel.inputAlphabet.observe(viewLifecycleOwner){result ->
            val temp = viewModel.inputAlphabet.value
            if (temp != null){
                val check = viewModel.alpha_check(result)
                if (check == -1){
                    val temp2 = viewModel.imageOrder.value
                    if (temp2!= null){
                        viewModel.change_image(temp2+1)
                    }
                }else{
                    // 바인딩으로 그리드 레이아웃에알파벳 넣기
                    val alphabet = viewModel.inputAlphabet.value
                    if (alphabet != null){
                        for(i in viewModel.get_word_index()){
                            val temp_textView = binding.wordHint.getChildAt(i) as TextView
                            temp_textView.text = alphabet
                        }
                    }
                }
            }

        }
    }
}
package com.example.hangmantest.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangmantest.R
import com.example.hangmantest.database.animal
import com.example.hangmantest.repository.Repository
import kotlinx.coroutines.launch
import java.util.Random

class GameViewModel(private val repository :Repository) : ViewModel() {
    var Death = 0
    var Correct = 0
    var imageList =  arrayOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,
        R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine)
    var index_list : MutableList<Int> = mutableListOf()
    private lateinit var animals: List<animal>
    private lateinit var answer : String
    private val _imageOrder : MutableLiveData<Int> = MutableLiveData(0)
    val imageOrder : LiveData<Int> = _imageOrder
    private val _inputAlphabet : MutableLiveData<String> = MutableLiveData()
    val inputAlphabet : LiveData<String> =_inputAlphabet
    private val _gameSuccessSignal :MutableLiveData<Int> = MutableLiveData()
    val gameSuccessSignal : LiveData<Int> = _gameSuccessSignal
    private val _gameOverSignal : MutableLiveData<Int> = MutableLiveData()
    val gameOverSignal : LiveData<Int> = _gameOverSignal
    val random = Random()
    val answer_num = random.nextInt(10)+1
    private val _animal_length : MutableLiveData<Int> = MutableLiveData()
    val animal_length :LiveData<Int> = _animal_length
    init{
        start()
    }
    fun change_image(image : Int){
        _imageOrder.value = image
    }
    fun change_input(alphabet : String){
        _inputAlphabet.value = alphabet
    }
    fun get_word_index() : List<Int>{
        return index_list
    }
    fun alpha_check(alpha : String) : Int{
        var ans = -1
        index_list = mutableListOf()
        for(i : Int in 0 .._animal_length.value!!-1){
            if (alpha.equals(answer[i].toString())){
                ans = 1
                index_list.add(i)
            }
        }
        if (ans == -1){
            val temp = _imageOrder.value
            if (temp!= null){
                _imageOrder.value= temp
            }
            Death+=1
        }else{
            Correct+=1
        }
        if (Correct == _animal_length.value){
            _gameSuccessSignal.value = 1
            // 게임 성공!
        }
        if (Death == _animal_length.value){
            _gameOverSignal.value = 1
            //게임실패
        }
        return ans
    }
    private fun start(){
        viewModelScope.launch {
            animals = repository.getAnimal()!!
            answer = animals[answer_num].animal
            Log.d("testt",answer)
            _animal_length.value = animals[answer_num].length
        }
    }
}
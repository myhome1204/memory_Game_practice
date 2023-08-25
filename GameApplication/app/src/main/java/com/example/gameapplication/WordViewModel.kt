package com.example.gameapplication
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WordViewModel: ViewModel() {
    val word = MutableLiveData<String>()
    fun updateWord(newWord: String) {
        // 데이터 업데이트 로직 추가
        word.value = newWord
    }
}
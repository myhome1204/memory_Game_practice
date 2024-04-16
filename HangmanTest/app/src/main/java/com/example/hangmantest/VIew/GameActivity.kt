package com.example.hangmantest.VIew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hangmantest.ViewModel.GameViewModel
import com.example.hangmantest.ViewModel.GameViewModelFactory
import com.example.hangmantest.database.animals_database
import com.example.hangmantest.databinding.ActivityGameBinding
import com.example.hangmantest.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var db : animals_database
    private lateinit var viewModel: GameViewModel
    private lateinit var repository: Repository
    private lateinit var myViewModelFactory: GameViewModelFactory
    private lateinit var imageFragment: ImageFragment
    private lateinit var wordFragment: WordFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = animals_database.getInstance(applicationContext)!!
        repository = Repository(db.animalDao())
        myViewModelFactory = GameViewModelFactory(repository)
        imageFragment = ImageFragment()
        wordFragment = WordFragment()
        supportFragmentManager.beginTransaction()
            .add(binding.imageFragment.id,imageFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(binding.wordFragment.id,wordFragment)
            .commit()
        viewModel = ViewModelProvider(this,myViewModelFactory).get(GameViewModel::class.java)
        viewModel.gameSuccessSignal.observe(this){value ->
            Toast.makeText(applicationContext, "게임을 성공적으로 끝내셨습니당!",Toast.LENGTH_SHORT).show()
        }
        viewModel.gameOverSignal.observe(this){value ->
            Toast.makeText(applicationContext, "게임오버 !!!!",Toast.LENGTH_SHORT).show()
        }
        binding.checkBtn.setOnClickListener {
            viewModel.change_input(binding.input.text.toString())
        }
    }

}
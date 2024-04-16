package com.example.hangmantest.VIew

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hangmantest.ViewModel.GameViewModel
import com.example.hangmantest.ViewModel.GameViewModelFactory
import com.example.hangmantest.database.animals_database
import com.example.hangmantest.databinding.ImagefragmentBinding
import com.example.hangmantest.repository.Repository

class ImageFragment : Fragment() {
    private lateinit var binding: ImagefragmentBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var db : animals_database
    private lateinit var repository: Repository
    private lateinit var myViewModelFactory: GameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagefragmentBinding.inflate(inflater)
        db = context?.let { animals_database.getInstance(it.applicationContext) }!!
        repository = Repository(db.animalDao())
        myViewModelFactory = GameViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory).get(GameViewModel::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.imageOrder.observe(viewLifecycleOwner){value ->
            binding.image.setImageResource(viewModel.imageList[value])
        }

    }
}
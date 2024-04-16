package com.example.hangmantest.repository

import com.example.hangmantest.database.animal
import com.example.hangmantest.database.animal_dao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class Repository (private val animalDao : animal_dao) {
    suspend fun getAnimal() : List<animal>{
        val animals = CoroutineScope(Dispatchers.IO).async {
            animalDao.find_animals()
        }.await()
        return animals
    }
}
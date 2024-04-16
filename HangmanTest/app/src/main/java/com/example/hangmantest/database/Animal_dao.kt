package com.example.hangmantest.database

import androidx.room.Dao
import androidx.room.Query
import com.example.hangmantest.database.animal

@Dao
interface animal_dao {
    @Query("SELECT * FROM animals")
    fun find_animals() : List<animal>
}
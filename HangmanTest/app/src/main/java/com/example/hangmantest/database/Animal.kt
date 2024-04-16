package com.example.hangmantest.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class animal(
    var animal : String,
    var length : Int
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}

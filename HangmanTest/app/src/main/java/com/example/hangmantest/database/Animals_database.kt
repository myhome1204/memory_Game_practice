package com.example.hangmantest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [animal::class], version = 1 )
abstract class animals_database : RoomDatabase(){
    abstract fun animalDao() : animal_dao

    companion object{
        private var instance : animals_database? = null
        @Synchronized
        fun getInstance(context: Context) : animals_database?{
            if(instance == null){
                synchronized(animals_database::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        animals_database::class.java,
                        "animal"
                    ).createFromAsset("db/animal_words.db").build()
                }
            }
            return instance
        }
    }
}
package com.example.gameapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE


import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.*

class DatabaseCreateActivity : AppCompatActivity() {
    //데이터베이스 만들고 실제로 데이터를 집어넣음
    //데이터베이스를 비동기스레드로 이용해 반복사용을 줄이고 효율을높이기위해서 코루틴으로 만들었다.
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_create2)
        CoroutineScope(Dispatchers.IO).launch {
            val appDatabase = AppDatabase.getInstance(applicationContext)
            val myDataDao = appDatabase.myDataDao()
            val myDataList = listOf(
                MyData(id = 1, answer = "apple", deathCount = 5),
                MyData(id = 2, answer = "banana", deathCount = 6),
                MyData(id = 3, answer = "grape", deathCount = 5),
                MyData(id = 4, answer = "kiwi", deathCount = 4),
                MyData(id = 5, answer = "coconut", deathCount = 7)
            )
            myDataDao.insertAll(myDataList)
            val allData = myDataDao.getAllData()
            for (data in allData) {
                Log.d(
                    "Database",
                    "ID: ${data.id}, Answer: ${data.answer}, Death Count: ${data.deathCount}"
                )
            }
        }
    }
}

@Entity(tableName = "my_table")
data class MyData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "answer") val answer: String,
    @ColumnInfo(name = "death_count") val deathCount: Int
)

@Dao
interface MyDataDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(myDataList: List<MyData>)

    @Query("SELECT * FROM my_table")
    suspend fun getAllData(): List<MyData>

    @Query("SELECT * FROM my_table WHERE id = :id")
    suspend fun getMyDataById(id: Int): MyData?
}

@Database(entities = [MyData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDataDao(): MyDataDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

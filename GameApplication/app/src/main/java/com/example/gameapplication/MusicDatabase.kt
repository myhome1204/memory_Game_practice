package com.example.gameapplication

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Music::class], version = 1)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
    companion object {
        private var INSTANCE: MusicDatabase? = null
        fun getInstance(context: Context): MusicDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MusicDatabase::class.java,"MusicDatabase.db"
                    )
                        .createFromAsset("MusicDatabase.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
@Entity(tableName = "Musics")
data class Music(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "musicfilepath") val musicfilepath: String?
)
@Dao
interface MusicDao {
    @Query("SELECT * FROM musics")
    fun getAll(): List<Music>
    @Insert
    fun insertMusicFilePath(music :Music)
}
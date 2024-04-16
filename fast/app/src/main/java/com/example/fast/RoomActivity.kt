package com.example.fast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.NullPointerException

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
//        //기본적으로 데이터베이스 작업은 메인 쓰레드에서 할 수 없다
//        //이유는 ,데이터베이스 작업을 휴대폰이 하는 동안 사용자가 기다려야하기 때문
//        //해결책은 , 쓰레드를 이요하거나 asvnc 를 이용하면된다
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user-database"
        ).allowMainThreadQueries().build()
        val userProfile = UserProfile(1,"홍","길동")
        findViewById<TextView>(R.id.save).setOnClickListener {
            database.userprofileDao().insert(userProfile)
            Log.d("testt",""+userProfile)
        }
        findViewById<TextView>(R.id.load).setOnClickListener {
            Log.d("testt","클릭됨")
            val userProfiles = database.userprofileDao().getAll()
            Log.d("testt",""+userProfiles)
        }
        findViewById<TextView>(R.id.del).setOnClickListener {
            database.userprofileDao().delete(userProfile)
        }
    }
}
//우리는 UserProfile을 만들엇고 그리고 그 클래스를 Entity로 사용하겟다고 말해주는것이 @Entity이다, 콜론명
//이러한문법을 어노태이션 이라고한다
//Entity는 데이터베이스에서보면 제일 윗 행이된다 (이름,학력,성적,과 등등....)
// 한가지 행을 유니크(유일하게)하게 만들어줘야한다 그게 Primary key이다 그건 @property를 사용한다
@Entity
data class UserProfile(
    //id가 자동으로 1씩 커지게 만들 수 있다
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "last_name")
    val lastName : String,
    @ColumnInfo(name = "first_name")
    val firstName : String
){
    //부생성자를만들면 나중에 객체만들때 앞에 id를안넣어도댐
    constructor(lastName: String,firstName: String): this(0,lastName,firstName)
}
//DAO를만들어야함 데이터에 접근하는 객체 (필요한걸 꺼내오는등등...CRUD)
@Dao
interface UserProfileDao{
    //CRUD > 데이터 베이스 조작
    //Query > 데이터 베이스 조회
    @Insert(onConflict = REPLACE)
    fun insert(userProfile: UserProfile)
    //SELECT * FROM 테이블명 WHERE 조건;
    @Delete
    fun delete(userProfile: UserProfile){

    }
    @Query("SELECT * FROM userprofile")
    fun getAll() :List<UserProfile>
}
@Database(entities = [UserProfile::class],version = 1)
abstract  class AppDatabase : RoomDatabase(){
    abstract fun userprofileDao() : UserProfileDao
}
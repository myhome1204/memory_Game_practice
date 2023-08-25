package com.example.fast
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.w3c.dom.Text
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.util.UUID
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import android.content.Context
import android.content.SharedPreferences



class SignupPage : AppCompatActivity() {
    var userId: String = ""
    var userPassword1: String = ""
    var userPassword2: String = ""
    var userName: String = ""
    private lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        sharedPreferences = getSharedPreferences("myappdatabase", Context.MODE_PRIVATE)
        val gson = GsonBuilder().setLenient().create()
        (findViewById<TextView>(R.id.logingogo)).setOnClickListener {
                    startActivity(Intent(this, MySignUp::class.java))
                }
        findViewById<EditText>(R.id.id_input).doAfterTextChanged {
            userId = it.toString()
        }
        findViewById<EditText>(R.id.password_input1).doAfterTextChanged {
            userPassword1 = it.toString()
        }
        findViewById<EditText>(R.id.password_input2).doAfterTextChanged {
            userPassword2 = it.toString()
        }
        findViewById<EditText>(R.id.name).doAfterTextChanged {
            userName = it.toString()
        }
        val password_ok =  findViewById<TextView>(R.id.password_ok)

        val retrofit = Retrofit.Builder()
            .baseUrl("http:/192.168.45.207/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        findViewById<TextView>(R.id.signgogo).setOnClickListener {
            if(userPassword1 != userPassword2){
                //이제 비밀번호 유효성검사 코드 추가하기 유효성에안맞으면 text를 형식에맞춰주세요라고 만들면됨
                password_ok.text = "비밀번호가 다릅니다."
                password_ok.visibility = View.VISIBLE
            }else{
                val hashedPassword = hashPassword(userPassword1)
                Log.d("testt","${hashedPassword}")
                password_ok.visibility = View.INVISIBLE
                retrofitService.Register(userId,hashedPassword,userName)
                    .enqueue(object : Callback<Redata> {
                        override fun onResponse(call: Call<Redata>, response: Response<Redata>) {
                            val re = response.body()
                            if(re != null) {
                                val UUID = generateUUID()
                                saveData("${re.userId}","${UUID}")
                                val keyAlias  = getData("${re.userId}")

                                Log.d("testt", "${re.userPassword}")
                            }
                        }
                        override fun onFailure(call: Call<Redata>, t: Throwable) {
                            Log.e("NetworkError","${t.message}")
                        }
                    })
            }
        }
        findViewById<TextView>(R.id.idcheck).setOnClickListener {
            retrofitService.checkDuplicateId(userId).enqueue(object : Callback<JsonElement>{
                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    if(response.isSuccessful) {
                        val jsonElement = response.body()
                        Log.d("testt", "아이디중복체크 연결성공")
                        if (jsonElement is JsonObject) {
                            val isDuplicate = jsonElement.get("isDuplicate").asBoolean
                            val message = jsonElement.get("message").asString
                            password_ok.text = message
                            password_ok.visibility = View.VISIBLE
                        }
                    }
                }
                override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                    Log.e("NetworkError","${t.message}")
                }
            })
        }
    }
    fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }
    private fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    private fun getData(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

}



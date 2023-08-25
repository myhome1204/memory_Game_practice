package com.example.fast
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.Serializable

class StudentFromService(
    val id : Int , val name : String = "" ,val age : Int,val intro : String
){
    constructor(name:String,age:Int,intro: String):this(0,name,age,intro)
}
class YoutubeItem(
    val id: Int,val title : String,val content : String,val video : String,val thumbnail:String
)
class UserToken(
     val username : String, val token : String
)
class Token(
    val token : String
)
data class Test1(
    val userId : String ,val userPassword1 :String ,val userPassword2 : String ,val userName :String
)
class jud(
    val jud : String
)
class MelonItem(
    val id : Int , val title : String = "" ,val song : String,val thumbnail : String
): Serializable
interface RetrofitService {
    @GET("melon/list/")
    fun getMelonItemList():Call<ArrayList<MelonItem>>
    @GET("json/students")
    fun getStudentList(): Call<ArrayList<StudentFromService>>
    @POST("json/students/")
    fun createStudent(
        @Body params: HashMap<String,Any>
    ): Call<StudentFromService>
    @GET("youtube/list/")
    fun getYoutubeItemList(): Call<ArrayList<YoutubeItem>>
    @POST("json/students")
    fun easyCreateStudent(
        @Body student : StudentFromService
    ): Call<StudentFromService>
    //login 정보 보내기
    @POST("user/login/")
    @FormUrlEncoded
    fun Login(@FieldMap params: HashMap<String,Any>
    ): Call<Token>
    //회원가입 정보 보내기
    @POST("user/signup/")
    @FormUrlEncoded
    fun join(@FieldMap params: HashMap<String,Any>
    ): Call<UserToken>
    @FormUrlEncoded
    @POST("SignUp.php")
    fun Register(
        @Field("userId") userId:String,
        @Field("userPassword") userPassword: String,
        @Field("userName") userName:String
    ): Call<Redata>

    @FormUrlEncoded
    @POST("check_duplicate.php")
    fun checkDuplicateId(@Field("userId") userId: String): Call<JsonElement>


    @FormUrlEncoded
    @POST("check_login.php")
    fun checkLogin(
        @Field("userId") userId:String,
        @Field("userPassword") userPassword: String
    ) : Call<checklogin>

}
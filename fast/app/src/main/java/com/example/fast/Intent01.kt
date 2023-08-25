package com.example.fast

import android.content.ComponentName
import android.content.Intent
import android.content.Intent.EXTRA_STREAM
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text
import java.nio.channels.InterruptedByTimeoutException

class Intent01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent01)
        //scheme는 카테고리를 고르는 느낌 (tel인지 http인지)
        //host는 더 세부적으로 뭔지 (naver.com만받을건지 google.com만받을건지)
        //intent로 보낼때 ACTION.VIEW를 햇으니까 intent필터에 안에 필터가 잘작동하도록(필터에잡히게) action.intent.action.VIEW추가하거
        //웹브라우저를 사용한다고 category.BROWSABLE추가하기
        //암시적 인텐트
        //-전화,SMS,Google Play Store,Website,GoogleMap,사진첩등등
        //데이터를 전달할때는 URI형태로 보내야댐 (Uniform Resource Indentifier)
            //-id라고 생각하면댐
            //-고유함 자원을 나타내는 주소
            // -URL 은 인터넷 페이지의 고유한 주소임
        val implicit_intent : TextView = findViewById(R.id.implicit_intent)
        implicit_intent.setOnClickListener {
            val intent : Intent = Intent("android.intent.action.DIAL", Uri.parse("tel:"+"010-0000-1111"))
            //intent를 실행하려면 startActivity를 해야함
            startActivity(intent)
        }
        //명시적 인텐트 + CpmponentName > 액티비티 전환
        val intent_one : TextView = findViewById(R.id.intent1)
        intent_one.setOnClickListener {
            val intent1 : Intent = Intent()
            val componentName : ComponentName = ComponentName(
                "com.example.fast",
                "com.example.fast.Intent02"
            )
            intent1.component = componentName
            startActivity(intent1)
        }
        //지금 apply쓰는 아래에 this는 원래는 TextView를말하는거다 하지만 this뒤에 골뱅이를붙이게되면
        // this는 이 AppCompatActivity()을 가리키게된다  ::뒤에 클래스.java사용
        (findViewById<TextView>(R.id.intent2)).apply{
            this.setOnClickListener{
                //아래 코드는 화면 전환하는 코드임
                startActivity(Intent(this@Intent01,Intent02::class.java))
            }
        }
        //명시적 인텐트 + data 전달 key,value형식으로 데이터를넣어줌
        (findViewById<TextView>(R.id.intent3)).apply{
            this.setOnClickListener{
                val intent = Intent(this@Intent01,Intent02::class.java)
                intent.putExtra("extra-data","data-one")
                //스트링으로보내면 받을때도 getString해야하고 Int로보내면 getInt로해야함
                startActivity(intent)
            }
        }
        //명시적인텐트 + 결과받기 2가지방법
        //requestCode 그 결과값을 구분하기위한 정수 변수임
        //구분을하기위해서  Intent01에서 여러군데로보내고 여러군데에서 결과값을받을때
        //Intent01 >>Intent02
        //Intent01 >>Intent03
        //Intent01 >>Intent04
        //requestCode는 변수느낌으로 설정하는느낌임
        //어디서오는 결과값인지 구분해줘야함
        //받을때는 OnCreate 범위밖에어 Generate로 받아야함 (마우스우클릭) onActivityResult
        (findViewById<TextView>(R.id.intent4)).apply{
            this.setOnClickListener{
                val intent = Intent(this@Intent01,Intent02::class.java)
                //결과값을받기위한 메소드는   startActivityForResult인데 deprecated됏음
                //resultCode
                //최종결과,성공,실패를 담아줌
                //그리고 결과를받는쪽말고 결과를 보내는쪽에서도 코드를 작성해줘야함
                //맨처음 데이터를보내고 데이터를받는쪽에서 데이터를받고 데이터 잘받앗다고 다시 결과를보내는 구조임
                //처음에 Intent01에서데이터를 보내고 Intent02로 화면전환이되고 다시 Intent02에서 잘받앗다고 답장을보내는데 답장을받는 시기는 Intent01이 다시열렷을때임
                //Intent02에서도 데이터를 보내는 코드를 작성해줘야함
                //그래서 Intent02를 바로 종료시켜서 Intent01을 여는거임
            }
        }
        //명시적인텐트 +결과받기 (ActivityResult API)
        //requestCode 가존재하지않음  저게없어도 구분가능하다
        val startActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            //onActivityResult에 해당하는 부분
            //it에 리절트코드랑 다들어있음
            when(it.resultCode){
                RESULT_OK ->{
                    Log.d("dataa",it.data?.extras?.getString("result")!!)
                }
            }
            //onActivityResult는 모든 Intent가 한곳에서 처리된다
            //ActivityResult API는 각각의 intent가 처리되는곳이 별도로 있다
        }
        (findViewById<TextView>(R.id.intent5)).apply{
            this.setOnClickListener{
                val intent = Intent(this@Intent01,Intent02::class.java)
               startActivityLauncher.launch(intent)
            }
        }
        
        //명시적인텐트 + 이미지 URI전달
        findViewById<TextView>(R.id.intent6).apply{
            this.setOnClickListener{
                val intent : Intent = Intent(this@Intent01,Intent02::class.java).apply { 
                    val imageUri = Uri.parse("android.resource://"+packageName+"/drawable/"+"cat")
                    this.action = Intent.ACTION_SEND
                    //key-value말고 다른타입의 데이터르 보낼꺼면 다른방식이필요함
                    this.putExtra(Intent.EXTRA_STREAM,imageUri)
                    this.setType("image/*")
                }
                startActivity(intent)
            }
        }
        //인텐트를 이용해서 데이터 전달이 가능하다
        //  -인텐트를 이용해서 키벨류 데이터를 전달한다
        //  -인텐트를 이용해서 이미지를 전달한다
    }

    
}
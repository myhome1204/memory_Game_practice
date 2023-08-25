package com.example.fast

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doBeforeTextChanged
import org.w3c.dom.Text

class Webview_homework01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_homework01)
        val myWebview = findViewById<WebView>(R.id.web)
        myWebview.setWebViewClient(object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                //return true면 주도권을 가져오지 않는다(우리앱이)
                //false면 주도권을 가져오겟다
                return false
            }
        })
        try{
            myWebview.loadUrl(
                intent.data!!.toString()
            )
        }catch (exception : Exception){

        }
        val myeditText = findViewById<EditText>(R.id.edittext)
        val urlPrefix = "http://www."
        var finalUrl = ""
        myeditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("testt","Before")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("testt","ing")
            }

            override fun afterTextChanged(s: Editable?) {

                finalUrl = urlPrefix + s.toString()
            }
        })
        //람다방식
        myeditText.doBeforeTextChanged { text, start, count, after ->  }
        //쉬운버전임
//        (findViewById<TextView>(R.id.search)).setOnClickListener {
//            val myWebview = findViewById<WebView>(R.id.web)
//            myWebview.loadUrl(finalUrl)
//        }
        val open = findViewById<TextView>(R.id.search)

        open.setOnClickListener {
            val url = myeditText.text.toString()
            //인텐트로 url전달만 한거임 그 후 작업도 해줘야함
            //인텐트로 내가 url을보내고 그걸다시 내가받아서 내 웹뷰에다가 띄워야함
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}






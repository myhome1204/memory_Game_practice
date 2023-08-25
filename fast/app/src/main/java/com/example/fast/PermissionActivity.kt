package com.example.fast

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        findViewById<TextView>(R.id.askpermission).setOnClickListener {
            val cameraPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            )
            if (cameraPermission != PackageManager.PERMISSION_GRANTED){
                //권한이 없는 경우
                ActivityCompat.requestPermissions(
                    this,
                    //무슨권한요청하는지 array형태로 보내줘야함 그래서받을때도 인덱스형태로받음
                    arrayOf(android.Manifest.permission.CAMERA),
                    1000
                )
            }else{
                Log.d("testt","권한있음")
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode ==1000){
            //우리가 보낸 권한요청이맞다면
            //grantResults에는 획득한 권한이있음
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("testt","승낙")
            }else{
                Log.d("testt","거부")
            }
        }
    }
}
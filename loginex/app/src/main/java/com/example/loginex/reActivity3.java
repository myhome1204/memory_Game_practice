package com.example.loginex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class reActivity3 extends AppCompatActivity {
    private EditText et_id1,et_pass1;
    private Button btn_login,btn_re1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re3);


        et_id1 = findViewById(R.id.et_id1);
        et_pass1 = findViewById(R.id.et_pass1);
        btn_login = findViewById(R.id.btn_login);
        btn_re1 = findViewById(R.id.btn_re1);
        //회원가입버튼을 클릭 시 수행
        btn_re1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(reActivity3.this,reActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText에 입력되어있는값을 가져온다
                String userID =et_id1.getText().toString();
                String userPass =et_pass1.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success  = jsonObject.getBoolean("success");
                            if (success){//로그인성공
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
                                Intent intent =  new Intent(reActivity3.this,MainActivity.class);
                                intent.putExtra( "userID",userID);
                                intent.putExtra( "userPassword",userPass);
                                startActivity(intent);
                            }else{//로그인실패
                                Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                                return ;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID,userPass,responseListener);
                RequestQueue queue = Volley.newRequestQueue(reActivity3.this);
            }
        });
    }
}
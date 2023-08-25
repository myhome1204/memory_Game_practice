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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class reActivity extends AppCompatActivity {
    private EditText et_id,et_pass,et_name,et_age,et_w;
    private Button btn_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티실행시 처음실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);
        //아디값 찾아주기

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_w = findViewById(R.id.et_w);
        btn_re = findViewById((R.id.btn_re));
        //회원가입 버튼 클릭시  수행
        btn_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력되어잇는값을 가져온다
                String userID =et_id.getText().toString();
                String userPass =et_pass.getText().toString();
                String userName =et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());
                int userWeight = Integer.parseInt(et_w.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success  = jsonObject.getBoolean("success");
                            if (success){//회원등록성공
                                Toast.makeText(getApplicationContext(),"회원등록 성공",Toast.LENGTH_SHORT).show();
                                Intent intent =  new Intent(reActivity.this,reActivity3.class);
                                startActivity(intent);
                            }else{//회원등록실패
                                Toast.makeText(getApplicationContext(),"회원등록 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } ;
                //서버로 volley 를통해 요청
                RegisterRequest registerRequest = new RegisterRequest(userID,userPass,userName,userAge,userWeight,responseListener);
                RequestQueue queue = Volley.newRequestQueue(reActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
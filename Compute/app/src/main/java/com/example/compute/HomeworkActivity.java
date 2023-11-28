package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class HomeworkActivity extends AppCompatActivity {
CheckBox android,iphone,windowphone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        android = findViewById(R.id.android);
        iphone = findViewById(R.id.iphone);
        windowphone = findViewById(R.id.windowphone);
        android.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(HomeworkActivity.this, "android good", Toast.LENGTH_LONG).show();
            }
        });
        iphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(HomeworkActivity.this, "iphone good", Toast.LENGTH_LONG).show();
            }
        });
        windowphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(HomeworkActivity.this, "MS사 모바일 운영체제", Toast.LENGTH_LONG).show();
            }
        });
    }
}
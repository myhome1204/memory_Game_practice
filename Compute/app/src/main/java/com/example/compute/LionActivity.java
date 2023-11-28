package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyImageView(this));
    }
}
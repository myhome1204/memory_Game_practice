package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Homeword2Activity extends AppCompatActivity {
TextView text;
CheckBox chkAgree;
RadioGroup group;
Button btndog, btncat , btnrabbit;
ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeword2);
        text = findViewById(R.id.text);
        chkAgree = findViewById(R.id.chkAgreed);
        group = findViewById(R.id.group);
        btndog= findViewById(R.id.btndog);
        btncat = findViewById(R.id.btncat);
        btnrabbit = findViewById(R.id.btnrabbit);
        imgPet = findViewById(R.id.imgpet);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkAgree.isChecked() == true){
                    text.setVisibility(View.VISIBLE);
                    group.setVisibility(View.VISIBLE);
                    btndog.setVisibility(View.VISIBLE);
                    btncat.setVisibility(View.VISIBLE);
                    btnrabbit.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                }else{
                    text.setVisibility(View.INVISIBLE);
                    group.setVisibility(View.INVISIBLE);
                    btndog.setVisibility(View.INVISIBLE);
                    btncat.setVisibility(View.INVISIBLE);
                    btnrabbit.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });
        btndog.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
               imgPet.setImageResource(R.drawable.dog2);
            }
        });
        btncat.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                imgPet.setImageResource(R.drawable.cat);
            }
        });
        btnrabbit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                imgPet.setImageResource(R.drawable.rabbit);
            }
        });
    }
}
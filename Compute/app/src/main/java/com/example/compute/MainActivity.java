package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button plusbtn;
    Button subbtn;
    Button mulbtn;
    Button divbtn;

    EditText edit1;
    EditText edit2;
    TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        plusbtn = findViewById(R.id.plusbtn);
        subbtn = findViewById(R.id.subbtn);
        mulbtn = findViewById(R.id.mulbtn);
        divbtn = findViewById(R.id.divbtn);
        result = findViewById(R.id.result);
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer su1 = Integer.parseInt(edit1.getText().toString());
                Integer su2 = Integer.parseInt(edit2.getText().toString());
                result.setText("계산결과 : "+(su1+su2));
            }
        });
        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer su1 = Integer.parseInt(edit1.getText().toString());
                Integer su2 = Integer.parseInt(edit2.getText().toString());
                result.setText("계산결과 : "+ (su1-su2));
            }
        });
        mulbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer su1 = Integer.parseInt(edit1.getText().toString());
                Integer su2 = Integer.parseInt(edit2.getText().toString());
                result.setText("계산결과 : "+(su1*su2));
            }
        });
        divbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double su1 = Double.parseDouble(edit1.getText().toString());
                Double su2 = Double.parseDouble(edit2.getText().toString());
                result.setText("계산결과 : "+(su1/su2));

            }
        });


    }
}
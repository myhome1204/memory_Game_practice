package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MoStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_study);
        Button btn = findViewById(R.id.btn);
        EditText eText = findViewById(R.id.edittext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strr = eText.getText().toString();
                Toast.makeText(getApplicationContext(),strr,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View target){
        Toast.makeText(getApplicationContext(),"Beep Bop",Toast.LENGTH_SHORT).show();
    }
}
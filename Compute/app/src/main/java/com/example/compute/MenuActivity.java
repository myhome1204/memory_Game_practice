package com.example.compute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    Button btn;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn = findViewById(R.id.menu);
        img = findViewById(R.id.menuimg);
        registerForContextMenu(btn);
        Animation animation1 = AnimationUtils.loadAnimation(MenuActivity.this,R.anim.test1);
        Animation animation2 = AnimationUtils.loadAnimation(MenuActivity.this,R.anim.test2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet animatorSet = new AnimatorSet();

                ObjectAnimator animation1 = ObjectAnimator.ofFloat(v, "alpha", 0.0f, 1.0f);
                animation1.setDuration(2000);

                ObjectAnimator animation2 = ObjectAnimator.ofFloat(v, "alpha", 1.0f, 0.0f);
                animation2.setDuration(2000);

                animatorSet.playSequentially(animation1, animation2);
                animatorSet.start();

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu1,menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cat){
            img.setImageResource(R.drawable.cat);
            return true;
        } else if (item.getItemId() == R.id.dog) {
            img.setImageResource(R.drawable.dog2);
            return true;
        }else if (item.getItemId()==R.id.rabbit){
            img.setImageResource(R.drawable.rabbit);
            return true;
        }else{
            return super.onContextItemSelected(item);
        }
    }
}
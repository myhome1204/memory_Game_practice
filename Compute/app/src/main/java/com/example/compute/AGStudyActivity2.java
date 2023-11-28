package com.example.compute;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AGStudyActivity2 extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agstudy2);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        VolumeControlView view =  (VolumeControlView) findViewById(R.id.volume);
        view.setKnobListener(new VolumeControlView.KnobListener() {
            @Override
            public void onChanged(double angle) {
                float rating = ratingBar.getRating();
                if(angle > 0 && rating < 7.0){
                    ratingBar.setRating(rating+1.0f);
                }else{
                    ratingBar.setRating(rating-1.0f);
                }
            }
        });

    }
}
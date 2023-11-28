package com.example.aaaapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaapplication.R;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    AlphaAnimation a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        a1 = createAnimation();

//        final Handler handler1 = new Handler();
//        handler1.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                t1.startAnimation(a1);
//            }
//        }, 3000);
        Animation a1 = AnimationUtils.loadAnimation(this,R.anim.ro);
        TextView tbox[] = new TextView[5];
        tbox[0] = t1;
        tbox[1] = t2;
        tbox[2] = t3;
        tbox[3] = t4;
        tbox[4] = t5;
        t1.setOnClickListener(new View.OnClickListener() {
            final Handler handler1 = new Handler();

            int index = 0;

            @Override
            public void onClick(View view) {
              t1.startAnimation(a1);
            }

//            private void startNextAnimation() {
//                if (index < 5) {
//                    AnimatorSet animatorSet = new AnimatorSet();
//                    ObjectAnimator animation1 = ObjectAnimator.ofFloat(tbox[index], "alpha", 0.0f, 1.0f);
//                    animation1.setDuration(1000);
//                    ObjectAnimator animation2 = ObjectAnimator.ofFloat(tbox[index], "alpha", 1.0f, 0.0f);
//                    animation2.setDuration(1000);
//                    animatorSet.playSequentially(animation1, animation2);
//                    animatorSet.addListener(new Animator.AnimatorListener() {
//                        @Override
//                        public void onAnimationStart(Animator animator) {}
//
//                        @Override
//                        public void onAnimationEnd(Animator animator) {
//                            index++;
//                            startNextAnimation(); // 다음 애니메이션 시작
//                        }
//
//                        @Override
//                        public void onAnimationCancel(Animator animator) {}
//
//                        @Override
//                        public void onAnimationRepeat(Animator animator) {}
//                    });
//                    animatorSet.start();
//                }
//            }
        });




    }

    AlphaAnimation createAnimation() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(1000);
        anim.setStartOffset(20);
        return anim;
    }
    AlphaAnimation createAnimation1() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setStartOffset(20);
        return anim;
    }
}

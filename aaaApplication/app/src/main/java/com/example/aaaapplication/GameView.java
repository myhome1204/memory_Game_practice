package com.example.aaaapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.View;

import java.util.List;

public class GameView extends View {
    private Context context;

    private Bitmap imgBack;
    private int w, h;
    private MediaPlayer mPlayer;
    private SoundPool mSound;
    private int soundId;
    private int hit = 0;
    private int miss = 0;
    private float makeTimer = 0;
    private Paint paint = new Paint();
    private List<Sparrow> mSparrow;
}
class GameThread extends Thread {
    public boolean canRun = true;

    @Override public
    void run() {
        while (canRun) {
            try {
                Time.update();
                makeSparrow();
                moveSparrow();
                removeDead();
                postInvalidate();
                sleep(10);
            } catch (Exception e) {
            }
        }
    }
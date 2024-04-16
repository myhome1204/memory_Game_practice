package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class gameview2 extends View {
    class GameThread extends Thread {
        public boolean canRun = true;

        @Override
        public void run() {
            while (canRun) {
                try {
                    Time.update();
                    makeSparrow();
                    moveSparrow();
                    removeDead();
                    postInvalidate();
                    sleep(10);
                } catch (Exception e) {
                    Log.d("testt","안댐");
                }

            }
        }
    }

    private MediaPlayer mPlayer;
    private SoundPool mSound;
    private int soundId;
    private int hit = 0;
    private int miss = 0;
    private float makeTimer = 0;
    private List<Sparrow> mSparrow;
    private Context context;
    private Bitmap imgBack;
    public GameThread mThread;
    private int w, h;
    private Random rnd = new Random();

    private Paint paint = new Paint();

    public gameview2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mSparrow = Collections.synchronizedList(new ArrayList<Sparrow>());
        mPlayer = MediaPlayer.create(context, R.raw.rondo);
        mPlayer.setLooping(true);
        mPlayer.start();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);
        } else {
            AudioAttributes attributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED).setUsage(AudioAttributes.USAGE_GAME).build();
            mSound = new SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(5).build();
        }
        CommonResources.set(context);
        soundId = mSound.load(context, R.raw.fire, 1);
        paint.setTextSize(60);
        paint.setColor(Color.WHITE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        imgBack = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        imgBack = Bitmap.createScaledBitmap(imgBack, w, h, true);
        if (mThread == null) {
            mThread = new GameThread();
            mThread.start();
        }
    }

    public void initGame() {
        synchronized (mSparrow) {
            mSparrow.clear();
        }
        hit = miss = 0;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(imgBack, 0, 0, null);

        synchronized (mSparrow) {
            for (Sparrow tmp : mSparrow) {
                canvas.rotate(tmp.ang, tmp.x, tmp.y);
                canvas.drawBitmap(tmp.bird, tmp.x - tmp.w, tmp.y - tmp.w, null);
                canvas.rotate(-tmp.ang, tmp.x, tmp.y);
            }
        }
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Hit: " + hit, 100, 100, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Miss: " + miss, w - 100, 100, paint);
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            fireBullet(event.getX(), event.getY());
        }
        return true;
    }


    private void removeDead() {
        synchronized (mSparrow) {
            for (int i = mSparrow.size() - 1; i >= 0; i--) {
                if (mSparrow.get(i).isDead) {
                    mSparrow.remove(i);
                }
            }
        }
    }


    private void fireBullet(float x, float y) {
        boolean isHit = false;
        mSound.play(soundId, 1, 1, 1, 0, 1);
        for (Sparrow tmp : mSparrow) {
            if (tmp.hitTest(x, y)) {
                isHit = true;
                break;
            }
        }
        hit = isHit ? hit + 1 : hit;
        miss = isHit ? miss : miss + 1;
    }

    private void makeSparrow() {
        makeTimer -= Time.deltaTime;
        if (makeTimer <= 0) {
            makeTimer = 0.5f;
            synchronized (mSparrow) {
                mSparrow.add(new Sparrow(w, h));
            }
        }
    }

    private void moveSparrow() {
        synchronized (mSparrow) {
            for (Sparrow tmp : mSparrow) {
                tmp.update();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mThread.canRun = false;
        super.onDetachedFromWindow();
    }
}
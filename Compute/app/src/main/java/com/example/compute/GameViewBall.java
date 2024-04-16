package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameViewBall extends View {
    private Context context;
    private GameThreadBall mThread;
    private Bitmap imgBack;
    private int w, h;
    private List<Ball> mBall;

    class GameThreadBall extends Thread {
        public boolean canRun = true;

        @Override
        public void run() {
            while (canRun) {
                try {
                    TimeBall.update();
                    moveBall();
                    removeDead();
                    postInvalidate();
                    sleep(10);
                } catch (Exception e) {
                }
            }
        }
    }

    public GameViewBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        CommonResource.set(context);
        mBall = Collections.synchronizedList(new ArrayList<Ball>());
    }

    @Override public
    boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            makeBall(event.getX(), event.getY());
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        imgBack = BitmapFactory.decodeResource(getResources(), R.drawable.field);
        imgBack = Bitmap.createScaledBitmap(imgBack, w, h, true);
        if (mThread == null) {
            mThread = new GameThreadBall();
            mThread.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mThread.canRun = false;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(imgBack, 0, 0, null);
        synchronized (mBall) {
            for (Ball tmp : mBall) {
                canvas.rotate(tmp.ang, tmp.x, tmp.y);
                canvas.drawBitmap(tmp.ball, tmp.x - tmp.r, tmp.y - tmp.r, null);
                canvas.rotate(-tmp.ang, tmp.x, tmp.y);
            }
        }
    }

    private synchronized void makeBall(float x, float y) {
        mBall.add(new Ball(w, h, x, y));
    }

    private synchronized void moveBall() {
        for (Ball tmp : mBall) {
            tmp.update();
        }
    }

    private synchronized void removeDead() {
        for (int i = mBall.size() - 1; i >= 0; i--) {
            if (mBall.get(i).isDead) {
                mBall.remove(i);
            }
        }
    }
}

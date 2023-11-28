package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import java.util.logging.LogRecord;

public class gameview extends View {
    private Context context;
    private Bitmap imgBack;
    private int w, h;
    private ArrayList<Bubble> mBubble = new ArrayList<Bubble>();

    public gameview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        imgBack = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        imgBack = Bitmap.createScaledBitmap(imgBack, w, h, true);
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    private void moveBubble() {
        for (Bubble tmp : mBubble) {
            tmp.update();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(imgBack, 0, 0, null);
        for (Bubble tmp : mBubble) {
            canvas.drawBitmap(tmp.bubble, tmp.x - tmp.bw, tmp.y - tmp.bw, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            mBubble.add(new Bubble(context, w, h, x, y));
        }
        return true;
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            moveBubble();
            invalidate();
            mHandler.sendEmptyMessageDelayed(0, 10);
        }
    };
}
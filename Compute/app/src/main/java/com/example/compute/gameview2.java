package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class gameview2 extends View {
    private Context context;
    private Bitmap imgBack;
    private int w, h;
    private Random rnd = new Random();
    private ArrayList<Bubble2> mBubble = new ArrayList<Bubble2>();
    static public ArrayList<smallBubble> mSmall = new ArrayList<smallBubble>();
    private Paint paint = new Paint();

    public gameview2(Context context, AttributeSet attrs) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(imgBack, 0, 0, null);
        for (Bubble2 tmp : mBubble) {
            canvas.drawBitmap(tmp.bubble, tmp.x - tmp.r, tmp.y - tmp.r, null);
        }
        for (smallBubble tmp: mSmall) {
            paint.setAlpha(tmp.alpha);
            canvas.drawBitmap(tmp.bubble, tmp.x - tmp.r, tmp.y - tmp.r, paint);
        }
    }

    private void makeBubble() {
        if (mBubble.size() < 20 && rnd.nextInt(1000) < 8) {
            mBubble.add(new Bubble2(context, w, h));
        }
    }

    private void moveBubble() {
        for (Bubble2 tmp : mBubble) {
            tmp.update();
        }
        for (smallBubble tmp2 : mSmall) {
            tmp2.update();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hitTest(event.getX(), event.getY());
        }
        return true;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Time.update();
            makeBubble();
            moveBubble();
            removeDead();

            invalidate();
            mHandler.sendEmptyMessageDelayed(0, 10);
        }
    };

    private void removeDead() {
        for (int i = mBubble.size() - 1; i >= 0; i--) {
            if (mBubble.get(i).isDead) mBubble.remove(i);
        }
        for (int i = mSmall.size() - 1; i >= 0; i--) {
            if (mSmall.get(i).isDead) {
                mSmall.remove(i);
            }
        }
    }

    private void hitTest(float x, float y) {
        for (Bubble2 tmp : mBubble) {
            if (tmp.hitTest(x, y)) break;
        }
    }
}

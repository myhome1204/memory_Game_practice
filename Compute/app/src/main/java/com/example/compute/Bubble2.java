package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.Random;

public class Bubble2 {
    public boolean isDead;
    private int scrW, scrH;
    private PointF dir = new PointF();
    private int speed;
    private Random rnd = new Random();
    private Context context;
    public float x, y;
    public int r;
    public Bitmap bubble;

    public Bubble2(Context context, int width, int height) {
        scrW = width;
        scrH = height;
        this.context = context;
        r = rnd.nextInt(71) + 50;
        bubble = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble);
        bubble = Bitmap.createScaledBitmap(bubble, r * 2, r * 2, true);
        initBubble();
    }

    public void initBubble() {
        speed = rnd.nextInt(51) + 150;
        double rad = Math.toRadians(rnd.nextInt(360));
        dir.x = (float) Math.cos(rad) * speed;
        dir.y = (float) -Math.sin(rad) * speed;
        x = rnd.nextInt(scrW - r * 4) + r * 2;
        y = rnd.nextInt(scrH - r * 4) + r * 2;

    }

    public void update() {

        x += dir.x * Time.deltaTime;
        y += dir.y * Time.deltaTime;
        if (x < r || x > scrW - r) {
            dir.x = -dir.x;
            x += dir.x * Time.deltaTime;
        }
        if (y < r || y > scrH - r) {
            dir.y = -dir.y;
            y += dir.y * Time.deltaTime;
        }
    }



}

package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;

import java.util.Random;

public class smallBubble {
    private int scrW, scrH;// 이동방향, 속도, 수명
    private PointF dir = new PointF();
    private int speed;
    private float life;
    public float x, y;
    public int r;
    public int alpha = 255;
    public boolean isDead;
    public Bitmap bubble;

    public smallBubble(Context context, int sw, int sh, float px, float py) {
        scrW = sw;
        scrH = sh;
        x = px;
        y = py;
        Random rnd = new Random();
        speed = 300;
        speed = rnd.nextInt(201) + 300;
        life = (rnd.nextInt(6) + 10) / 10f;
        double rad = Math.toRadians(rnd.nextInt(360));
        dir.x = (float) Math.cos(rad) * speed;
        dir.y = (float) -Math.sin(rad) * speed;
        r = rnd.nextInt(11) + 10;
        int n = rnd.nextInt(6);
        bubble = BitmapFactory.decodeResource(context.getResources(), R.drawable.b0 + n);
        bubble = Bitmap.createScaledBitmap(bubble, r * 2, r * 2, true);
    }

    public void update() {
        x += dir.x * Time.deltaTime;
        y += dir.y * Time.deltaTime;
        life -= Time.deltaTime;
        if (life < 0) {
            alpha -= 5;
            if (alpha < 0) alpha = 0;
        }
        if (alpha == 0 || x < -r || x > scrW + r || y < -r || y > scrH + r) {
            isDead = true;
        }
    }
}

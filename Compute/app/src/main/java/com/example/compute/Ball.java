package com.example.compute;

import android.graphics.Bitmap;
import android.graphics.PointF;

public class Ball {
    private int scrW, scrH;
    private float ground;
    private int speed = 300;
    private int rotAng = 120;
    private float gravity = 1500f;
    private float bounce = 0.8f;
    private PointF dir = new PointF();
    public float x, y;
    public int r;
    public float ang;
    public Bitmap ball;
    public boolean isDead;

    public Ball(int width, int height, float px, float py) {
        scrW = width;
        scrH = height;
        x = px;
        y = py;
        ball = CommonResource.ball;
        r = CommonResource.r;
        ground = scrH * 0.8f;
        dir.x = speed;
        dir.y = 0;
    }

    public void update() {
        ang += rotAng * TimeBall.deltaTime;
        dir.y += gravity * TimeBall.deltaTime;
        x += dir.x * TimeBall.deltaTime;
        y += dir.y * TimeBall.deltaTime;
        if (y > ground) {
            y = ground;
            dir.y = -dir.y * bounce;
        }
        isDead = (x > scrW + r);
    }
}

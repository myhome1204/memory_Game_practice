package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CommonResource {
    static public Bitmap ball;
    static public int r = 80;

    static public void set(Context context) {
        ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(ball, r * 2, r * 2, true);
    }
}

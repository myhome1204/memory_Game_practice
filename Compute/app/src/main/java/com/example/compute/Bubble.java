package com.example.compute;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Bubble {
    private int w,h;
    private int sx,sy;
    public Bitmap bubble;
    public int x,y,bw;
    public Bubble(Context context,int sw,int sh,int px  ,int py){
        w = sw;
        h= sh;
        x = px;
        y= py;
        Random rnd = new Random();
        bw = rnd.nextInt(101)+50;
        bubble = BitmapFactory.decodeResource(context.getResources(),R.drawable.bubble);
        bubble = Bitmap.createScaledBitmap(bubble,bw*2,bw*2,true);
        sx = rnd.nextInt(5)+1;
        sy = rnd.nextInt(5)+1;
        sx = rnd.nextInt(2) == 0?sx:-sx;
        sy = rnd.nextInt(2) == 0?sy:-sy;
    }
    public void update(){
        x += sx;
        y += sy;
        if(x < bw || x> w-bw){
            sx = -sx;
            x += sx;

        }
        if(y < bw || y> h-bw){
            sy = -sy;
            y += sy;

        }
    }
}

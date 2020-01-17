package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Casilla {
    public int x, y, w, h;
    public Bitmap sprite;
    public String state;
    // Monster

    public Casilla(int x, int y, int w, int h, Bitmap sp) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sprite = Bitmap.createScaledBitmap(sp, this.w, this.h * 2, false);
    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        cnv.drawBitmap(this.sprite, this.x, this.y, pnt);
    }
}

package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameButton {
    public int x, y, w, h;
    public Bitmap back;

    public GameButton(int x, int y, int w, int h, Bitmap b) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.back = b;
    }

    public void draw(Canvas cnv) {
        Bitmap bpm = Bitmap.createScaledBitmap(this.back, this.w, this.h, false);

        cnv.drawBitmap(bpm, this.x, this.y, null);
    }

    public boolean isPressed(int x, int y) {
        if(x > this.x && x < this.x + this.w && y > this.y && y < this.y + this.h) {
            return true;
        }
        return false;
    }
}

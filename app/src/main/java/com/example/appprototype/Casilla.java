package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Casilla {
    public int x, y, w, h;
    public Bitmap sprite;
    public String state;
    public Card card = null;
    public boolean selected;
    // Monster

    public Casilla(int x, int y, int w, int h, Bitmap sp) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sprite = Bitmap.createScaledBitmap(sp, this.w, this.h * 2, false);
        this.state = "empty";
        this.selected = false;
    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        cnv.drawBitmap(this.sprite, this.x, this.y, pnt);
        if(this.card != null) {
            int differ = this.card.cardSprite.getWidth() - this.w;
            int charSizeX = this.card.cardSprite.getWidth() - differ - 40;
            int charSizeY = this.card.cardSprite.getHeight() - differ - 40;
            int charPosY = charSizeY - this.h;
            Bitmap bpm = Bitmap.createScaledBitmap(this.card.cardSprite, charSizeX, charSizeY, false);
            cnv.drawBitmap(bpm, this.x + 20, this.y - (charSizeY / 2) + 35, null);
        }
        if(this.selected) {
            pnt.setStyle(Paint.Style.STROKE);
            pnt.setStrokeWidth(3);
            pnt.setColor(Color.parseColor("#FF0000"));
            cnv.drawRoundRect(this.x, this.y, this.x + this.w, this.y + this.h, 10 ,10, pnt);
        }
    }
}

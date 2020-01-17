package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Hashtable;
import java.util.Map;

public class Card {
    public int x, y, w, h;
    public String name;
    public int lvl = 0;
    public int coste;
    public Bitmap cardSprite;
    public Map<String, Integer> atributos;
    public String tipo;
    public boolean selected;
    public String color;

    public Card(int x, int y, int w, int h, String color, int lvl, Bitmap sprite, String name) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.lvl = lvl;
        this.cardSprite = sprite;
        this.name = name;
    }

    public void draw(Canvas cnv) {
        Bitmap sp = Bitmap.createScaledBitmap(this.cardSprite, this.w - 20, this.h - 20, false);
        Paint pnt = new Paint();
        pnt.setShadowLayer(1, 5, 5, Color.rgb(191, 191, 191));
        if(this.selected) {
            pnt.setColor(Color.parseColor("#ff00ff"));
        }else {
            pnt.setColor(Color.parseColor(this.color));
        }

        //pnt.setStyle(Paint.Style.STROKE);
        //pnt.setColor(Color.parseColor("#347474"));
        //canvas.drawRect(x, y, x + this.w, y + this.h, pnt);
        cnv.drawRoundRect(this.x, this.y, x + this.w, y + this.h, 6, 6, pnt);

        cnv.drawBitmap(sp, this.x + 10, this.y + 10, null);

        pnt = new Paint();

        pnt.setColor(Color.parseColor("#347474"));

        int circPosX = this.x + this.w - 30;
        int circPosY = this.y + this.h - 30;

        cnv.drawCircle(circPosX, circPosY, 25, pnt);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(35);
        cnv.drawText("" + this.lvl, circPosX - 10, circPosY + 15, pnt);

        pnt.setColor(Color.parseColor("#000000"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.name, this.x + 10, this.y + 25, pnt);
    }
}

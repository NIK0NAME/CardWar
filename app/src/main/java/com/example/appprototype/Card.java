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
    public int lvl;
    public int coste;
    public Bitmap cardSprite;
    public Map<String, Integer> atributos;
    public String tipo;
    public boolean selected;
    public String color;

    public Card(int x, int y, int w, int h, String color) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        pnt.setShadowLayer(1, 5, 5, Color.rgb(191, 191, 191));
            pnt.setColor(Color.parseColor(this.color));

            //pnt.setStyle(Paint.Style.STROKE);
            //pnt.setColor(Color.parseColor("#347474"));
            //canvas.drawRect(x, y, x + this.w, y + this.h, pnt);
            cnv.drawRoundRect(this.x, this.y, x + this.w, y + this.h, 6, 6, pnt);
    }
}

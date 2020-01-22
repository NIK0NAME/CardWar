package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Hashtable;
import java.util.Map;

public class Card {
    public int x, y, w, h;
    public int coste;
    public Bitmap cardSprite;
    public Map<String, Integer> atributos;
    public String tipo;
    public boolean selected;
    public String color;
    public Monster monster;
    public Map<String, Integer> atributs;
    public Map<String, Integer> counters;
    public int mana = 4;

    public Card(int x, int y, int w, int h, String color, int lvl, Bitmap sprite, String name, int damage, int life) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.cardSprite = sprite;
        this.monster = new Monster(sprite, "active", lvl, name, damage, life);
    }

    public void draw(Canvas cnv) {
        Bitmap sp = Bitmap.createScaledBitmap(this.cardSprite, this.w - 20, this.h - 20, false);
        Paint pnt = new Paint();

        if(this.selected) {
            pnt.setShadowLayer(1, 0, 8, Color.rgb(0, 0, 0));
            pnt.setColor(Color.parseColor("#ff00ff"));
        }else {
            pnt.setColor(Color.parseColor(this.color));
        }

        //pnt.setStyle(Paint.Style.STROKE);
        //pnt.setColor(Color.parseColor("#347474"));
        //canvas.drawRect(x, y, x + this.w, y + this.h, pnt);
        //Fondo carta
        cnv.drawRoundRect(this.x, this.y, x + this.w, y + this.h, 6, 6, pnt);

        //Monstruo carta
        cnv.drawBitmap(sp, this.x + 10, this.y + 10, pnt);

        pnt = new Paint();
        pnt.setColor(Color.parseColor("#3498db"));
        pnt.setAntiAlias(true);

        //name
        pnt.setColor(Color.parseColor("#000000"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.monster.name, this.x + 10, this.y + 25, pnt);

        //BordeCarta
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setColor(Color.BLACK);
        pnt.setStrokeWidth(1);
        cnv.drawRoundRect(this.x, this.y, this.x + this.w, this.y + this.h, 6, 6, pnt);


        pnt.setStyle(Paint.Style.FILL);
        pnt.setColor(Color.parseColor("#3498db"));
        //mana
        int circPosX = this.x + this.w - 30;
        int circPosY = this.y - 10;

        cnv.drawCircle(circPosX, circPosY, 25, pnt);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(35);
        cnv.drawText("" + this.mana, circPosX - 10, circPosY + 15, pnt);

        //Damage and life

        int xposDmg = this.x + 25;
        int yposDmg = this.y + this.w + 35;

        pnt.setColor(Color.parseColor("#E74C3C"));
        cnv.drawCircle(xposDmg, yposDmg, 20, pnt);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.monster.damage, xposDmg - 5, yposDmg + 10, pnt);

        int xposLife = this.x + this.w - 25;
        int yposLife = this.y + this.w + 35;

        pnt.setColor(Color.parseColor("#1ABC9C"));
        cnv.drawCircle(xposLife, yposLife, 20, pnt);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.monster.life, xposLife - 15, yposLife + 10, pnt);
    }
}

package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monster {
    public int x, y, w, h;
    public Bitmap sprite;
    public Map<String, List<Bitmap>> anims;
    public List<Bitmap> currentAnim;
    public int frameCount = 0;
    public String type;
    public int lvl;
    public String name;
    public int damage;
    public int life;
    public String state; // ready, attack,

    public Monster(Bitmap sp, String type, int lvl, String name, int damage, int life) {
        this.sprite = sp;
        this.type = type;
        this.lvl = lvl;
        this.name = name;
        this.damage = damage;
        this.life = life;
        this.anims = new HashMap<>();
        this.currentAnim = new ArrayList<>();
        this.state = "created";
    }

    public void setAnimation(String name) {
        if(this.anims.get(name) != null) {
            this.currentAnim = new ArrayList<>((ArrayList)this.anims.get(name));
        }
        this.frameCount = 0;
    }

    public void addAnimation(String name, ArrayList<Bitmap> frames) {
        this.anims.put(name, frames);
    }

    public void changeAnimFrame() {
        if(this.currentAnim.size() != 0) {
            this.frameCount++;
            if(this.frameCount >= this.currentAnim.size()) {
                this.frameCount = 0;
            }
        }
    }

    public void draw(Canvas cnv) {
        Bitmap bpm = Bitmap.createScaledBitmap(this.sprite, this.w, this.h, false);
        if(this.currentAnim.size() != 0) {
            Bitmap framee = Bitmap.createScaledBitmap(this.currentAnim.get(this.frameCount), this.w, this.h, false);
            cnv.drawBitmap(framee, this.x, this.y, null);
        }else {
            cnv.drawBitmap(bpm, this.x, this.y, null);
        }

        //Damage and life
        Paint pnt = new Paint();
        Paint strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);

        int xposDmg = this.x + 25;
        int yposDmg = this.y + this.w + 55;

        pnt.setColor(Color.parseColor("#e53935"));
        cnv.drawCircle(xposDmg, yposDmg, 20, pnt);
        cnv.drawCircle(xposDmg, yposDmg, 20, strokePaint);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.damage, xposDmg - 5, yposDmg + 10, pnt);

        int xposLife = this.x + this.w - 25;
        int yposLife = this.y + this.w + 55;

        pnt.setColor(Color.parseColor("#1ABC9C"));
        cnv.drawCircle(xposLife, yposLife, 20, pnt);
        cnv.drawCircle(xposLife, yposLife, 20, strokePaint);

        pnt.setColor(Color.parseColor("#ffffff"));
        pnt.setTextSize(25);
        cnv.drawText("" + this.life, xposLife - 15, yposLife + 10, pnt);
    }
}

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
    public Monster monster = null;

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
        //  Draw selected rect
        if(this.selected) {
            pnt.setStyle(Paint.Style.STROKE);
            pnt.setStrokeWidth(3);
            pnt.setColor(Color.parseColor("#FF0000"));
            cnv.drawRoundRect(this.x + 20, this.y + 20, this.x + this.w - 20, this.y + this.h - 20, 10 ,10, pnt);
        }
        //  Draw the monster
        if(this.monster != null) {

            if(this.monster.state.equals("attack")) {
                pnt.setStyle(Paint.Style.FILL);

                pnt.setColor(Color.rgb( 52, 152, 219));
                pnt.setAlpha(125);
                //pnt.setStrokeWidth(4);
                cnv.drawRoundRect(this.x + 18, this.y + 18, this.x + this.w - 18, this.y + this.h - 18, 10 ,10, pnt);
            }

            this.monster.draw(cnv);
        }

        //  Life counter
    }

    public void update() {
        if(this.monster != null) {
            this.monster.changeAnimFrame();
        }
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
        Bitmap sp = this.monster.sprite;
        int differ = sp.getWidth() - this.w;
        int charSizeX = sp.getWidth() - differ - 40;
        int charSizeY = sp.getHeight() - differ - 40;
        int charPosY = this.y - (charSizeY / 2) + 35;

        this.monster.x = this.x + 20;
        this.monster.y = charPosY;
        this.monster.w = charSizeX;
        this.monster.h = charSizeY;

        this.state = "full";
    }
}

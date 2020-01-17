package com.example.appprototype;

import android.graphics.Bitmap;

public class Monster {
    public int x, y, w, h;
    public Bitmap sprite;
    public String type;
    public int lvl;
    public String name;
    public int damage;
    public int life;

    public Monster(Bitmap sp, String type, int lvl, String name, int damage, int life) {
        this.sprite = sp;
        this.type = type;
        this.lvl = lvl;
        this.name = name;
        this.damage = damage;
        this.life = life;
    }
}

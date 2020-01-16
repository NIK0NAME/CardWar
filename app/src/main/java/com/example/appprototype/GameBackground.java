package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameBackground extends Sprite {

    protected SuperficieJuego gameSurface;
    private long lastDrawNanoTime = -1;
    public int w, h;
    public boolean selected = false;

    public GameBackground(SuperficieJuego gameSurface, Bitmap image, int x, int y, int w, int h) {
        super( 1, 1, image, x, y);

        this.gameSurface = gameSurface;
        this.w = w;
        this.h = h;
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.image;
        Paint pnt = new Paint();
        if(this.image == null) {
            pnt.setColor(Color.parseColor("#63b7af"));
            pnt.setShadowLayer(1, 5, 5, Color.BLACK);
            canvas.drawRect(x, y, x + this.w, y + this.h, pnt);
        }else canvas.drawBitmap(bitmap, x, y, null);
        // Last draw time.
        this.lastDrawNanoTime = System.nanoTime();
    }
}

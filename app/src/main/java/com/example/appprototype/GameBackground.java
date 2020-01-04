package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameBackground extends GameObject {

    protected SuperficieJuego gameSurface;
    private long lastDrawNanoTime =-1;

    public GameBackground(SuperficieJuego gameSurface, Bitmap image, int x, int y) {
        super(image, 1, 1, x, y);

        this.gameSurface= gameSurface;
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.image;
        canvas.drawBitmap(bitmap,x, y, null);
        // Last draw time.
        this.lastDrawNanoTime= System.nanoTime();
    }
}

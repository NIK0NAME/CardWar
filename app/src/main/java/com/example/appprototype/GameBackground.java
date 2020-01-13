package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameBackground extends Sprite {

    protected SuperficieJuego gameSurface;
    private long lastDrawNanoTime = -1;

    public GameBackground(SuperficieJuego gameSurface, Bitmap image, int x, int y) {
        super( 1, 1, image, x, y);

        this.gameSurface = gameSurface;
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.image;
        Paint pnt = new Paint();
        canvas.drawBitmap(bitmap, x, y, null);
        // Last draw time.
        this.lastDrawNanoTime= System.nanoTime();
    }
}

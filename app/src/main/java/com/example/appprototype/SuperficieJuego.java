package com.example.appprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*
* Clase que contiene todos los elementos del juego
*
*/

public class SuperficieJuego extends SurfaceView implements SurfaceHolder.Callback {

    protected GameBackground backgrund;

    public SuperficieJuego(Context context) {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);
    }

    //Funcion que actualiza el estado de los elementos de la superfice
    public void  update(Canvas cnv) {
        super.draw(cnv);
    }

    //Funcion setup, se ejecuta al principio del juego y es donde se inicializan todas las cosas
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.flat_night_4_bg);
        this.backgrund = new GameBackground(this, chibiBitmap1, 0,0);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}

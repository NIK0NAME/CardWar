package com.example.appprototype;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/*
* Clase que contiene todos los elementos del juego
*
*/

public class SuperficieJuego extends SurfaceView implements SurfaceHolder.Callback {

    protected GameBackground backgrund;
    protected  GameThread gameThread;
    protected int width;
    protected int height;
    protected  GameScene initSceene;
    public Context cnt;
    public View v;
    public RunSceene runSceene;

    // Lista casillas
    // Jugador
    // Escena
    //


    public SuperficieJuego(Context context, int width, int height, View v) {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        this.width = width;
        this.height = height;

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            this.height -= resources.getDimensionPixelSize(resourceId) - 25; /// 2 - 60;
        }
        this.cnt = context;
        this.v = v;
    }

    public void makeInitSceene() {
        this.runSceene = new RunSceene(this.cnt, this.width, this.height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //hideSystemUI();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();
            this.runSceene.touchEvento(x, y);
            return true;
        }
        return false;
    }


    //Funcion que actualiza el estado de los elementos de la superfice
    public void  update() {
        this.runSceene.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        this.backgrund.draw(canvas);

        this.runSceene.draw(canvas);

        Paint p = new Paint();
        p.setColor(Color.RED);
    }

    //Funcion setup, se ejecuta al principio del juego y es donde se inicializan todas las cosas
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.flat_night_4_bg);
        //chibiBitmap1 = Bitmap.createScaledBitmap(chibiBitmap1, 0, this.height, false);
        this.backgrund = new GameBackground(this, null, 0,0, this.width, this.height);

        makeInitSceene();

        this.gameThread = new GameThread(this, surfaceHolder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getRootView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}

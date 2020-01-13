package com.example.appprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

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
    public List<Sprite> campoBattalla;

    // Lista casillas
    // Jugador
    // Escena
    //


    public SuperficieJuego(Context context, int width, int height) {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        this.width = width;
        this.height = height;
    }

    public void makeInitSceene() {
        calcularSuperficieJuego();
    }

    public void calcularSuperficieJuego() {
        // cartasAbajo
        // campoBattalla
        // tamañoCasilla
        // tamañoCarta
        //
        int cardsDisplayerHeight,
            battlefieldWidth,
            battlefieldHeight;
        int tilesNumX = 6;
        int tilesNumY = 4;
        int tileInitialSize = 400;
        int initialBattlefieldPosY = 200;
        int initialBattlefieldPosX = 100;

        cardsDisplayerHeight = 400;
        int sitioDisponible = this.height - cardsDisplayerHeight - initialBattlefieldPosY;
        int sitioX = this.width - initialBattlefieldPosX;
        battlefieldWidth = tilesNumX * tileInitialSize;
        battlefieldHeight = tilesNumY * tileInitialSize * 2 + 1;
        while(battlefieldWidth > sitioX || battlefieldHeight > sitioDisponible) {
            tileInitialSize--;
            battlefieldWidth = tilesNumX * tileInitialSize;
            battlefieldHeight = tilesNumY * tileInitialSize * 2;
        }

        initialBattlefieldPosX = (this.width - battlefieldWidth) / 2;

        campoBattalla = new ArrayList<>();

        Bitmap tilea = BitmapFactory.decodeResource(this.getResources(),R.drawable.tilea);
        Bitmap tileb = BitmapFactory.decodeResource(this.getResources(),R.drawable.tileb);


        tilea = Bitmap.createScaledBitmap(tilea, tileInitialSize, tileInitialSize, false);
        tileb = Bitmap.createScaledBitmap(tileb, tileInitialSize, tileInitialSize, false);

        int posX = initialBattlefieldPosX;
        int posY = initialBattlefieldPosY;

        for(int i = 0; i < tilesNumY; i++) {
            for(int n = 0; n < tilesNumX; n++) {
                if(i % 2 == 0) {
                    if(n % 2 == 0) {
                        campoBattalla.add(new GameBackground(this, tilea, posX, posY));
                    }else {
                        campoBattalla.add(new GameBackground(this, tileb, posX, posY));
                    }
                }else {
                    if(n % 2 != 0) {
                        campoBattalla.add(new GameBackground(this, tilea, posX, posY));
                    }else {
                        campoBattalla.add(new GameBackground(this, tileb, posX, posY));
                    }
                }
                posX += tileInitialSize;

            }
            posY += tileInitialSize;
            posX = initialBattlefieldPosX;
        }

        posY += tileInitialSize;

        posX = initialBattlefieldPosX;
        for(int i = 0; i < tilesNumY; i++) {
            for(int n = 0; n < tilesNumX; n++) {
                if(i % 2 ==  0 || n % 2 != 0) {
                    campoBattalla.add(new GameBackground(this, tilea, posX, posY));
                }else {
                    campoBattalla.add(new GameBackground(this, tileb, posX, posY));
                }
                posX += tileInitialSize;

            }
            posY += tileInitialSize;
            posX = initialBattlefieldPosX;
        }

    }

    //Funcion que actualiza el estado de los elementos de la superfice
    public void  update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.backgrund.draw(canvas);
        for(Sprite sp : campoBattalla) {
            GameBackground itm = (GameBackground) sp;
            itm.draw(canvas);
        }
        /*Paint t = new Paint();
        t.setTextSize(70);
        canvas.drawText("secreen sixe: " + this.width + " - " + this.height,
                (width / 2 - 450) * 1f,
                height / 2 * 1f,
                t);
        t.setColor(-1);
        canvas.drawRect(0, height - 200, width, height, t);
        int pos = 0;
        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0) {
                t.setColor(Color.RED);
            }else {
                t.setColor(Color.BLUE);
            }
            t.setStrokeWidth(2);
            canvas.drawRect(pos, height / 6  * 5, pos + width / 5, height, t);
            pos += width / 5;
        }*/
    }

    //Funcion setup, se ejecuta al principio del juego y es donde se inicializan todas las cosas
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.flat_night_4_bg);
        this.backgrund = new GameBackground(this, chibiBitmap1, 0,0);

        makeInitSceene();

        this.gameThread = new GameThread(this, surfaceHolder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}

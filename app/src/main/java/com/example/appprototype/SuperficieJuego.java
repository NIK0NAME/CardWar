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
    public List<Sprite> campoBattalla;
    public Context cnt;
    public View v;
    public List<Sprite> cartas;

    public CardDisplayer cardDisplayer;
    public Battlefield battlefield1, battlefield2;

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
        int tilesNumY = 3;
        int tileInitialSize = 300;
        int initialBattlefieldPosY = 150;
        int initialBattlefieldPosX = 50;

        cardsDisplayerHeight = 350;
        int sitioDisponible = this.height - cardsDisplayerHeight - initialBattlefieldPosY;
        int sitioX = this.width - initialBattlefieldPosX;
        battlefieldWidth = tilesNumX * tileInitialSize;
        battlefieldHeight = tilesNumY * tileInitialSize * 2 + (tileInitialSize * 3);
        while(battlefieldWidth > sitioX || battlefieldHeight > sitioDisponible) {
            tileInitialSize--;
            battlefieldWidth = tilesNumX * tileInitialSize;
            battlefieldHeight = tilesNumY * tileInitialSize * 2 + (tileInitialSize * 3);
        }

        initialBattlefieldPosX = (this.width - battlefieldWidth) / 2;

        //cardsDisplayerHeight = tileInitialSize * 2;

        campoBattalla = new ArrayList<>();
        cartas = new ArrayList<>();

        Bitmap tilea = BitmapFactory.decodeResource(this.getResources(), R.drawable.tilea);
        Bitmap tileb = BitmapFactory.decodeResource(this.getResources(), R.drawable.norm_battlefield_tile);
        Bitmap tile_card = BitmapFactory.decodeResource(this.getResources(), R.drawable.cards_back);
        Bitmap tile_card_background = BitmapFactory.decodeResource(this.getResources(), R.drawable.card_background);
        Bitmap end_tile = BitmapFactory.decodeResource(this.getResources(), R.drawable.end_battlefield_tile);


        tilea = Bitmap.createScaledBitmap(tilea, tileInitialSize, tileInitialSize, false);
        tileb = Bitmap.createScaledBitmap(tileb, tileInitialSize, tileInitialSize, false);
        tile_card = Bitmap.createScaledBitmap(tile_card, this.width, cardsDisplayerHeight, false);
        end_tile = Bitmap.createScaledBitmap(end_tile, tileInitialSize, tileInitialSize * 2, false);
        //tile_card_background = Bitmap.createScaledBitmap(tile_card_background, tileInitialSize, tileInitialSize * 2, false);

        List<Bitmap> catillaSp = new ArrayList<>();
        catillaSp.add(end_tile);

        this.battlefield1 = new Battlefield(initialBattlefieldPosX,
                initialBattlefieldPosY,
                battlefieldWidth,
                battlefieldHeight,
                tileInitialSize,
                catillaSp);
        this.battlefield2 = new Battlefield(initialBattlefieldPosX,
                initialBattlefieldPosY + (4 * tileInitialSize) + tileInitialSize / 2,
                battlefieldWidth,
                battlefieldHeight,
                tileInitialSize,
                catillaSp);
        this.cardDisplayer = new CardDisplayer(0, this.height - cardsDisplayerHeight, this.width, cardsDisplayerHeight, this.cnt);

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //hideSystemUI();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();

            // Toast.makeText(this.cnt, "Click done in " + x + "x" + y, Toast.LENGTH_LONG).show();
            // checkCards(x, y);
            this.cardDisplayer.checkCardSelection(x, y);
            this.battlefield2.mostrarCasillaDisponible(this.cardDisplayer.selectedCard);

            return true;
        }
        return false;
    }

    public void checkCards(int x, int y) {
        for(int i = cartas.size() - 1; i >= 0; i--) {
            GameBackground itm = (GameBackground) cartas.get(i);
            if(x > itm.x && x < itm.x + itm.width && y > itm.y && x < itm.y + itm.height) {
                if(itm.selected) {
                    itm.selected = false;
                    itm.y += 50;
                }else {
                    itm.y -= 50;
                    itm.selected = true;
                }
            }
        }
    }

    //Funcion que actualiza el estado de los elementos de la superfice
    public void  update() {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        this.backgrund.draw(canvas);
        this.battlefield1.draw(canvas);
        this.battlefield2.draw(canvas);
        for(Sprite sp : cartas) {
            GameBackground itm = (GameBackground) sp;
            itm.draw(canvas);
        }
        Paint p = new Paint();
        p.setColor(Color.RED);

        this.cardDisplayer.draw(canvas);
        /*canvas.drawRect(0, this.height - 200, 200, this.height, p);
        canvas.drawRect(0, 0, 200, this.height - 250, p);*/
    }

    //Funcion setup, se ejecuta al principio del juego y es donde se inicializan todas las cosas
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap chibiBitmap1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.flat_night_4_bg);
        //chibiBitmap1 = Bitmap.createScaledBitmap(chibiBitmap1, 0, this.height, false);
        this.backgrund = new GameBackground(this, null, 0,0, this.width, this.height);

        makeInitSceene();

        //makeScreenUIgoFuckAway();

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

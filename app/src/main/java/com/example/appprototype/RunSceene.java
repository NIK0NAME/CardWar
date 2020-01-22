package com.example.appprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunSceene {
    public String sceeneState;
    public int round = 0;
    public CardDisplayer cardDisplayer;
    public Battlefield campoAliado, campoEnemigo;
    public int w, h;
    public Context cnt;
    public GameButton nextRound;

    public RunSceene(Context cnt, int w, int h) {
        this.cnt = cnt;
        this.w = w;
        this.h = h;
        this.sceeneState = "initialize";
        this.initializeGame();
    }

    public void initializeGame() {
        int cardsDisplayerHeight,
                battlefieldWidth,
                battlefieldHeight;
        int tilesNumX = 6;
        int tilesNumY = 3;
        int tileInitialSize = 300;
        int initialBattlefieldPosY = 150;
        int initialBattlefieldPosX = 50;

        cardsDisplayerHeight = 350;
        int sitioDisponible = this.h - cardsDisplayerHeight - initialBattlefieldPosY;
        int sitioX = this.w - initialBattlefieldPosX;
        battlefieldWidth = tilesNumX * tileInitialSize;
        battlefieldHeight = tilesNumY * tileInitialSize * 2 + (tileInitialSize * 3);
        while(battlefieldWidth > sitioX || battlefieldHeight > sitioDisponible) {
            tileInitialSize--;
            battlefieldWidth = tilesNumX * tileInitialSize;
            battlefieldHeight = tilesNumY * tileInitialSize * 2 + (tileInitialSize * 3);
        }

        initialBattlefieldPosX = (this.w - battlefieldWidth) / 2;

        Bitmap end_tile = BitmapFactory.decodeResource(this.cnt.getResources(), R.drawable.end_battlefield_tile);
        Bitmap readyButton = BitmapFactory.decodeResource(this.cnt.getResources(), R.drawable.ready_button);

        end_tile = Bitmap.createScaledBitmap(end_tile, tileInitialSize, tileInitialSize * 2, false);
        //tile_card_background = Bitmap.createScaledBitmap(tile_card_background, tileInitialSize, tileInitialSize * 2, false);

        List<Bitmap> catillaSp = new ArrayList<>();
        catillaSp.add(end_tile);

        this.campoEnemigo = new Battlefield(initialBattlefieldPosX,
                initialBattlefieldPosY,
                battlefieldWidth,
                battlefieldHeight,
                tileInitialSize,
                catillaSp);
        this.campoAliado = new Battlefield(initialBattlefieldPosX,
                initialBattlefieldPosY + (4 * tileInitialSize) + tileInitialSize / 2,
                battlefieldWidth,
                battlefieldHeight,
                tileInitialSize,
                catillaSp);
        this.cardDisplayer = new CardDisplayer(0, this.h - cardsDisplayerHeight, this.w, cardsDisplayerHeight, this.cnt);

        this.sceeneState = "primerTurnoAliado";

        this.nextRound = new GameButton(this.w - 170, initialBattlefieldPosY + battlefieldHeight/2 - 120, 150, 90, readyButton);

        primerTurnoEnemigo();
    }

    public void update() {
        //stateMachine();
        this.campoAliado.update();
    }

    public void primerTurnoAliado() {
        this.sceeneState = "finTurnoAliado";
        this.cardDisplayer.addCard();
        this.campoAliado.readyToRound();
        this.round++;
    }

    public void primerTurnoEnemigo() {
        final Context cntt = this.cnt;
        final Battlefield camp = this.campoEnemigo;
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap cardSp6 = BitmapFactory.decodeResource(cntt.getResources(), R.drawable.monster2);
                Random rnd = new Random();
                for(int i = 0; i < 4; i++) {
                    Monster m = new Monster(cardSp6, "monster", 3, "jesus", rnd.nextInt(6), rnd.nextInt(3));
                    camp.addCarta(m, rnd.nextInt(camp.casillas.size() - 1));
                    try {
                        Thread.sleep(300);
                    }catch (Exception ex) {

                    }
                }

                primerTurnoAliado();
            }
        });
        h.start();
    }

    public void stateMachine() {
        switch (this.sceeneState) {
            case "primerTurnoRondaAliado": break;
            case "primerTurnoRondaEnemigo": break;
            case "turnoRondaAliado": break;
            case "turnoRondaEnemigo": break;
        }
    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        pnt.setColor(Color.BLACK);
        pnt.setTextSize(80);
        cnv.drawText("" + this.round, this.w / 2, 100, pnt);

        this.campoEnemigo.draw(cnv);

        this.campoAliado.draw(cnv);
        this.nextRound.draw(cnv);
        this.cardDisplayer.draw(cnv);
    }

    public void touchEvento(int x, int y) {
        if(this.nextRound.isPressed(x, y)) {
            primerTurnoEnemigo();
        }

        if(this.campoEnemigo.cmporbarCarta(x, y, this.campoAliado)) {

        }else if(this.campoAliado.comprobarCasilla(x, y, this.cardDisplayer)) {
            this.cardDisplayer.removeSelected();
        }
        this.cardDisplayer.checkCardSelection(x, y);

        this.campoAliado.mostrarCasillaDisponible(this.cardDisplayer.selectedCard);
    }

}

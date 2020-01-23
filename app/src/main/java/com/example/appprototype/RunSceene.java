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
    public List<Float> linePnts = null;
    public boolean lineVisible = false;

    public RunSceene(Context cnt, int w, int h) {
        this.cnt = cnt;
        this.w = w;
        this.h = h;
        this.sceeneState = "initialize";
        this.initializeGame();
    }

    public void initializeGame() {
        this.linePnts = new ArrayList<>();
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

        firstShow();

    }

    public void update() {
        //stateMachine();
        this.campoAliado.update();
    }

    public void firstShow() {
        final Battlefield camAlie = this.campoAliado;
        final Battlefield camEnem = this.campoEnemigo;
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                int finalUbicationA = camAlie.x;
                int finalUbicationE = camEnem.x;
                int startA = -camAlie.w;
                int startB = camAlie.w + camEnem.tamCasilla;
                camAlie.x = startA;
                camEnem.x = startB;
                camAlie.updateCampo();
                camEnem.updateCampo();
                try {
                    Thread.sleep(500);
                }catch (Exception ex) {

                }
                while(startA < finalUbicationA) {
                    startA += 2;
                    startB -= 2;
                    camAlie.x = startA;
                    camEnem.x = startB;
                    camAlie.updateCampo();
                    camEnem.updateCampo();
                    try {
                        Thread.sleep(2);
                    }catch (Exception ex) {

                    }
                }
                camAlie.x = finalUbicationA;
                camEnem.x = finalUbicationE;
                camAlie.updateCampo();
                camEnem.updateCampo();
                try {
                    Thread.sleep(100);
                }catch (Exception ex) {

                }
                primerTurnoEnemigo();
            }
        });

        h.start();
    }

    public void primerTurnoAliado() {
        this.nextRound.visible = true;
        this.sceeneState = "alieAtack";
        this.cardDisplayer.addCard();
        this.campoAliado.readyToRound();
        this.round++;
    }

    public void turnoAliado() {
        this.lineVisible = false;
        this.nextRound.visible = true;
        this.sceeneState = "alieAtack";
        this.cardDisplayer.addCard();
        this.campoAliado.readyToRound();
    }

    public void primerTurnoEnemigo() {
        this.campoAliado.freeCellSelection();
        this.nextRound.visible = false;
        this.sceeneState = "enemyAtack";
        final Context cntt = this.cnt;
        final Battlefield camp = this.campoEnemigo;
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap cardSp6 = BitmapFactory.decodeResource(cntt.getResources(), R.drawable.monster3);
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
        if(this.lineVisible && this.linePnts.size() > 0) {
            pnt.setColor(Color.RED);
            pnt.setStrokeWidth(5);
            cnv.drawLine(this.linePnts.get(0), this.linePnts.get(1), this.linePnts.get(2), this.linePnts.get(3), pnt);
        }
        this.nextRound.draw(cnv);
        this.cardDisplayer.draw(cnv);
    }

    public void turnoEnemigo() {
        this.campoAliado.freeCellSelection();
        this.nextRound.visible = false;
        this.sceeneState = "enemyAtack";
        final Context cntt = this.cnt;
        final Battlefield camp = this.campoEnemigo;
        final Battlefield alie = this.campoAliado;
        final CardDisplayer disp = this.cardDisplayer;
        this.lineVisible = true;

        final List<Float> line = this.linePnts;
        Thread h = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap cardSp6 = BitmapFactory.decodeResource(cntt.getResources(), R.drawable.monster2);
                Random rnd = new Random();
                for(Casilla cs : camp.casillas) {
                    if(cs.state.equals("full")) {

                        cs.selected = true;
                        try {
                            Thread.sleep(700);
                        }catch (Exception ex) {

                        }
                        Casilla alieCs = alie.getMonster();
                        if(alieCs != null) {
                            alieCs.selected = true;
                            alieCs.monster.life -= cs.monster.damage;
                            cs.monster.life -= alieCs.monster.damage;
                            line.add((float)(cs.x + cs.w / 2));
                            line.add((float)(cs.y + cs.h));
                            line.add((float)(alieCs.x + alieCs.w / 2));
                            line.add((float)(alieCs.y));
                            if(alieCs.monster.life <= 0) {
                                alieCs.monster = null;
                                alieCs.state = "empty";
                            }
                            if(cs.monster.life <= 0) {
                                cs.monster = null;
                                cs.state = "empty";
                            }
                            try {
                                Thread.sleep(1000);
                            }catch (Exception ex) {
                            }
                            alieCs.selected = false;
                        }else {
                            disp.life -= cs.monster.damage;
                            try {
                                Thread.sleep(1000);
                            }catch (Exception ex) {
                            }
                        }
                        line.clear();
                        cs.selected = false;
                    }
                }

                turnoAliado();
            }
        });
        h.start();
    }

    public void touchEvento(int x, int y) {
        if(this.nextRound.isPressed(x, y)) {
            if(this.campoEnemigo.allDead()) {
                primerTurnoEnemigo();
            }else {
                turnoEnemigo();
            }

        }

        if(this.sceeneState.equals("alieAtack")) {
            if(this.campoEnemigo.cmporbarCarta(x, y, this.campoAliado)) {

            }else if(this.campoAliado.comprobarCasilla(x, y, this.cardDisplayer)) {
                this.cardDisplayer.removeSelected();
            }
        }

        this.cardDisplayer.checkCardSelection(x, y);

        if(this.sceeneState.equals("alieAtack")) {
            this.campoAliado.mostrarCasillaDisponible(this.cardDisplayer.selectedCard);
        }

    }

}

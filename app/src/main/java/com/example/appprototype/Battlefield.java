package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    public int x, y, w, h, tamCasilla, wBattlefield, hBattlefield;
    public List<Casilla> casillas;
    public List<Bitmap> spriteCasilla;
    public Casilla selCasilla;

    public Battlefield(int x, int y, int w, int h, int tamCasilla, List<Bitmap> spriteCasilla) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tamCasilla = tamCasilla;
        this.spriteCasilla = spriteCasilla;
        this.wBattlefield = 6;
        this.hBattlefield = 3;
        this.casillas = new ArrayList<>();
        this.inicializarCampo();
        this.selCasilla = null;
    }

    public void inicializarCampo(){
        int posX = this.x;
        int posY = this.y;
        for(int i = 0; i < hBattlefield; i++) {
            for(int n = 0; n < wBattlefield; n++) {
                Casilla cs = new Casilla(posX, posY, this.tamCasilla, this.tamCasilla, this.spriteCasilla.get(0));
                if(i == 0) {
                    //cs.state = "disabled";
                }
                this.casillas.add(cs);

                posX += this.tamCasilla;
            }
            posY += this.tamCasilla;
            posX = this.x;
        }
    }

    public void addCarta(Monster m, int pos){
        this.casillas.get(pos).setMonster(m);
    }

    public void mostrarCasillaDisponible(Card c) {
        if(c != null) {
            for(int i = 0; i < this.casillas.size(); i++) {
                Casilla cs = this.casillas.get(i);
                if(cs.state.equals("empty")) {
                    cs.selected = true;
                }else {
                    cs.selected = false;
                }
            }
        }
    }

    public boolean comprobarCasilla(int x, int y, CardDisplayer c) {
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);

            cs.selected = false;
            this.selCasilla = null;
        }
        if(c.selectedCard == null) {
            for(int i = 0; i < this.casillas.size(); i++) {
                Casilla cs = this.casillas.get(i);
                if (x > cs.x && x < cs.x + cs.w && y > cs.y && y < cs.y + cs.h) {
                    if (cs.state.equals("full")) {
                        cs.selected = true;
                        this.selCasilla = cs;
                        return false;
                    }
                }
            }
            return false;
        }
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);
            if(x > cs.x && x < cs.x + cs.w && y > cs.y && y < cs.y + cs.h) {
                if(cs.state.equals("empty") && c.mana >= c.selectedCard.mana) {
                    cs.state = "full";
                    cs.setMonster(c.selectedCard.monster);
                    c.mana -= c.selectedCard.mana;
                    return true;
                }
            }
        }
        return false;
    }

    public void update() {
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);
            cs.update();
        }
    }

    public void draw(Canvas canvas){
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);
            cs.draw(canvas);
        }
    }

    public boolean cmporbarCarta(int x, int y, Battlefield campoAliado) {
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);

            cs.selected = false;
            this.selCasilla = null;
        }
        if(campoAliado.selCasilla == null) {
            /*for(int i = 0; i < this.casillas.size(); i++) {
                Casilla cs = this.casillas.get(i);
                if (x > cs.x && x < cs.x + cs.w && y > cs.y && y < cs.y + cs.h) {
                    if (cs.state.equals("full")) {
                        cs.selected = true;
                        return false;
                    }
                }
            }*/
            return false;
        }
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);
            if(x > cs.x && x < cs.x + cs.w && y > cs.y && y < cs.y + cs.h) {
                if(cs.state.equals("full")) {
                    cs.monster.life -= campoAliado.selCasilla.monster.damage;
                    if(cs.monster.life <= 0) {
                        cs.monster = null;
                        cs.state = "empty";
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

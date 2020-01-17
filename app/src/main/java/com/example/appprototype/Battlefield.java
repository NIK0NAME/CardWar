package com.example.appprototype;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Battlefield {
    public int x, y, w, h, tamCasilla, wBattlefield, hBattlefield;
    public List<Casilla> casillas;
    public List<Bitmap> spriteCasilla;

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
    }

    public void inicializarCampo(){
        int posX = this.x;
        int posY = this.y;
        for(int i = 0; i < hBattlefield; i++) {
            for(int n = 0; n < wBattlefield; n++) {

                this.casillas.add(new Casilla(posX, posY, this.tamCasilla, this.tamCasilla, this.spriteCasilla.get(0)));

                posX += this.tamCasilla;
            }
            posY += this.tamCasilla;
            posX = this.x;
        }
    }

    public void addCarta(Card c){

    }
    public void mostrarCasillaDisponible(){

    }



    public void draw(Canvas canvas){
        for(int i = 0; i < this.casillas.size(); i++) {
            Casilla cs = this.casillas.get(i);
            cs.draw(canvas);
        }
    }
}

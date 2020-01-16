package com.example.appprototype;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class CardDisplayer {

    /*
    * #ee8572
    * #35495e
    * #347474
    * #63b7af
    * */

    public Point position;
    public Point dimensions;
    public int cardsCount;
    // cards back layer
    // cards array
    public Point cardDimensions;
    public String backColor = "#347474";
    public List<Sprite> cartas;


    public CardDisplayer(int x, int y, int w, int h) {
        this.position = new Point(x, y);
        this.dimensions = new Point(w, h);

        this.cardsCount = 6;

        cartas = new ArrayList<>();
        int separation = (this.dimensions.x - 10) / this.cardsCount;
        int initpos = 20;
        for(int i = 0; i < this.cardsCount; i++) {
            cartas.add(new GameBackground(null, null, initpos, this.position.y + 10, 180, 240));
            initpos += separation;
        }
    }

    public void calculateCardsPosition() {

    }

    public void calculateCardOrientation() {

    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        pnt.setColor(Color.parseColor(this.backColor));

        //cnv.save();
        //cnv.rotate(5, 0, 0);

        cnv.drawRect(this.position.x, this.position.y, this.dimensions.x, this.dimensions.y, pnt);

        for(int i = cartas.size() - 1; i >= 0; i--) {
            GameBackground itm = (GameBackground) cartas.get(i);
            itm.draw(cnv);
        }

        //restore canvas
        //cnv.restore();
    }

    public void update() {

    }
}

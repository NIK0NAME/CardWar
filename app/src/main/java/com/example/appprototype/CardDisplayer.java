package com.example.appprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public Context cnt;


    public CardDisplayer(int x, int y, int w, int h, Context cnt) {
        this.position = new Point(x, y);
        this.dimensions = new Point(w, h);

        this.cnt = cnt;

        Bitmap tilea = BitmapFactory.decodeResource(this.cnt.getResources(), R.drawable.card1);

        this.cardsCount = 6;

        cartas = new ArrayList<>();
        int separation = 180 + (((this.dimensions.x - 80) - ((this.cardsCount) * 180)) / this.cardsCount);
        int initpos = ((this.dimensions.x) - (separation * cardsCount)) / 2;
        separation -= (180 - separation) / this.cardsCount;

        int bajator = 0;
        for(int i = 0; i < this.cardsCount; i++) {
            cartas.add(new GameBackground(null, null, initpos, this.position.y + 70 - bajator, 180, 240));
            initpos += separation;
            if(i > this.cardsCount / 2 -1) {
                bajator -= 10;
            }else {
                bajator += 10;
            }
        }
    }

    public void calculateCardsPosition() {

    }

    public void calculateCardOrientation() {

    }

    public void draw(Canvas cnv) {
        Paint pnt = new Paint();
        pnt.setColor(Color.parseColor(this.backColor));



        cnv.drawRect(this.position.x, this.position.y, this.dimensions.x, this.dimensions.y, pnt);
        int incliCounter = 20 / cartas.size();
        int initialCount = 10;

        for(int i = cartas.size() - 1; i >= 0; i--) {
            GameBackground itm = (GameBackground) cartas.get(i);
            cnv.save();
            cnv.rotate(initialCount, itm.x + 90, itm.y + 120);
            initialCount -= incliCounter;
            itm.draw(cnv);
            cnv.restore();
        }

        //restore canvas

    }

    public void update() {

    }
}

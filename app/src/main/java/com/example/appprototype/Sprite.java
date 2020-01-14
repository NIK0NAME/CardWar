package com.example.appprototype;

import android.graphics.Bitmap;

public abstract class Sprite {

    protected final int rowCount;
    protected final int colCount;

    public int width = 0;
    public int height = 0;

    public int x;
    public int y;

    protected int WIDTH = 0;
    protected int HEIGHT = 0;

    //Array para guardar las animaciones
    public Bitmap image;

    public Sprite(int rowCount1, int colCount1, Bitmap image, int x, int y) {
        this.rowCount = rowCount1;
        this.colCount = colCount1;

        this.x = x;
        this.y = y;
        this.image = image;

        if(image != null) {
            this.WIDTH = image.getWidth();
            this.HEIGHT = image.getHeight();

            this.width = this.WIDTH/ colCount;
            this.height= this.HEIGHT/ rowCount;
        }
    }

    protected Bitmap createSubImageAt(int row, int col)  {
        // createBitmap(bitmap, x, y, width, height).
        Bitmap subImage = Bitmap.createBitmap(image, col* width, row* height, width, height);
        return subImage;
    }

    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

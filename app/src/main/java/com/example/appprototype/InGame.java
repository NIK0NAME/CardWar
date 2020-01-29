package com.example.appprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;

public class InGame extends AppCompatActivity {

    public int w, h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_in_game);

        Display display = getWindowManager().getDefaultDisplay();

        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;

        Point size = new Point();
        display.getRealSize(size);
        this.w = size.x;
        this.h = size.y; // - 75 - 18;

        setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));
    }
}

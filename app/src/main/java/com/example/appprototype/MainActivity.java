package com.example.appprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPlay;
    public int w, h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen

        //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        /*this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
/*
        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);*/

        hideSystemUI();

        Display display = getWindowManager().getDefaultDisplay();

        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;

        Point size = new Point();
        display.getRealSize(size);
        this.w = size.x;
        this.h = size.y - 75 - 18;



        //setContentView(R.layout.activity_main);
        setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));



        //getSupportActionBar().hide();

        /*btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);*/
    }

    public void initGame() {

    }

    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        /*ActionBar actionBar = getActionBar();
        actionBar.hide();*/

    }

    @Override
    public void onClick(View view) {
        if(view == btnPlay) {
            setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));
            //hideSystemUI();
        }
    }
}

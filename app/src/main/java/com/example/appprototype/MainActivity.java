package com.example.appprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPlay;
    public int w, h;
    public Thread makeFullScreenTrue;
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

        setContentView(R.layout.activity_main);
        //setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));



        //getSupportActionBar().hide();

        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0) {
                            // TODO: The navigation bar is visible. Make any desired
                            // adjustments to your UI, such as showing the action bar or
                            // other navigational controls.
                            meCagoEnAndroid();

                        } else {
                            // TODO: The navigation bar is NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.

                        }
                    }
                });
    }

    public void initGame() {

    }

    public void meCagoEnAndroid() {
        makeFullScreenTrue = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // Sleep.
                    Thread.sleep(2000);
                } catch(InterruptedException e)  {

                    //Toast.makeText(this, "Si te sale esto, te jodes.", Toast.LENGTH_LONG).show();
                }
                hideSystemUI();
            }
        });
        makeFullScreenTrue.run();
    }


    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        /*ActionBar actionBar = getActionBar();
        actionBar.hide();*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //hideSystemUI();
        return super.onTouchEvent(event);

    }

    @Override
    public void onClick(View view) {
        if(view == btnPlay) {
            // setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));
            Intent intent = new Intent(this, UiActivity.class);
            startActivity(intent);
        }else{
            //hideSystemUI();
        }
    }
}

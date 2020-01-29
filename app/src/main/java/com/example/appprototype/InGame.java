package com.example.appprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
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

        setContentView(new SuperficieJuego(this, this.w, this.h, getWindow().getDecorView()));

        hideSystemUI();
    }

    public void meCagoEnAndroid() {
        Thread makeFullScreenTrue = new Thread(new Runnable() {
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
}

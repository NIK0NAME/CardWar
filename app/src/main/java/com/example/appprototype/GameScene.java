package com.example.appprototype;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameScene {
    //Layers - Background - firstLayer - secondLayer
    protected List<Sprite> touchedSprites;
    protected Map<String, List<Sprite>> layers;
    protected List<Sprite> back;

    public GameScene(List<Sprite> back) {
        this.layers = new HashMap<>();
        this.back = back;
        layers.put("back", this.back);
    }

    public void renderSceene(Canvas cnv) {

    }
}

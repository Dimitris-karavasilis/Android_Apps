package com.example.jimmys.spacymess;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by JIMMYS on 9/11/2016.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event);
}

package com.example.jimmys.spacymess;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by JIMMYS on 28/11/2016.
 */

public class Background {

    private Bitmap image;
    private int x, y, dy;

    public Background(Bitmap res)
    {
        image = res;
    }

    public void update()
    {
        y+=dy;
        if (y<-Constants.SCREEN_HEIGHT)
            y=0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
        if(y<0)
        {
            canvas.drawBitmap(image, x , y+Constants.SCREEN_HEIGHT, null);
        }
    }

    public void setVector(int dy)
    {
        this.dy = dy;
    }

    public void checkDraw(Canvas canvas){
        if (canvas != null) {
            //final int savedState = canvas.save();
            //canvas.scale(scaleFactorX, scaleFactorY);
            draw(canvas);
            //canvas.restoreToCount(savedState);
        }
    }

}

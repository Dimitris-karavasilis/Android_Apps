package com.example.jimmys.spacymess;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;


/**
 * Created by JIMMYS on 4/12/2016.
 */

public class StartBtnMenu extends Activity {

    MediaPlayer l2st_1;
    @Override
    protected void onCreate(Bundle startGame) {
        super.onCreate(startGame);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(new GamePanel (this));
        l2st_1 = MediaPlayer.create(this,R.raw.live_to_see_tomorrow);
        l2st_1.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        l2st_1.release();
    }
}



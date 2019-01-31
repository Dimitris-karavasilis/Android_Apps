package com.example.jimmys.spacymess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    MediaPlayer lrdnb_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lrdnb_0 = MediaPlayer.create(this,R.raw.reaper_liquid_drum_n_bass);

        //setting main menu visible and adding button listeners
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main_menu);
        lrdnb_0.start();

        findViewById(R.id.startButton).setOnClickListener(this);
        findViewById(R.id.optionButton).setOnClickListener(this);
        findViewById(R.id.highscoreButton).setOnClickListener(this);
        findViewById(R.id.exitButton).setOnClickListener(this);
    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//    }
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        if(!lrdnb_0.isPlaying()){
//            lrdnb_0.start();
//        }
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        lrdnb_0.release();
//    }
//    @Override
//    protected void onStop(){
//        super.onStop();
//        lrdnb_0.stop();
//      //  finish();
//    }
//    @Override
//    protected void onResume(){
//        super.onResume();
//        lrdnb_0.start();
//    }
@Override
protected void onDestroy() {
    super.onDestroy();
    lrdnb_0.release();
}


    //handle click option buttons
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.startButton:
                lrdnb_0.release();
                Intent startGame = new Intent("android.intent.action.StartBtnMenu") ;
                startActivity(startGame);
                break;
            case R.id.optionButton:
                break;
            case R.id.highscoreButton:
               //getting preferences
                SharedPreferences prefs = this.getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
                prefs.getInt("NewHighScore", 0);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Score");
                builder.setMessage("The highest score is: \n" + Integer.toString(Constants.HighScore));
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.show();
// Must call show() prior to fetching text view
                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);

                break;
            case R.id.exitButton:
                lrdnb_0.release();
                finish();
                break;
        }
    }

}
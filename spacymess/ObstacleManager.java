package com.example.jimmys.spacymess;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JIMMYS on 3/11/2016.
 */

public class ObstacleManager {
    //higher index = lower on screen = higher y value
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;

    private long startTime;
    private long initTime;

    public int LocScore = 0;

    public ObstacleManager(int playerGap,int obstacleGap,int obstacleHeight,int color){
//        BitmapFactory bfr = new BitmapFactory();
//        Bitmap idleRoid = bfr.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.roids);
//        new Background(Bitmap.createScaledBitmap( (BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.roids)),
//                obstacleGap,obstacleHeight,true) );
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        obstacles = new ArrayList<>();

        startTime = initTime = System.currentTimeMillis();

        populateObstacles();
    }

    private void populateObstacles(){
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while (currY < 0 ){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(new Obstacle(obstacleHeight,color,xStart,currY,playerGap));
            currY += obstacleHeight+obstacleGap;
        }
    }

    public boolean playerCollide(RectPlayer player){
        for(Obstacle ob : obstacles){
            if(ob.playerCollide(player))
                return true;
        }
        return false;
    }

    public boolean checkIfHighScore(){
        if(LocScore > Constants.HighScore) {
            Constants.HighScore = LocScore;
            return true;
        }
        else
            return false;
    }

    public void update(){
        if(startTime < Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int)(System.currentTimeMillis()-startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime-initTime)/2000.0)) * Constants.SCREEN_HEIGHT/10000.0f;
        for(Obstacle ob : obstacles ){
            ob.incrementY(speed*elapsedTime);
        }
        if(obstacles.get(obstacles.size()-1).getRectangle().top >= Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random()*(Constants.SCREEN_WIDTH-playerGap));
            obstacles.add(0,new Obstacle(obstacleHeight,color,xStart,obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap,playerGap));
            obstacles.remove(obstacles.size()-1);
            randomizeObstacleColor();
            LocScore++;
        }

    }

    public void randomizeObstacleColor(){
        Random rand = new Random();
        int r1 = rand.nextInt();
        int g1 = rand.nextInt();
        int b1 = rand.nextInt();
        color=Color.rgb(r1,g1,b1);
    }

    public void draw(Canvas canvas){
        for(Obstacle ob : obstacles )
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("" + LocScore,50,50 + paint.descent() - paint.ascent(),paint);
    }
}

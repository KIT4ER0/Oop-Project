/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timer;

import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.plugins.tiff.ExifGPSTagSet;
import main.GamePanel;

/**
 *
 * @author FinalDIE
 */
public class FinishGame {
    private GamePanel gp;
    private Timer timer;
    private long gameTimerDuration;
    private boolean gameFinished = false;
    private long  gamestart , usingTime, remainingTime;
    private int pausecount;

// set Timer
    public FinishGame(long timeDuration, GamePanel gp) {
        this.gp = gp;
        this.gameTimerDuration = timeDuration;
        timer = new Timer();
        System.out.println("add timer");
        gamestart = System.currentTimeMillis();
    }

// start cooldown
    public void starttimer() {
        timer.schedule(new GameTimerTask(), gameTimerDuration);
        System.out.println("timer start");
    }

// after cooldown
    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            gameFinished = true;
           
        }
    }
    public String getRemainingTime() {
        if(gp.getGameState() == gp.getPlayState() && pausecount == 1){
            gamestart = System.currentTimeMillis();
            usingTime = System.currentTimeMillis() - gamestart;
            pausecount = 0;
        }
        //pause
        else if(gp.getGameState() == gp.getPauseState()){
            gameTimerDuration = remainingTime;
            usingTime = 0;
            pausecount = 1;
        }
        else if(gp.getGameState() == gp.getPlayState() && pausecount == 0){
            usingTime = System.currentTimeMillis() - gamestart;
        }
        remainingTime = gameTimerDuration - usingTime;
        System.out.println( gameTimerDuration + " - " + usingTime + " = " + remainingTime);

        long minutes = (remainingTime / 1000) / 60;
        long seconds = (remainingTime / 1000) % 60;
        if(gp.getGameState() == gp.getFinishState()){
            minutes = 00;
            seconds = 00;
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public int getPausecount() {
        return pausecount;
    }

    public void setPausecount(int pausecount) {
        this.pausecount = pausecount;
    }
    
}

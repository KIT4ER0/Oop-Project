
package timer;

import java.util.Timer;
import java.util.TimerTask;

public class tableACooldown {
    private Timer timer;
    private long cooldownDuration;
    private boolean finishedA = false;

// set Timer
    public tableACooldown(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
        this.timer = new Timer();
    }

// start cooldown
    public void startCooldown() {
        timer.schedule(new CooldownTask(), cooldownDuration);
    }

// after cooldown
    private class CooldownTask extends TimerTask {
        @Override
        public void run() {
            finishedA = true;
           
        }
    }

    public boolean isFinishedA() {
        return finishedA;
    }

    public void setFinishedA(boolean finishedA) {
        this.finishedA = finishedA;
    }
    
}

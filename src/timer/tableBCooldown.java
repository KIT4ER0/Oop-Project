
package timer;

import java.util.Timer;
import java.util.TimerTask;

public class tableBCooldown {
    private Timer timer;
    private long cooldownDuration;
    private boolean finishedB = false;

// set Timer
    public tableBCooldown(long cooldownDuration) {
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
            finishedB = true;
           
        }
    }

    public boolean isFinishedB() {
        return finishedB;
    }

    public void setFinishedB(boolean finishedB) {
        this.finishedB = finishedB;
    }
    
}

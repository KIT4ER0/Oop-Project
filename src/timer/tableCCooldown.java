
package timer;

import java.util.Timer;
import java.util.TimerTask;

public class tableCCooldown {
    private Timer timer;
    private long cooldownDuration;
    private boolean finishedC = false;

// set Timer
    public tableCCooldown(long cooldownDuration) {
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
            finishedC = true;
           
        }
    }

    public boolean isFinishedC() {
        return finishedC;
    }

    public void setFinishedC(boolean finishedC) {
        this.finishedC = finishedC;
    }
    
}

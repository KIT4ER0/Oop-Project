
package timer;

import java.util.Timer;
import java.util.TimerTask;

public class CustomerBCooldown {
    private Timer timer;
    private long cooldownDuration;
    private boolean finishedcustB = false;

// set timer
    public CustomerBCooldown(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
        this.timer = new Timer();
        
    }

// start cooldown
    public void startCooldown() {
        timer.schedule(new CooldownTask(), cooldownDuration);
        
    }
    
//cancel cooldown
    public void cancelCooldown() {
        timer.cancel();
        
    }

// aftercooldown
    private class CooldownTask extends TimerTask {
        @Override
        public void run() {
            finishedcustB = true;

        }
    }

    public boolean isFinishedcustB() {
        return finishedcustB;
    }

    public void setFinishedcustB(boolean finishedcustB) {
        this.finishedcustB = finishedcustB;
    }
    
}

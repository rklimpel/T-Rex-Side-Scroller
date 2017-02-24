package programming.projekt.trex;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player extends GameObject {

    final int defaultHeight = 80;
    final int defaultWidth = 40;
    int defaultY;

    int jumpTimerDelay = 10;
    double jumpTime = 0;

    Boolean isJumping = false;

    final int jumpHeight = 150;


    public Player(int paneWidth, int paneHeight) {

        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth / 8;
        y = paneHeight - defaultHeight;

        defaultY = y;

    }

    /**
     *
     */
    public void jump() {

        Timer jumpTimer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {

                //If Player reaches Bottom again end the jump prozess
                if(getY()>=defaultY && isJumping){
                    setY(defaultY);
                    jumpTimer.cancel();
                    jumpTimer.purge();
                    isJumping = false;
                    jumpTime = 0;
                    System.out.println("jump's over");

                }else{
                    jumpTime += 0.1;

                    isJumping = true;

                    System.out.println("jump before y: " + getY());

                    setY(((int)Math.pow(jumpTime-Math.sqrt(jumpHeight),2))+jumpHeight+paneHeight-(defaultY-height+jumpHeight));

                    System.out.println("jump after y: " + getY());

                }
            }
        };
        jumpTimer.scheduleAtFixedRate(task, 0, jumpTimerDelay);
    }

    public Boolean getJumping() {
        return isJumping;
    }
}

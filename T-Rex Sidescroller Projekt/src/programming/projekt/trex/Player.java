package programming.projekt.trex;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player extends GameObject {

    //players messurements
    final int defaultHeight = 80;
    final int defaultWidth = 40;

    //default y positon of the player
    int defaultY;

    //jump timer tick delay in milliseconds
    int jumpTimerDelay = 10;
    //counts up on jump, resets after jump
    double jumpTime = 0;
    //check if the player is still jump
    Boolean isJumping = false;


    /**------------------------**/
    //Jump Configuration: Formula Data
    final double gravitation = 20;
    //optium:20
    final double jumpSpeed = 80;


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
     * do the JUMP
     */
    public void jump() {

        Timer timer_jump = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {

                //If Player reaches Bottom again end the jump prozess
                if(getY()>=defaultY && isJumping){

                    setY(defaultY);
                    timer_jump.cancel();
                    timer_jump.purge();
                    isJumping = false;
                    jumpTime = 0;
                    System.out.println("jump's over");

                //Else calculate the new Players y coordinate
                }else{

                    jumpTime += 0.1;

                    isJumping = true;

                    //System.out.println("jump before y: " + getY());
                    setY(paneHeight-(int)((jumpSpeed *jumpTime-(gravitation/2)*Math.pow(jumpTime,2))+height+1));
                    //System.out.println("jump after y: " + getY());
                }
            }
        };
        timer_jump.scheduleAtFixedRate(task, 0, jumpTimerDelay);
    }

}

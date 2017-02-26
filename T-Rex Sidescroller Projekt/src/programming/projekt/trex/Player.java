package programming.projekt.trex;

import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class Player extends GameObject {

    //players messurements
    final int defaultHeight = R.playerHeight;
    final int defaultWidth = R.playerWidth;

    double rotation;

    double jumpAllTime;
    double rotationPerTick;

    //Checked if the player is crouching
    Boolean isCrouching = false;

    final double playerJumpRotation = R.playerJumpRotation;

    //default y positon of the player
    int defaultY;

    //jump timer tick delay in milliseconds
    int jumpTimerDelay = R.playerJumpTimerDelay;
    //counts up on jump, resets after jump
    double jumpTime = 0;
    //check if the player is still jump
    Boolean isJumping = false;
    //Jump Timer
    Timer timer_jump;


    /**
     * ------------------------
     **/
    //Jump Configuration: Formula Data
    final double gravitation = R.playerGravitation;
    //optium:20
    final double jumpSpeed = R.playerJumpSpeed;


    public Player(int paneWidth, int paneHeight) {

        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;

        width = defaultWidth;
        height = defaultHeight;

        x = paneWidth / 8;
        y = paneHeight - defaultHeight;

        defaultY = y;

        rotation = 0;

        color = R.playerColor;

    }

    /**
     * do the JUMP
     */
    public void jump() {

        jumpAllTime = (jumpSpeed / gravitation) * 2;
        //System.out.println(jumpAllTime);
        rotationPerTick = (360 / jumpAllTime / 10) * playerJumpRotation;

        timer_jump = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {

                //If Player reaches Bottom again end the jump prozess
                if (y >= defaultY && isJumping) {

                    setY(defaultY);

                    stopJumpTimer();
                    jumpTime = 0;

                    rotation = 0;
                    //System.out.println("jump's over");

                    //Else calculate the new Players y coordinate
                } else {


                    jumpTime += 0.1;

                    //System.out.println("jumpTime: " + jumpTime);

                    isJumping = true;

                    //System.out.println("jump before y: " + getY());


                    setY(paneHeight - (int) ((jumpSpeed * jumpTime - (gravitation / 2) * Math.pow(jumpTime, 2)) + defaultHeight + 1));


                    //System.out.println("jump after y: " + getY());

                    if (R.playerRotation) {
                        rotation += rotationPerTick;
                    }

                }
            }
        };
        timer_jump.scheduleAtFixedRate(task, 0, jumpTimerDelay);
    }

    public void crouch() {

        height = (int) (defaultHeight * R.playerCrouchSize);
        yOffset = (int) (defaultHeight * (1 - R.playerCrouchSize));

        isCrouching = true;
    }

    public void crouchEnd() {
        height = defaultHeight;
        yOffset = 0;
        isCrouching = false;
    }

    public void stopJumpTimer() {
        timer_jump.cancel();
        timer_jump.purge();
        isJumping = false;
    }


}

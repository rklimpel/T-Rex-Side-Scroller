package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;
import java.util.Timer;
import java.util.TimerTask;


public class Player extends GameObject {

   //Rotation value of the player (0-360)
   double rotation;
   //Here the time for a whole jump will be calculated and stored
   double jumpAllTime;
   //how far the player rotates per jumpTimer tick
   double rotationPerTick;
   //How far the player should rotate by one jump
   final double playerJumpRotation = R.playerJumpRotation;
   //Player rotation activated?
   final Boolean playerRotation = R.playerRotation;

   Player player;

   //Checked if the player is crouching
   Boolean isCrouching = false;

   //jump timer tick delay in milliseconds
   int jumpTimerDelay = R.playerJumpTimerDelay;
   //counts up on jump, resets after jump
   double jumpTime = 0;
   //check if the player is still jump
   Boolean isJumping = false;

   Boolean isJumpingDown = false;
   //Jump Timer
   Timer timer_jump;

   //Jump Configuration: Formula Data
   final double gravitation = R.playerGravitation;
   //optium:20
   double defaultJumpSpeed = R.playerJumpSpeed;

   int landingOffset;

   int nextPlatformOffset;

   GameModel gameModel;

   Platform platform;

   double jumpSpeed;

   public Player(int paneWidth, int paneHeight, GameModel gameModel) {

      this.gameModel = gameModel;

      this.player = this;

      //players messurements
      defaultHeight = R.playerHeight;
      defaultWidth = R.playerWidth;

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

   public void jump(){
      this.jump(defaultJumpSpeed);
   }

   /**
    * calculates the new Y values for the jumping ployer
    */
   public void jump(final double jumpSpeedValue) {

      isJumpingDown = false;

      jumpSpeed = jumpSpeedValue;

      //Other jump speed if player is couched
      if (isCrouching) {
         jumpSpeed = R.crouchingPlayerJumpSpeed;
      } else {
         jumpSpeed = R.playerJumpSpeed;
      }

      //System.out.print("Jump!");

      //Calculations for Player Rotation:
      jumpAllTime = (jumpSpeed / gravitation) * 2;
      rotationPerTick = (360 / jumpAllTime / 10) * playerJumpRotation;

      timer_jump = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {

            if(platform != null && !platform.playerOnPlatform){
               landingOffset = 0;
            }else if(platform!=null && platform.playerOnPlatform){
               landingOffset = platform.getPlatformOffset();
            }

            nextPlatformOffset = 0;

            if(platform == null){
               landingOffset = 0;
            }

            //If Player reaches Bottom again end the jump prozess
            if (y >= defaultY - landingOffset && isJumping) {

               setY(defaultY - landingOffset);

               stopJumpTimer();

               jumpTime = 0;
               rotation = 0;

               if(platform == null){
                  platformOffset=0;
               }

            }

            //Else calculate the new Players y coordinate
            else {

               jumpTime += 0.1;

               isJumping = true;

               //Calculate the new y value for the player (senkrechter Wurf)
               setY(paneHeight - (int) ((jumpSpeed * jumpTime - (gravitation / 2) * Math.pow(jumpTime, 2))
                       + defaultHeight + 1 + platformOffset));

               if(jumpSpeed-gravitation*jumpTime<0){
                  isJumpingDown=true;
               }


               if (playerRotation) {
                  rotation += rotationPerTick;
               }

            }
         }
      };
      timer_jump.scheduleAtFixedRate(task, 0, jumpTimerDelay);
   }

   /**
    * Set Player to Crouching
    */
   public void crouch() {

      height = (int) (defaultHeight * R.playerCrouchSize);
      yCrouchOffset = (int) (defaultHeight * (1 - R.playerCrouchSize));

      isCrouching = true;

      //System.out.print("crouch");
   }

   /**
    * Reverts the "set Player to Crouching" changes
    * (crouch ends)
    */
   public void crouchEnd() {

      height = defaultHeight;
      yCrouchOffset = 0;
      isCrouching = false;

      //System.out.println("crouch Released");
   }

   /**
    * called when player reaches the bottom again or hit an obstacle.
    * ends the jumpTimer instantly and player won't move on y axis anymore
    */
   public void stopJumpTimer() {
      timer_jump.cancel();
      timer_jump.purge();
      jumpTime = 0;
      isJumping = false;
      isJumpingDown = false;
   }

   public Boolean getCrouching() {
      return isCrouching;
   }

   public Boolean getJumping() {
      return isJumping;
   }

   public double getRotation() {
      return rotation;
   }

   public void setPlatform(Platform platform) {
      this.platform = platform;
   }
}

package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;
import java.util.Timer;
import java.util.TimerTask;

/**
 * class for the player-object, the actions which the player perfomrs
 * are calculated in this class.
 * As well as its size and color.
 */
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

   private Boolean trickJumpOn = false;

   /**
    * constructor for the player, here the default position and color are set.
    * The size is loaded from the R-class
    * @param paneWidth width of the current pane
    * @param paneHeight height of the current pane
    * @param gameModel
    */
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

      x = paneWidth / 5;
      y = paneHeight - defaultHeight;

      defaultY = y;

      rotation = 0;

      color = R.playerColor;

   }

   public void jump(){
      this.jump(defaultJumpSpeed);
   }

   /**
    * main jump method for the game.
    * In this method the y-values while the player is jumping are calculated.
    * Therefor is the formula for a vertical throw was taken. The position is
    * calculated by the past time. This time is shown by a new timer which is reseted
    * after every jump.
    * Additionally is checked if the player is crouching then the jumpheight
    * is not as high as in normal running and if the player is running on a plattform.
    * In this case the lower y-value is used. If player rotation is activated this calculation
    * is made here as either.
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

      //Calculations for Player Rotation:
      jumpAllTime = (jumpSpeed / gravitation) * 2;
      rotationPerTick = (360 / jumpAllTime / 10) * playerJumpRotation;

      //the new timer for the calculation of the jump
      timer_jump = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {

            //checking if player is on a platform
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


               if (playerRotation || trickJumpOn) {
                  rotation += rotationPerTick;
               }

            }
         }
      };
      timer_jump.scheduleAtFixedRate(task, 0, (long)(jumpTimerDelay*R.gamespeed));
   }

   public void fall(int platformFallingFrom){

      isJumpingDown = true;

      jumpSpeed = 0;

      //the new timer for the calculation of the jump
      timer_jump = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {

            //checking if player is on a platform
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


            }

            //Check for all platforms:
            for (int j = 0; j < gameModel.getPlatforms().size(); j++) {

               //If the player is inside the bounds of an platform and the platform is lower than the
               //platform he fall of
               if (gameModel.getPlatforms().get(j).checkOnPlatform(player)
                       &&gameModel.getPlatforms().get(j).getPlatformOffset()<=gameModel.getPlatforms().get(platformFallingFrom).getPlatformOffset()) {

                  //Tell the player about his next platform
                  player.setPlatformOffset((paneHeight - player.groundLvl) - gameModel.getPlatforms().get(j).getY());
                  player.setPlatform(gameModel.getPlatforms().get(j));

                  //Set player position to next platform (hard) not smooth argl
                  player.setY(player.defaultY - player.platformOffset);

                  //Tell this platform about the player on it
                  gameModel.getPlatforms().get(j).playerOnPlatform = true;
               }
            }
         }
      };
      timer_jump.scheduleAtFixedRate(task, 0, (long)(jumpTimerDelay*R.gamespeed));
   }

   /**
    * Method for the crouching-action.
    * Here the height of the player is reduced by a given factor and
    * the new offset for jumping is calculated
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

      System.out.println("rotation: " +rotation);
      System.out.println("rotation modulo: " +rotation%360);

      if(rotation < 50 || rotation%360 < 50  || (rotation > 360-50 && rotation < 360)){
         rotation = 0;
      }else{

         gameModel.stopGameTimer();

         gameModel.gameOver = true;
         gameModel.trickJumpFail = true;

         //Call the gameController stopUpdate Timer delayed so every view hast the change to update to
         //the last view state
         new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               gameModel.getGameController().stopUpdater();
            }
         }, 50);

      }

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

   public Boolean getTrickJumpOn() {
      return trickJumpOn;
   }

   public void setTrickJumpOn(Boolean trickJumpOn) {
      this.trickJumpOn = trickJumpOn;
   }
}

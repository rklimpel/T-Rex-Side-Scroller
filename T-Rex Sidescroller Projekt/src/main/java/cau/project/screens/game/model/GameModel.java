package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;
import main.java.cau.project.screens.game.controller.GameController;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameModel {

   //LvL Index counts the positions in the lvl Array
   //after every fired obstacle lvl index += 1
   //to get the next obstacle waiting time for obstacle timer
   private int lvlIndex = 0;
   //Timer set to the numbers in lvlArrays and count down with game timer.
   //On obstacle Timer 0 a object is fired
   private int obstacleTimer = 0;
   //toggled by new lvl load. Makes the first obstacle Timer countdown don't fire a obstacle
   private Boolean lvlPause = true;
   //create a lvls instance to load the lvls from
   private Levels levels = new Levels();
   //level array saves the lvl arrays loaded from lvls class locally
   private int[][] levelArray;

   //Counts up how often the game timer has ticked,
   //can be used to generate the complete gametime
   private int timerTick = 0;

   //GameTimer von continoues moves
   private Timer gameTimer = new Timer();

   //is the Timer enabled?
   public Boolean gameTimerEnabled = false;

   //Jump waiting
   private Boolean jumpWaiting = false;

   //Delay, before the timer starts
   private int gameTimerOffset = R.gameTimerOffset;
   private int gameTimerDelay = R.gameTimerDelay;
   //optimum:3

   //The Player Object that contains to the Game
   public Player player;

   //The Ground
   public Ground ground;

   //Game GameController who called the GameModel, set on contsructor
   private GameController gameController;

   //Save Pane Size here when constructor gets called:
   private int paneWidth;
   private int paneHeight;

   //Remembers the Players GameScore
   private int score;

   //if player died this will be true:
   public Boolean gameOver = false;

   //List off all Obstacles that are in the Game
   private ArrayList<Obstacle> obstacles = new ArrayList<>();

   //List of all Powerups that are in the Game
   private ArrayList<Powerup> powerups = new ArrayList<>();

   //List of all Platforms that are in the Game
   private ArrayList<Platform> platforms = new ArrayList<>();

   //List of all Background Objects
   private ArrayList<BackgroundObject> backgroundObjects = new ArrayList<>();

   Random rand = new Random();

   int nextBackground = 500;

   /**
    * Creates a new GameModel Instance (i think there should be just one...)
    * saves GameController to class variable
    *
    * @param gameController
    */
   public GameModel(GameController gameController, int paneWidth, int paneHeight) {
      this.gameController = gameController;
      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;
      this.gameOver = false;

      this.ground = new Ground(paneWidth, paneHeight);
   }

   /**
    * Creates a new Player instace from Player class
    */
   public void createPlayer() {
      player = new Player(paneWidth, paneHeight, this);
   }

   /**
    * Called by Space, makes the player jump
    */
   public void jump() {
      if (!player.isJumping) {
         player.jump();
         for (int i = 0; i < platforms.size(); i++) {
            platforms.get(i).playerOnPlatform = false;
         }
         player.platform = null;
      } else if (paneHeight - R.groundLvL - player.getY()
              <= player.getHeight() / 2 + player.getHeight()) {
         jumpWaiting = true;
      }
   }

   /**
    * Creates a new Obstacle instance from Obstacle class
    */
   public void createObstacle(int type, int yOffset) {
      Obstacle obstacle = new Obstacle(type, paneWidth, paneHeight, yOffset);
      obstacles.add(obstacle);
   }

   /**
    * Creates a new Powerup
    *
    * @param powerupType
    * @param yOffset
    */
   public void createPowerup(int powerupType, int yOffset) {
      Powerup powerup = new Powerup(powerupType, paneWidth, paneHeight, yOffset);
      powerups.add(powerup);
   }

   /**
    * Creates a new Platform
    *
    * @param platformType
    * @param yOffset
    */
   public void createPlatform(int platformType, int yOffset) {
      Platform platform = new Platform(platformType, this, paneWidth, paneHeight, yOffset);
      platforms.add(platform);
   }

   /**
    * Starts the continous ticking Game Timer
    */
   public void startGameTimer() {
      gameTimer = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {
            GameTick();
         }
      };
      gameTimer.scheduleAtFixedRate(task, gameTimerOffset, gameTimerDelay);
      gameTimerEnabled = true;
   }

   /**
    * Stops the continous ticking Game Timer
    */
   public void stopGameTimer() {
      gameTimer.cancel();
      gameTimer.purge();
      gameTimerEnabled = false;
   }

   /**
    * Fires on every GameTimer tick
    * object and other movements should be done here
    * <p>
    * sends update notification to controller
    */
   public void GameTick() {

      timerTick += 1;

      checklvl();

      checkScore();

      if (jumpWaiting && !player.isJumping) {

         //System.out.println("Waiting jump called!");

         jump();
         jumpWaiting = false;
      }

      checkPowerups();

      checkPlatforms();

      checkObstacles();

      nextBackground -= 1;
      if (nextBackground == 0) {
         checkBackgroundObjects();
      }

      for (int i = 0; i < backgroundObjects.size(); i++) {
         backgroundObjects.get(i).setX(backgroundObjects.get(i).getX() - 0.3);
         if(backgroundObjects.get(i).getX()+backgroundObjects.get(i).getWidth()<0){
            backgroundObjects.remove(i);
         }
      }


   }

   private void checkBackgroundObjects() {

      nextBackground = randInt(0, 2500);

      System.out.println("New Background Cactus!");

      BackgroundObject backgroundObject = new BackgroundObject(paneWidth, paneHeight);


      /*int mid = (int)(((paneHeight/2) + (paneHeight-R.groundLvL-50))/2);
      int diff = mid - (int)imageView.getY();

      int ratio = R.backgroundObjectHeight/R.backgroundObjectWidth;*/

      backgroundObject.setWidth(R.backgroundObjectWidth);
      backgroundObject.setHeight(R.backgroundObjectHeight);

      backgroundObjects.add(backgroundObject);

   }
   private int randInt(int min, int max) {
      if (min < 0) {
         min = 1;
      }
      if (max < 0) {
         max = 2;
      }

      int randomNum = rand.nextInt((max - min) + 1) + min;

      return randomNum;
   }


   /**
    * check existing Platforms
    */
   public void checkPlatforms() {
      // Do Things with Platforms
      for (int i = 0; i < platforms.size(); i++) {

         if (!platforms.get(i).checkOutisde()) {

            platforms.get(i).moveLeft();

            //Jump on Platform and land on it
            if (platforms.get(i).checkOnPlatform(player) && player.isJumpingDown) {
               player.stopJumpTimer();
               player.setPlatformOffset((paneHeight - player.groundLvl) - platforms.get(i).getY());
               platforms.get(i).playerOnPlatform = true;
               player.setPlatform(platforms.get(i));
            } else if (platforms.get(i).playerOnPlatform
                    && !platforms.get(i).checkOnPlatform(player)
                    && !player.isJumping) {

               platforms.get(i).playerOnPlatform = false;
               player.setPlatform(null);
               player.setPlatformOffset(0);
               player.setY(player.defaultY);

               for (int j = 0; j < platforms.size(); j++) {
                  if (platforms.get(j).checkOverPlatform(player)) {
                     player.setPlatformOffset((paneHeight - player.groundLvl) - platforms.get(j).getY());
                     platforms.get(j).playerOnPlatform = true;
                     player.setPlatform(platforms.get(j));
                     player.setY(player.defaultY - player.platformOffset);
                  }
               }


            }

         } else {

            platforms.remove(i);

         }

      }
   }

   /**
    * check existing Powerups
    */
   private void checkPowerups() {
      //Do Things with Powerups
      for (int i = 0; i < powerups.size(); i++) {

         if (!powerups.get(i).checkOutisde()) {

            powerups.get(i).moveLeft();

            if (player.checkCollision(powerups.get(i))) {

               System.out.println("Got that Powerup!");

               score += 5;
               powerups.remove(i);

            }
         } else {
            powerups.remove(i);
         }
      }
   }

   /**
    * Checks existing Obstacles
    */
   private void checkObstacles() {
      //Do Things with obstacles
      for (int i = 0; i < obstacles.size(); i++) {

         if (!obstacles.get(i).checkOutisde()) {

            obstacles.get(i).moveLeft();

            if (player.checkCollision(obstacles.get(i))) {

               stopGameTimer();
               gameOver = true;

               System.out.println("Collision!");

               player.stopJumpTimer();

               new Timer().schedule(new TimerTask() {
                  @Override
                  public void run() {
                     gameController.stopUpdater();
                  }
               }, 50);

            }

         } else {
            obstacles.remove(i);
         }
      }
   }

   /**
    * Checks if the player jumped completly over an obstacle
    */
   private void checkScore() {
      for (int i = 0; i < obstacles.size(); i++) {
         if (obstacles.get(i).getX() + obstacles.get(i).getWidth() == player.getX()) {
            score += 1;
         }
      }
   }

   /**
    * reads the lvl-Array and creates the objects on the
    * specific spots.
    */
   private void checklvl() {

      //If no lvl is active set a new random lvl as active lvl and
      //reset obstacle Timer and lvl Index
      if (levels.getActiveLvl() == R.EMPTY) {

         levels.setRandomLvl();
         obstacleTimer = R.obstaclePauseTimer;
         lvlPause = true;
         lvlIndex = 0;

         //System.out.println("obstacle timer: " + obstacleTimer);
         //System.out.println("lvl index " + lvlIndex);
         //System.out.println("pause: " + lvlPause +"\n");
      }

      //if Obstacle Timer is down and there is a active lvl fire a new Obstacle
      if (obstacleTimer == 0 && levels.getActiveLvl() != R.EMPTY) {

         if (lvlPause) {

            //Get Level Array for active lvl from Level class
            levelArray = levels.getActiveLvlArray();
            lvlPause = false;

         } else {

            //Create a new Game Object here
            if (lvlIndex != R.EMPTY) {

               if (levelArray[1][lvlIndex - 1] < 100) {
                  createObstacle(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
                  //System.out.println("Create Obstacle- index: " + (lvlIndex - 1));
               } else if (levelArray[1][lvlIndex - 1] >= 100 && levelArray[1][lvlIndex - 1] < 200) {
                  createPowerup(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
               } else if (levelArray[1][lvlIndex - 1] >= 200 && levelArray[1][lvlIndex - 1] < 300) {
                  createPlatform(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
               }

            }

         }

         //Check if next stuff in the lvl Array ist an obstacle or an powerup
         //If lvl index = -1 there are no more obstacles in this lvl and he should not load more
         //Else do the normal stuff
         if (lvlIndex != R.EMPTY && lvlIndex < levelArray[0].length) {

            if (levelArray[1][lvlIndex] < 100) {

               //Create a helping obstacle to check obstacle width
               Obstacle helpObstacle = new Obstacle(levelArray[1][lvlIndex]);

               //set new obstacle timer by lvl array data and add obstacle width offset
               obstacleTimer = levelArray[0][lvlIndex] + helpObstacle.getWidth();

            } else if (levelArray[1][lvlIndex] >= 100 && levelArray[1][lvlIndex] < 200) {

               Powerup helpPowerup = new Powerup(levelArray[1][lvlIndex], paneWidth, paneHeight, 0);

               obstacleTimer = levelArray[0][lvlIndex] + helpPowerup.getWidth();

            } else if (levelArray[1][lvlIndex] >= 200 && levelArray[1][lvlIndex] < 300) {

               obstacleTimer = levelArray[0][lvlIndex];

            }

            lvlIndex += 1;

         }

         // if lvl index is out of bounds set activelvl to -1 and reset lvl index
         else if (lvlIndex >= levelArray[0].length) {
            lvlIndex = R.EMPTY;
            levels.setActiveLvl(R.EMPTY);
         } else {
            levels.setActiveLvl(R.EMPTY);
         }

      }

      if (obstacleTimer != 0) {
         obstacleTimer -= 1;
      }

   }

   public int getScore() {
      return score;
   }

   public Player getPlayer() {
      return player;
   }

   public Boolean getGameOver() {
      return gameOver;
   }

   public ArrayList<Obstacle> getObstacles() {
      return obstacles;
   }

   public int getPaneWidth() {
      return paneWidth;
   }

   public int getPaneHeight() {
      return paneHeight;
   }

   public Ground getGround() {
      return ground;
   }

   public ArrayList<Powerup> getPowerups() {
      return powerups;
   }

   public ArrayList<Platform> getPlatforms() {
      return platforms;
   }

   public ArrayList<BackgroundObject> getBackgroundObjects() {
      return backgroundObjects;
   }
}
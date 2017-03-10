package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;
import main.java.cau.project.screens.game.controller.GameController;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * This class is the main Game calculation class it handles all important game value changes
 */
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

   //is the GameTimer enabled?
   public Boolean gameTimerEnabled = false;

   //Jump waiting Boolean
   //if the player is jumping and the user send jump input short before touching the ground
   //the jump will be saved and executed when the last jump ends
   private Boolean jumpWaiting = false;

   //Delay, before the Gametimer starts...
   private int gameTimerOffset = R.gameTimerOffset;
   //Delay of the Gametimer, ticks every gameTimerDelay Milliseconds
   private int gameTimerDelay = R.gameTimerDelay;

   //The Player Object that contains to the Game
   public Player player;

   //The Ground Object that contains to the Game
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

   //List of all Background Objects (Cactus things)
   private ArrayList<BackgroundObject> backgroundObjects = new ArrayList<>();

   //Random generator, used to set Random Background object gaps
   private Random rand = new Random();

   //integer that counts down from a number and when it is 0 it spawn a new background object
   //500 is the value for the first background object. then the random int will be used
   private int nextBackground = 500;

   public Boolean trickJumpFail = false;

   private ArrayList<ScorePopup> scorePopups = new ArrayList<>();


   /**
    * Creates a new GameModel Instance
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

      Boolean nearPlatform = false;
      int waitingJumpToleranz = 50;

      //Check if jump called when near Platform
      for (int i = 0; i < platforms.size(); i++) {
         if(platforms.get(i).getY()-(player.getY()+player.getHeight())<=waitingJumpToleranz
                 && platforms.get(i).getY()-(player.getY()+player.getHeight()) >= 0){
            nearPlatform = true;
            System.out.println("NEAR PLATFORM");
         }
      }

      //Check if jump called when near ground
      if (ground.getY()-player.getY()-player.getHeight()<=waitingJumpToleranz
              &&ground.getY()-player.getY()-player.getHeight()>=0){
         nearPlatform = true;
         System.out.println("NEAR GROUND");
      }

      //If the player isn't jumping call jump in players class
      if (!player.isJumping) {
         player.jump();

         //If players jump is called all Platforms should be notified that
         //the player is no longer on any platform
         for (int i = 0; i < platforms.size(); i++) {
            platforms.get(i).playerOnPlatform = false;
         }
         player.platform = null;
      }
      //If the player is near to the ground save the jump and execute it later
      else if (nearPlatform && player.isJumpingDown) {
         jumpWaiting = true;
      }
   }

   /**
    * Creates a new Obstacle instance from Obstacle class
    * adds obstacle to obstacle list
    *
    * @param type
    * @param yOffset
    */
   public void createObstacle(int type, int yOffset) {
      Obstacle obstacle = new Obstacle(type, paneWidth, paneHeight, yOffset);
      obstacles.add(obstacle);
   }

   /**
    * Creates a new Powerup
    * adds Powerup to powerup list
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
    * adds Platform to Platform List
    *
    * @param platformType
    * @param yOffset
    */
   public void createPlatform(int platformType, int yOffset) {
      Platform platform = new Platform(platformType, this, paneWidth, paneHeight, yOffset);
      platforms.add(platform);
   }

   /**
    * Starts the continous ticking Game Timer and set GameTimer to Enabled
    */
   public void startGameTimer() {
      gameTimer = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {
            GameTick();
         }
      };
      gameTimer.scheduleAtFixedRate(task, gameTimerOffset, (long)(gameTimerDelay*R.gamespeed));
      gameTimerEnabled = true;
   }

   /**
    * Stops the continous ticking Game Timer and set GameTimer to Disabled
    */
   public void stopGameTimer() {
      gameTimer.cancel();
      gameTimer.purge();
      gameTimerEnabled = false;
   }

   /**
    * Fires on every GameTimer tick
    *
    * do object movements
    * check collisions
    * setup levels and lvl objects
    * setup background objects (cactus things)
    *
    */
   public void GameTick() {

      timerTick += 1;

      checklvl();

      checkScore();

      //If a waiting jump is called and the player is no longer jumping do the waiting jump
      if (jumpWaiting && !player.isJumping) {
         jump();
         jumpWaiting = false;
      }

      checkPowerups();

      checkPlatforms();

      checkObstacles();

      for (int i = 0; i < scorePopups.size(); i++) {
         scorePopups.get(i).setX(scorePopups.get(i).getX()-1);
      }

      nextBackground -= 1;
      //create new background object if nextBackground value = 0
      if (nextBackground == 0) {
         checkBackgroundObjects();
      }

      //Move the background objects
      for (int i = 0; i < backgroundObjects.size(); i++) {
         backgroundObjects.get(i).setX(backgroundObjects.get(i).getX() - 0.3);
         if(backgroundObjects.get(i).getX()+backgroundObjects.get(i).getWidth()<0){
            backgroundObjects.remove(i);
         }
      }
   }

   /**
    * Sets up a new Background object object and adds it to background object list
    * creates a new Random int how long we should wait for the next background obejct
    */
   private void checkBackgroundObjects() {

      nextBackground = randInt(0, 2500);

      BackgroundObject backgroundObject = new BackgroundObject(paneWidth, paneHeight);

      backgroundObjects.add(backgroundObject);

      //System.out.println("New Background Cactus!");

   }

   /**
    * Helper Method to create a random int
    *
    * @param min
    * @param max
    * @return
    */
   private int randInt(int min, int max) {

      int randomNum = 500;

      try{

         randomNum = rand.nextInt((max - min) + 1) + min;

      }catch (Exception e){

         System.out.println(e);
         //In the beginning we sometimes got the error that min is bigger than max.
         //this catch will catch the exeption. then the default value for the next background
         //obstacle is 500

      }

      return randomNum;
   }


   /**
    * do things with the existing platforms:
    *
    * move them left
    * check for out of game window
    * check for player jump on them
    * check for player fall of from them
    *
    */
   private void checkPlatforms() {

      //For every Platform in the List:
      for (int i = 0; i < platforms.size(); i++) {

         //If Platform is not Outside the Window
         if (!platforms.get(i).checkOutisde()) {

            platforms.get(i).moveLeft();

            //Play was not on a Platform and now Jumps down on it
            if (platforms.get(i).checkOnPlatform(player) && player.isJumpingDown) {

               //Stop the Players Jump instantly so he remains on the Platform lvl
               player.stopJumpTimer();

               //Tell the players class instance the Offset of the y poisition because of the platform
               //Tell the players class about the platform object he is on
               player.setPlatformOffset((paneHeight - player.groundLvl) - platforms.get(i).getY());
               player.setPlatform(platforms.get(i));

               //Tell the platform that the player is on it
               platforms.get(i).playerOnPlatform = true;
            }

            //Player was on Platform, is not jumping but the Platform checker says he is no longer on the Platform
            // => means: Player should fall down
            else if (platforms.get(i).playerOnPlatform
                    && !platforms.get(i).checkOnPlatform(player)
                    && !player.isJumping) {

               //Tell the platfrom that the player is no longer on it
               platforms.get(i).playerOnPlatform = false;

               //Removes the platform offset from player class
               player.setPlatform(null);

               //Hard! reset of the player to the ground. Not really smooth. should be changed
               player.fall(i);

            }
         }

         //If the platform has been moved outside the game window remove it from the list
         else {
            platforms.remove(i);
         }
      }
   }

   /**
    * do things with all of the powerups in the powerup list
    *
    * move them left
    * check collision and handles on collision actions
    * remove if out of window bounds
    *
    */
   private void checkPowerups() {

      //Do Things with every Powerup in the List
      for (int i = 0; i < powerups.size(); i++) {

         //If powerups is inside the game window move it left
         //and check the collision with the player
         if (!powerups.get(i).checkOutisde()) {

            powerups.get(i).moveLeft();

            if (player.checkCollision(powerups.get(i))) {

               //If player collides with the powerup give him 5 points
               //and remove the powerup from the powerup list
               increaseScore(5);


               ScorePopup scorePopup = new ScorePopup(powerups.get(i).getY()-10,
                       (powerups.get(i).getX()+powerups.get(i).getWidth()/2),
                       5,false,GameModel.this);

               scorePopups.add(scorePopup);


               powerups.remove(i);

            }
         }

         //If powerup is outside the screen bound remove it from the powerup list
         else {
            powerups.remove(i);
         }
      }
   }

   /**
    * Check every existing Obstacle
    * look up if it is ouside of the screen, then remove it from obstacle list
    *
    * for all inside obstacles:
    *
    * move them left and check collision with player
    * if there is a collision stop game and jump and set gameover to true
    */
   private void checkObstacles() {

      //Do Things with all obstacles
      for (int i = 0; i < obstacles.size(); i++) {

         //If inside of the window move and check for collision
         if (!obstacles.get(i).checkOutisde()) {

            obstacles.get(i).moveLeft();

            //If collision from player and obstacle stop this game
            if (player.checkCollision(obstacles.get(i))) {

               stopGameTimer();

               gameOver = true;

               player.stopJumpTimer();

               //Call the gameController stopUpdate Timer delayed so every view hast the change to update to
               //the last view state
               new Timer().schedule(new TimerTask() {
                  @Override
                  public void run() {
                     gameController.stopUpdater();
                  }
               }, 50);

               System.out.println("Collision!");
            }
         }

         //If outside of the window remove obstacle from list
         else {
            obstacles.remove(i);
         }
      }
   }

   /**
    * Checks if the player jumped completly over an obstacle
    * if true he got that point
    */
   private void checkScore() {
      for (int i = 0; i < obstacles.size(); i++) {
         if (obstacles.get(i).getX() + obstacles.get(i).getWidth() == player.getX()) {

            if(player.getTrickJumpOn()){
               increaseScore(2);
               ScorePopup scorePopup = new ScorePopup(obstacles.get(i).getY()-30,
                       obstacles.get(i).getX()+(obstacles.get(i).getWidth()/2),
                       2,true,this);
               scorePopups.add(scorePopup);
            }else{
               increaseScore(1);
               ScorePopup scorePopup = new ScorePopup(obstacles.get(i).getY()-30,
                       obstacles.get(i).getX()+(obstacles.get(i).getWidth()/2),
                       1,false,this);
               scorePopups.add(scorePopup);

            }

            System.out.println("Score POP: " + getScorePopups().size());
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

      }

      //if Obstacle Timer is down and there is a active lvl fire a new Game Object
      if (obstacleTimer == 0 && levels.getActiveLvl() != R.EMPTY) {

         if (lvlPause) {

            //Get Level Array for active lvl from Level class
            levelArray = levels.getActiveLvlArray();
            lvlPause = false;

         } else {

            //Create a new Game Object here
            if (lvlIndex != R.EMPTY) {

               //If GameObject type in lvl Array is between 0 and 99 it's an Obstacle
               if (levelArray[1][lvlIndex - 1] < 100) {
                  //Create the Obstacle with Type and y Offset from levelArray
                  createObstacle(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
               }

               //If Gameobject type in lvl Array is between 100 and 199 it's an Powerup
               else if (levelArray[1][lvlIndex - 1] >= 100 && levelArray[1][lvlIndex - 1] < 200) {
                  //Create the powerup with type and yOffset from levelArray
                  createPowerup(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
               }

               //If gameobject type in lvl Array ist between 200 and 299 it's a Platform
               else if (levelArray[1][lvlIndex - 1] >= 200 && levelArray[1][lvlIndex - 1] < 300) {
                  //Create the platform with type and y offset from level Array
                  createPlatform(levelArray[1][lvlIndex - 1], levelArray[2][lvlIndex - 1]);
               }
            }
         }

         //Check if next stuff in the lvl Array ist an obstacle or an powerup or Platform
         //If lvl index = -1 there are no more obstacles in this lvl and he should not load more
         if (lvlIndex != R.EMPTY && lvlIndex < levelArray[0].length) {

            //For the obstacles in levelArray
            if (levelArray[1][lvlIndex] < 100) {

               //Create a helping obstacle to check obstacle width
               Obstacle helpObstacle = new Obstacle(levelArray[1][lvlIndex]);

               //set new obstacle timer by lvl array data and add obstacle width offset
               //so obstacles cannot lie in each other
               obstacleTimer = levelArray[0][lvlIndex] + helpObstacle.getWidth();
            }

            //For the Powerups in Level Array
            else if (levelArray[1][lvlIndex] >= 100 && levelArray[1][lvlIndex] < 200) {

               //create a helping powerup instance to get width from it
               Powerup helpPowerup = new Powerup(levelArray[1][lvlIndex], paneWidth, paneHeight, 0);

               //Set obstacle timer by lvl array data and add powerup with as offset
               //so no gameobject can be inside the powerup
               obstacleTimer = levelArray[0][lvlIndex] + helpPowerup.getWidth();
            }

            //for the platforms in levelArray:
            else if (levelArray[1][lvlIndex] >= 200 && levelArray[1][lvlIndex] < 300) {
               obstacleTimer = levelArray[0][lvlIndex];
            }

            lvlIndex += 1;

         }

         // if lvl index is out of bounds the active lvl is completly done.
         // set activelvl to -1 and reset lvl index
         else if (lvlIndex >= levelArray[0].length) {
            lvlIndex = R.EMPTY;
            levels.setActiveLvl(R.EMPTY);
         } else {
            levels.setActiveLvl(R.EMPTY);
         }

      }

      //decrase the obstacle timer on every time checkLvL's is called
      if (obstacleTimer != 0) {
         obstacleTimer -= 1;
      }
   }

   private void increaseScore(int value){
      score += value;
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

   public GameController getGameController() {
      return gameController;
   }

   public ArrayList<ScorePopup> getScorePopups() {
      return scorePopups;
   }
}
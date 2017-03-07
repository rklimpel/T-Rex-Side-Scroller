package main.java.cau.project;

import javafx.scene.paint.Color;

/**
 * Global Constants for Game Configuration
 */
public class R {

   /**
    * Player
    **/

   //Players Size:
   public static final int playerHeight = 100;
   public static final int playerWidth = 40;

   //Players crouched size multiplikator (player height * crouchsize)
   public static final double playerCrouchSize = 0.4;

   //Player Color:
   public static final Color playerColor = Color.RED;

   //Player's Jump configuration
   public static final int playerJumpTimerDelay = 10;
   public static final double playerGravitation = 20;
   public static final double playerJumpSpeed = 80;
   public static final double crouchingPlayerJumpSpeed = 50;


   //Aktivate and deaktivate player jump rotation:
   public static final Boolean playerRotation = false;

   //How far the Player roates on one jump:
   //0.5 = 180° ; 1 = 360° ... 0.5 steps
   public static final double playerJumpRotation = 0.5;

   /**
    * Obstacle
    **/

   //Obstacle Size:
   public static final int obstacleHeight0 = 80;
   public static final int obstacleWidth0 = 20;

   public static final int obstacleHeight1 = 50;
   public static final int obstacleWidth1 = 20;

   public static final int obstacleHeight2 = 30;
   public static final int obstacleWidth2 = 100;

   /**
    * Powerups
    */

   public static final int powerupHeight = 35;
   public static final int powerupWidth = 35;

   /**
    * Platforms
    */

   public static final int platformHeight = 10;
   public static final int platformWidth = 600;

   /**
    * Game Parameters
    **/

   //Game Timer Settings in Milliseconds:
   public static final int gameTimerOffset = 0;
   public static final int gameTimerDelay = 3;

   //Game Ticks between two sample_levels:
   public static int obstaclePauseTimer = 500;

   //Load Rectangles or Images for Gameplay
   public static final Boolean gameobjectsAsImages = true;

   //MUSIC on off default value
   public static Boolean musicOn = false;

   //Ground lvl, offset of all gameobjects from pane bottom
   public static final int groundLvL = 30;

   //Ground Height
   public static final int groundSize = 1;

   /**
    * Helper Consants
    **/

   //Empty INT
   public static final int EMPTY = -1;

   /**
    * Level Things
    **/

   //Save Lvl Configuration File Names here
   public static final String lvl_freeTesting = "free_testing";
   public static final String lvl_samples = "sample_levels";
   public static final String lvl_powerupTesting = "powerup_test";

   //Path to Levels File
   public static final String levelsPath = "./src/main/res/leveldata/";

   //Put all Lvl Files that should be loaded in this String Array. Lvl Loader will do the rest....
   public static String[] levelFiles = {lvl_powerupTesting};

   /**
    * Lighthouse
    **/

   public static final int lighthouseWidth = 28;
   public static final int lighthouseHeight = 14;

   /**
    * View ID'S
    */

   public static final String viewIdGameDesktop = "GD";
   public static final String viewIdGameLighthouse = "GL";
   public static final String viewIdMenu = "ME";
   public static final String viewIdEnd = "EN";
   public static final String viewIdSplit = "SP";
   public static final String viewIdSplitChooser = "SC";

   /**
    * Scene Switcher FXML View Paths's
    */
   public static final String viewPathGameDesktop= "../screens/game/view/desktop/DesktopView.fxml";
   public static final String viewPathMenu = "../screens/menu/MenuView.fxml";
   public static final String viewPathGameLighthouse = "../screens/game/view/lh/LhView.fxml";
   public static final String viewPathGameEnd = "../screens/end/Endview.fxml";
   public static final String viewPathGameSplit = "../screens/split/SplitView.fxml";
   public static final String viewPathSplitChooser = "../screens/split/SplitChooserView.fxml";

   /**
    * Font Id's
    */

   public static final String fontPixel = "game_over";

   /**
    * Developer Debugging Helpers
    **/

   //Only play one lvl (-1 = normal game)
   public static final int onlyLvl = -1;

}
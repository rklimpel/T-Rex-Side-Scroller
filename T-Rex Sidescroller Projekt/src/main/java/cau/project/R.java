package main.java.cau.project;

import javafx.scene.paint.Color;

/**
 * Global Constants for Game Configuration
 */
public class R {

    /** Player **/

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

    /** Obstacle **/

    //Obstacle Size:
    public static final int obstacleHeight = 80;
    public static final int obstacleWidth = 20;

    /** Game Parameters **/

    //Game Timer Settings in Milliseconds:
    public static final int gameTimerOffset = 0;
    public static final int gameTimerDelay = 3;

    //Game Ticks between two sample_levels.txt:
    public static int obstaclePauseTimer = 500;

    //Load Rectangles or Images for Gameplay
    public static final Boolean gameobjectsAsImages = true;

    //Ground lvl, offset of all gameobjects from pane bottom
    public static final int groundLvL = 50;

    //Ground Height
    public static final int groundSize = 1;

    /** Helper Consants **/

    //Empty INT
    public static final int EMPTY = -1;

    //Path to Levels File
    public static final String levelsPath = "./src/main/res/leveldata/sample_levels.txt";

    /** Developer Debugging Helpers **/

    //Only play one lvl (-1 = normal game)
    public static final int onlyLvl = -1;

}
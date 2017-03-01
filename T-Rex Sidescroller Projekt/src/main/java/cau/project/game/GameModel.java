package main.java.cau.project.game;

import main.java.cau.project.R;
import main.java.cau.project.game.view_desktop.DesktopController;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
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

    //is the Timer enabled?
    public  Boolean gameTimerEnabled = false;

    //Jump waiting
    private Boolean jumpWaiting = false;

    //Delay, before the timer starts
    private int gameTimerOffset = R.gameTimerOffset;
    private int gameTimerDelay = R.gameTimerDelay;
    //optimum:3

    //The Player Object that contains to the Game
    public Player player;

    //Game Controller who called the GameModel, set on contsructor
    private Controller gameController;

    //Save Pane Size here when constructor gets called:
    private int paneWidth;
    private int paneHeight;

    //Remembers the Players GameScore
    private int score;

    //if player died this will be true:
    public Boolean gameOver = false;

    //List off all Obstacles that are in the Game
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    /**
     * Creates a new GameModel Instance (i think there should be just one...)
     * saves Controller to class variable
     *
     * @param controller
     */
    public GameModel(Controller controller) {
        this.gameController = controller;
    }

    /**
     * Gets Pane Size from DesktopController
     */
    private void getPane() {
        paneWidth = gameController.getPaneWidth();
        paneHeight = gameController.getPaneHeight();

        //System.out.println("paneWidth: " + paneWidth);
        //System.out.println("paneHeight: " + paneHeight);
    }

    /**
     * Creates a new Player instace from Player class
     */
    public void createPlayer() {
        getPane();
        player = new Player(paneWidth, paneHeight);
    }

    /**
     * Called by Space, makes the player jump
     */
    public void jump() {

        if (!player.isJumping) {
            player.jump();
        }else if(paneHeight- R.groundLvL - player.getY()
                <= player.getHeight()/2+player.getHeight()){
            jumpWaiting = true;
        }
    }

    /**
     * Creates a new Obstacle instance from Obstacle class
     */
    public void createObstacle(int yOffset){
        Obstacle obstacle = new Obstacle(paneWidth,paneHeight,yOffset);
        obstacles.add(obstacle);
    }

    /**
     * Starts the continous ticking Game Timer
     */
    public void startGameTimer() {
        TimerTask task = new TimerTask() {
            public void run() {
                GameTick();
            }
        };
        gameTimer.scheduleAtFixedRate(task, gameTimerOffset, gameTimerDelay);
        gameTimerEnabled = true;

        getPane();

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

        if(jumpWaiting && !player.isJumping){
            jump();
            jumpWaiting = false;
        }

        for (int i = 0; i < obstacles.size(); i++) {
            if (!obstacles.get(i).checkOutisde()) {

                obstacles.get(i).moveLeft();

                if(player.checkCollision(obstacles.get(i))){

                    stopGameTimer();
                    gameOver = true;

                    System.out.println("Collision!");
                    player.stopJumpTimer();
                }

            }else{
                obstacles.remove(i);
                //System.out.println("removed obstacle");
            }
        }

        //Notify the Gamecontroller about value changes
        gameController.Update();
    }

    /**
     * Checks if the player jumped completly over an obstacle
     */
    private void checkScore() {

        for (int i = 0; i < obstacles.size(); i++) {
            if(obstacles.get(i).getX()+obstacles.get(i).getWidth()==player.getX()){
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
        if(levels.getActiveLvl()==R.EMPTY){

            levels.setRandomLvl();
            obstacleTimer = R.obstaclePauseTimer;
            lvlPause = true;
            lvlIndex = 0;

            //System.out.println("obstacle timer: " + obstacleTimer);
            //System.out.println("lvl index " + lvlIndex);
            //System.out.println("pause: " + lvlPause +"\n");
        }

        //if Obstacle Timer is down and there is a active lvl fire a new Obstacle
        if(obstacleTimer == 0 && levels.getActiveLvl() != R.EMPTY){

            if(lvlPause == true){

                //Get Level Array for active lvl from Level class
                levelArray = levels.getActiveLvlArray();
                lvlPause = false;

            }else{

                //Create a new Obstacle Object
                if(lvlIndex!=R.EMPTY){
                    createObstacle(levelArray[2][lvlIndex-1]);
                    System.out.println("Create Obstacle- index: " + (lvlIndex-1));
                }

                //System.out.println("Object created! \n");
            }


            //If lvl index = -1 there are no more obstacles in this lvl and he should not load more
            //Else do the normal stuff
            if(lvlIndex!=R.EMPTY && lvlIndex <levelArray[0].length){
                //Create a helping obstacle to check obstacle width
                Obstacle helpObstacle = new Obstacle();

                //set new obstacle timer by lvl array data and add obstacle width offset
                obstacleTimer = levelArray[0][lvlIndex] + helpObstacle.getWidth();

                lvlIndex+=1;
            }
            // if lvl index is out of bounds set activelvl to -1 and reset lvl index
            else if(lvlIndex >= levelArray[0].length){
                lvlIndex = R.EMPTY;
                levels.setActiveLvl(R.EMPTY);
            }else{
                levels.setActiveLvl(R.EMPTY);
            }

        }

        if(obstacleTimer != 0){
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
}
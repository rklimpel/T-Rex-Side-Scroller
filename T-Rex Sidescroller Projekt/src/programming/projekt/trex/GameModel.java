package programming.projekt.trex;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameModel {

    //Lvl Arrays
    int[] lvl1 = {10, 300, 500, 10, 10, 800, 10, 700};
    int[] lvl2 = {10, 300, 500, 10, 10, 800, 10, 700};

    static int score=0;
    int lvlIndex = 0;
    int obstacleTimer = 0;
    Boolean lvlPause = true;

    Levels levels = new Levels();

    int[] levelArray;


    //Counts up how often the game timer has ticked,
    //can be used to generate the complete gametime
    int timerTick = 0;

    //GameTimer von continoues moves
    Timer gameTimer = new Timer();

    //is the Timer enabled?
    Boolean gameTimerEnabled = false;

    //Delay, before the timer starts
    int gameTimerOffset = 0;
    int gameTimerDelay = 3;
    //optimum:3

    //The Player Object that contains to the Game
    Player player;

    //Game Controller who called the GameModel, set on contsructor
    GameController gameController;

    //Save Scene Size when constructor gets called:
    int SceneWidth;
    int SceneHeight;

    //Save Pane Size here when constructor gets called:
    int paneWidth;
    int paneHeight;

    //List off all Obstacles that are in the Game
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    /**
     * Creates a new GameModel Instance (i think there should be just one...)
     * saves Controller to class variable
     *
     * @param gameController
     */
    public GameModel(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Gets Pane Size from GameController
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
        }
    }

    /**
     * Creates a new Obstacle instance from Obstacle class
     */
    public void createObstacle(){
        Obstacle obstacle = new Obstacle(paneWidth,paneHeight);
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

        for (int i = 0; i < obstacles.size(); i++) {
            if (!obstacles.get(i).checkOutisde()) {

                obstacles.get(i).moveLeft();

                if(player.checkCollision(obstacles.get(i))){

                    stopGameTimer();

                    System.out.println("Collision!");
                    player.stopJumpTimer();
                }

            }else{
                obstacles.remove(i);
                score+=1;
                //System.out.println("removed obstacle");
            }
        }

        //Notify the Gamecontroller about value changes
        gameController.Update();
    }

    /**
     * reads the lvl-Array and creates the objects on the
     * specific spots.
     */
    private void checklvl() {

        //If no lvl is active set a new random lvl as active lvl and
        //reset obstacle Timer and lvl Index
        if(levels.getActiveLvl()==-1){

            levels.setRandomLvl();
            obstacleTimer = 500;
            lvlPause = true;
            lvlIndex = 0;

            System.out.println("obstacle timer: " + obstacleTimer);
            System.out.println("lvl index " + lvlIndex);
            System.out.println("pause: " + lvlPause +"\n");
        }

        //if Obstacle Timer is down and there is a active lvl fire a new Obstacle
        if(obstacleTimer == 0 && levels.getActiveLvl() != -1){

            if(lvlPause == true){

                //Get Level Array for active lvl from Level class
                levelArray = levels.getActiveLvlArray();

                lvlPause = false;

            }else{

                createObstacle();
                System.out.println("Object created! \n");

            }

            //If lvl index = -1 there are no more obstacles in this lvl and he should not load more
            //Else do the normal stuff
            if(lvlIndex!=-1){
                //Create a helping obstacle to check obstacle width
                Obstacle helpObstacle = new Obstacle(0,0);

                //set new obstacle timer by lvl array data and add obstacle width offset
                obstacleTimer = levelArray[lvlIndex] + helpObstacle.getWidth();

                lvlIndex+=1;

                // if lvl index is out of bounds set activelvl to -1 and reset lvl index
                if(lvlIndex >= levelArray.length){
                    lvlIndex = -1;
                }
            }else{
                levels.setActiveLvl(-1);
            }


        }

        if(obstacleTimer != 0){
            obstacleTimer -= 1;
        }


    }

}

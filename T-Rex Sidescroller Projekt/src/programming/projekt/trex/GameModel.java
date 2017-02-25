package programming.projekt.trex;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
//array for the levelstructure
public class GameModel {
    int[] lvl = {10, 300, 500, 10, 10, 800, 10, 700};

    int counterLvlArray = 0;
    int obstacleTick = 0;


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

    Obstacle obstacle;

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
    public void createObstacle() {
        obstacle = new Obstacle(paneWidth, paneHeight);
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
            } else {
                obstacles.remove(i);
                System.out.println("removed obstacle");
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


        if (obstacles != null && obstacleTick == lvl[counterLvlArray]/*+  obstacles.get(obstacles.size()-1).getWidth()*/) {
            createObstacle();


            counterLvlArray += 1;
            obstacleTick = 0;

        }
        if (obstacles == null && obstacleTick == lvl[counterLvlArray]) {
            createObstacle();


            counterLvlArray += 1;
            obstacleTick = 0;
        }
        if (counterLvlArray == lvl.length - 1) {
            counterLvlArray = 0;
        }
        obstacleTick += 1;
    }


}

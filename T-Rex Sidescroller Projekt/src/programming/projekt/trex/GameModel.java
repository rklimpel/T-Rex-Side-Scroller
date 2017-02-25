package programming.projekt.trex;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameModel{

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
    public GameModel(GameController gameController){
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
    public void createPlayer(){
        getPane();
        player = new Player(paneWidth,paneHeight);
    }

    /**
     * Called by Space, makes the player jump
     */
    public void jump(){
        if(!player.isJumping){
            player.jump();
        }
    }

    /**
     * Creates a new Obstacle instance from Obstacle class
     */
    public void createObstacle(){
        obstacle = new Obstacle(paneWidth,paneHeight);
        obstacles.add(obstacle);
    }

    /**
     * Starts the continous ticking Game Timer
     */
    public void startGameTimer(){
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
    public void stopGameTimer(){
        gameTimer.cancel();
        gameTimer.purge();
        gameTimerEnabled = false;
    }

    /**
     * Fires on every GameTimer tick
     * object and other movements should be done here
     *
     * sends update notification to controller
     */
    public void GameTick(){

        timerTick += 1;

        for (int i = 0; i < obstacles.size(); i++) {
            if(!obstacles.get(i).checkOutisde()){
                obstacles.get(i).moveLeft();
            }else{
                obstacles.remove(i);
                System.out.println("removed obstacle");
            }
        }

        //Notify the Gamecontroller about value changes
        gameController.Update();
    }
}

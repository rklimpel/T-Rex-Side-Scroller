package programming.projekt.trex;

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
    int gameTimerDelay = 10;

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

    public GameModel(GameController gameController){
        this.gameController = gameController;
    }

    private void getPane() {
        paneWidth = gameController.getPaneWidth();
        paneHeight = gameController.getPaneHeight();
    }

    public void createPlayer(){
        player = new Player();
    }

    public void createObstacle(){
        getPane();obstacle = new Obstacle(paneWidth,paneHeight);
    }

    public void startGameTimer(){
        getPane();
        TimerTask task = new TimerTask() {
            public void run() {
                GameTick();
            }
        };
        gameTimer.scheduleAtFixedRate(task, gameTimerOffset, gameTimerDelay);
        gameTimerEnabled = true;
    }

    public void stopGameTimer(){
        gameTimer.cancel();
        gameTimer.purge();
        gameTimerEnabled = false;
    }

    public void GameTick(){

        timerTick += 1;

        obstacle.moveLeft();

        //Notify the Gamecontroller about value changes
        gameController.Update();
    }
}

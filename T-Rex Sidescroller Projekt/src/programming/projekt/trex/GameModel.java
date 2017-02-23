package programming.projekt.trex;

import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameModel{

    int TimerTick = 0;
    Timer gameTimer = new Timer();
    Boolean gameTimerEnabled = false;
    int gameTimerOffset = 0;
    int gameTimerDelay = 10;

    Player player;

    GameController gameController;

    public GameModel(GameController gameController){
        this.gameController = gameController;
    }

    public void createPlayer(){
        player = new Player();
    }

    public void startGameTimer(){

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

        player.moveright();

        gameController.Update();
    }
}

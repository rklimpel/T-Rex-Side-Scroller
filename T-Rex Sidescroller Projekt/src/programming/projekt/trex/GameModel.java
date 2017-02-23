package programming.projekt.trex;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameModel{

    Timer gameTimer = new Timer();
    int gameTimerOffset = 0;
    int gameTimerDelay = 500;

    public void startGameTimer(){

        TimerTask task = new TimerTask() {
            public void run() {
                GameTick();
            }
        };
        gameTimer.scheduleAtFixedRate(task, gameTimerOffset, gameTimerDelay);
    }

    public void GameTick(){
        System.out.println("Hallo");

        //Move Obstacles here

    }
}

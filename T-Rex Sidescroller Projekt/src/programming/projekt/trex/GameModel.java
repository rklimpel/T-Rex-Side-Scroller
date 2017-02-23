package programming.projekt.trex;

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
    int gameTimerDelay = 500;

    Player player;

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
        System.out.println(TimerTick+=1);

        //Move Obstacles here

    }
}

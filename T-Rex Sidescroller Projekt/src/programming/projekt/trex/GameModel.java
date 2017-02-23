package programming.projekt.trex;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameModel{

    Timer timer = new Timer();

    public void startGameTimer(){

        //start offeset
        int initialDelay = 0;
        //repeat delay
        int delay = 500;

        TimerTask task = new TimerTask() {
            public void run() {
                GameTick();
            }
        };
        timer.scheduleAtFixedRate(task, initialDelay, delay);
    }

    public void GameTick(){
        System.out.println("Hallo");

        //Move Obstacles

    }
}

package main.java.cau.project.screens.game.model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Small Popup Labels appearing over an Obstacle and show points the player got
 */
public class ScorePopup extends GameObject{

   private String text = "";
   private double opacity = 1;
   private GameModel gameModel;

   public ScorePopup(int y, int x, int score,Boolean trickPoints,GameModel gameModel) {
      this.y = y;
      this.x = x;
      this.gameModel = gameModel;

      if(!trickPoints){
         this.text = "+"+score;
      }else{
         this.text = "+"+score;
      }

      Animation();

   }

   public String getText() {
      return text;
   }

   private void Animation(){
      Timer timer = new Timer();
      TimerTask timerTask = new TimerTask() {
         @Override
         public void run() {
            opacity -= 0.01;
            y -= 0.1;
            if(opacity <= 0){
               timer.purge();
               gameModel.getScorePopups().remove(ScorePopup.this);
            }
         }
      };
      timer.scheduleAtFixedRate(timerTask, 0,10);
   }

   public double getOpacity() {
      return opacity;
   }
}

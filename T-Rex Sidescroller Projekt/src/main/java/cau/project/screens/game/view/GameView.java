package main.java.cau.project.screens.game.view;

import main.java.cau.project.Main;
import main.java.cau.project.View;
import main.java.cau.project.screens.game.controller.GameController;

public class GameView extends View{

   public GameController controller;

   public long lastTime;
   public int frames;

   public GameView() {


   }

   public void setController(int paneWidth, int paneHeight){

      if (Main.gameController == null) {
         Main.gameController = new GameController(view);
         controller = Main.gameController;
      } else {
         Main.gameController.addListener(view);
         controller = Main.gameController;
      }
   }

   public void setController(){
      this.setController(800,400);
   }

   @Override
   public void Update() {

   }

   @Override
   public void exit(int i) {

   }

   public void calcAndShowFPS(){

      if(System.currentTimeMillis() >= lastTime+1000){
         System.out.println("FPS: " + frames);
         lastTime = System.currentTimeMillis();
         frames = 0;
      }
      frames +=1;
   }
}

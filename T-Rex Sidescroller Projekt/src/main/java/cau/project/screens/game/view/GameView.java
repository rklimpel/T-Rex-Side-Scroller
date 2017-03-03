package main.java.cau.project.screens.game.view;

import javafx.application.Platform;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.View;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.screens.split.SplitView;
import main.java.cau.project.services.SceneSwitcher;

public class GameView extends View{

   public GameController controller;

   public long lastTime;
   public int frames;

   public GameView() {


   }

   public void setController(int paneWidth, int paneHeight){

      if (Main.gameController == null) {
         Main.gameController = new GameController(view,paneWidth,paneHeight);
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

   /**
    * Exit Desktop Game Screen when getting exit call from Game Controller
    * <p>
    * check for Gameover, if not exit to menu
    *
    */
   @Override
   public void exit(String nextViewID) {

      Main.stage.getScene().getRoot().setEffect(null);

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            if(Main.getMainView().getViewID()!=R.viewIdSplit) {

               if (nextViewID == R.viewIdEnd) {
                  SceneSwitcher.END.load();
               } else if (nextViewID == R.viewIdMenu) {
                  SceneSwitcher.MENU.load();
               }

            }else if(Main.getMainView().getViewID()==R.viewIdSplit){

               SplitView splitView = (SplitView)Main.getMainView();
               splitView.setSub2(SceneSwitcher.END.getRoot());
               splitView.setSub1(SceneSwitcher.END.getRoot());

            }
         }
      });

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

package main.java.cau.project.services;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.View;
import main.java.cau.project.end.EndView;
import main.java.cau.project.menu.MenuView;

public class KeyboardListener implements EventHandler<KeyEvent> {

   View view;

   public KeyboardListener(View view) {
      this.view = view;

      Main.stage.getScene().setOnKeyPressed(this);
      Main.stage.getScene().setOnKeyReleased(this);
   }

   @Override
   public void handle(KeyEvent event) {

      //For Press Events
      if (event.getEventType() == KeyEvent.KEY_PRESSED) {

         //IF Caller Is Desktop Game
         if (view.getViewID() == R.viewIdGameDesktop) {

            //On Space Pressed
            if (event.getCode() == KeyCode.SPACE) {
               Main.gameController.jump(view);

            }

            //On Escape Pressed:
            else if (event.getCode() == KeyCode.ESCAPE) {
               Main.gameController.quitGame(view);
            }

            //On Control Pressed
            else if (event.getCode() == KeyCode.CONTROL) {
               Main.gameController.crouch();
            }

         }

         //If Caller is Menu View
         else if (view.getViewID() == R.viewIdMenu) {

            //On Enter pressed
            if (event.getCode() == KeyCode.ENTER) {
               MenuView menuView = (MenuView) view;
               menuView.startGameDesktop();
            }
         }

         //If Caller is End View
         else if (view.getViewID() == R.viewIdEnd){

            if(event.getCode() == KeyCode.ENTER){
               EndView endView = (EndView)view;
               endView.restartGame();
            }

            else if (event.getCode() == KeyCode.ESCAPE){
               view.exit(-1);
            }

         }
      }

      //Key Event Release
      else if (event.getEventType() == KeyEvent.KEY_RELEASED) {

         //IF Caller Is Desktop Game
         if(view.getViewID() == R.viewIdGameDesktop){

            //On Control Pressed
            if (event.getCode() == KeyCode.CONTROL) {
               Main.gameController.crouchEnd();
            }
         }

      }
   }
}

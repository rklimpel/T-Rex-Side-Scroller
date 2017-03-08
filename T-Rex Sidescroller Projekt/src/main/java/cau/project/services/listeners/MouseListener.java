package main.java.cau.project.services.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.java.cau.project.Main;
import main.java.cau.project.View;


/**
 * class for handling keyboard-events
 */
public class MouseListener implements EventHandler<MouseEvent> {

   View view;

   /**
    * constructer for every kind of used mouselistener
    * the different actions are splitted:
    *
    * 1. by pressed and released
    * 2. by the view which calls the listener
    * 3. by the mousebutton itself
    *
    * @param view view which calls the listener
    */
   public MouseListener(View view) {
      this.view = view;

      Main.stage.getScene().setOnMousePressed(this);
      Main.stage.getScene().setOnMouseReleased(this);
   }

   @Override
   public void handle(MouseEvent event) {
      //For Press Events
      if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {

         //setting the jumpaction to right-mouse button
         if (event.getButton() == MouseButton.PRIMARY) {

            Main.gameController.jump(view);

            //setting the crouch-started-acton to left-click-on mouse
         } else if (event.getButton() == MouseButton.SECONDARY) {

            Main.gameController.crouch();

         }

         //For Release Events
      } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {

         //when the mouse-left-click ends the crouch ends as well
         if (event.getButton() == MouseButton.SECONDARY) {

            Main.gameController.crouchEnd();

         }
      }
   }
}

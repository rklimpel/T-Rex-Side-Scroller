package main.java.cau.project.services.listeners;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.java.cau.project.Main;
import main.java.cau.project.View;


public class MouseListener implements EventHandler<MouseEvent> {

   View view;

   public MouseListener(View view) {
      this.view = view;

      Main.stage.getScene().setOnMousePressed(this);
      Main.stage.getScene().setOnMouseReleased(this);
   }

   @Override
   public void handle(MouseEvent event) {

      if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
         if (event.getButton() == MouseButton.PRIMARY) {
            Main.gameController.jump(view);
         } else if (event.getButton() == MouseButton.SECONDARY) {
            Main.gameController.crouch();
         }
      } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
         if (event.getButton() == MouseButton.SECONDARY) {
            Main.gameController.crouchEnd();
         }
      }
   }
}

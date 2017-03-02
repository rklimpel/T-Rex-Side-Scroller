package main.java.cau.project.screens.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.cau.project.*;
import main.java.cau.project.screens.game.view.lh.LhView;
import main.java.cau.project.services.LighthouseNetwork;
import main.java.cau.project.services.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;


public class MenuView extends View {

   @FXML
   public Button btn_startDesktop;
   @FXML
   public Button btn_startLighthouse;

   private Boolean lighthouseConnected = false;

   private View view;

   LighthouseNetwork lighthouseNetwork = new LighthouseNetwork();

   public MenuView() {

      view = this;
      setViewID(R.viewIdMenu);

      try {
         lighthouseNetwork.connect();
         lighthouseConnected = true;
      } catch (IOException e) {
         e.printStackTrace();
         lighthouseConnected = false;
      }

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            new KeyboardListener(view);

            if (lighthouseConnected) {
               btn_startLighthouse.setStyle("-fx-background-color:  #4caf50;");
            } else {
               btn_startLighthouse.setStyle("-fx-background-color:  #f44336;");
            }

         }
      });
   }

   public void startGameDesktop() {
      SceneSwitcher.GAME_DESKTOP.load();
   }

   public void onClick_btn_startLighthouse() {
      /*try {
         lighthouseNetwork.send(Helper.convertLighthouseImage());
      } catch (IOException e) {
         e.printStackTrace();
      }*/
      SceneSwitcher.GAME_LH.load();
   }

   public void onClick_btn_startSplit(ActionEvent actionEvent) {
      SceneSwitcher.GAME_DOUBLE.load();
   }
}
package main.java.cau.project.screens.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.java.cau.project.*;
import main.java.cau.project.services.LighthouseNetwork;
import main.java.cau.project.services.LighthouseService;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;


public class MenuView extends View {

   @FXML
   public Button btn_startDesktop;
   @FXML
   public Button btn_startLighthouse;

   private Boolean lighthouseConnected = false;

   private View view;

   LighthouseService lighthouseService = new LighthouseService();

   public MenuView() {

      view = this;
      setViewID(R.viewIdMenu);

      Main.setMainView(this);

      lighthouseConnected = lighthouseService.connect();
      Main.setLighthouseService(lighthouseService);

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
      SceneSwitcher.GAME_LH.load();
   }

   public void onClick_btn_startSplit(ActionEvent actionEvent) {
      SceneSwitcher.GAME_DOUBLE.load();
   }
}
package main.java.cau.project.screens.menu;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import main.java.cau.project.*;
import main.java.cau.project.services.LighthouseService;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class MenuView extends View {

   @FXML
   public Button btn_startDesktop;
   @FXML
   public Button btn_startLighthouse;
   @FXML
   public Button btn_startSplit;
   @FXML
   public CheckBox checkBoxMusic;
   @FXML
   public GridPane background;

   private Boolean lighthouseConnected = false;

   private View view;

   LighthouseService lighthouseService = new LighthouseService();

   Timer timer;

   public MenuView() {

      view = this;
      setViewID(R.viewIdMenu);

      Main.setMainView(this);

      Main.setLighthouseService(lighthouseService);

      checkLighthouseConnection();

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            new KeyboardListener(view);

            styleButtons();

            URL url = this.getClass().getResource("/main/res/assets/mexiko/background.png");

            Image image = null;
            try {
               image = new Image(url.openStream(),background.getWidth(),background.getHeight(),false,false);
            } catch (IOException e) {
               e.printStackTrace();
            }

            BackgroundImage myBI= new BackgroundImage(image,
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);

            background.setBackground(new Background(myBI));

            checkBoxMusic.setSelected(R.musicOn);

            checkBoxMusic.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                  if(checkBoxMusic.isSelected()){
                     R.musicOn = true;
                  }else{
                     R.musicOn = false;
                  }
               }
            });

         }
      });
   }

   private void styleButtons() {
      btn_startDesktop.setStyle("-fx-text-fill: white; -fx-base:  #795548;");
      btn_startSplit.setStyle("-fx-text-fill: white; -fx-base:  #795548;");
   }

   private void checkLighthouseConnection(){

      timer = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {

            lighthouseConnected = lighthouseService.connect();

            Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  if (lighthouseConnected) {
                     btn_startLighthouse.setStyle("-fx-base:  #4caf50;");

                  } else {
                     btn_startLighthouse.setStyle("-fx-base:  #f44336;");
                  }
               }
            });
         }
      };
      timer.scheduleAtFixedRate(task, 0, 2000);

   }

   public void startGameDesktop() {
      timer.purge();
      SceneSwitcher.GAME_DESKTOP.load();
   }

   public void onClick_btn_startLighthouse() {
      timer.purge();
      SceneSwitcher.GAME_LH.load();
   }

   public void onClick_btn_startSplit(ActionEvent actionEvent) {
      timer.purge();
      SceneSwitcher.GAME_SPLIT.load();
   }
}
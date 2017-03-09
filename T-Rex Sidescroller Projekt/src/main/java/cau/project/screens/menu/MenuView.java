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


public class MenuView extends View {

   @FXML
   public Button btn_startDesktop;
   @FXML
   public Button btn_startLighthouse;
   @FXML
   public CheckBox checkBoxMusic;
   @FXML
   public GridPane background;

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

   public void startGameDesktop() {
      SceneSwitcher.GAME_DESKTOP.load();
   }

   public void onClick_btn_startLighthouse() {
      SceneSwitcher.GAME_LH.load();
   }

   public void onClick_btn_startSplit(ActionEvent actionEvent) {
      SceneSwitcher.GAME_SPLIT.load();
   }
}
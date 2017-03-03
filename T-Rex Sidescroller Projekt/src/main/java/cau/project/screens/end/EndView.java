package main.java.cau.project.screens.end;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.java.cau.project.*;
import main.java.cau.project.screens.game.view.lh.LhConverter;
import main.java.cau.project.services.LighthouseNetwork;
import main.java.cau.project.services.loader.CustomFontLoader;
import main.java.cau.project.services.Helper;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class EndView extends View{

   int score;

   @FXML
   Label lbl_endscore;
   @FXML
   Label lbl_gameover;
   @FXML
   GridPane background;
   @FXML
   Label lbl_restart;
   @FXML
   Button btn_restart;

   CustomFontLoader customFontLoader = new CustomFontLoader();

   public EndView() {

      view = this;
      setViewID(R.viewIdEnd);

      score = Helper.score;

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            new KeyboardListener(view);

            setScoreLabel();
            setGameoverLabel();
            setRestartButton();

            background.setStyle("-fx-background-color: #000000;");
            lbl_restart.setFont(customFontLoader.load(R.fontPixel,100));
         }
      });

      if(Main.getMainView().getViewID()==R.viewIdGameLighthouse){
         LighthousEnd();
      }

   }

   int x = 0;

   private void setRestartButton() {
      btn_restart.setStyle("-fx-text-fill: grey; -fx-base: black;");
   }

   private void setGameoverLabel() {
      lbl_gameover.setFont(customFontLoader.load(R.fontPixel,100));
      lbl_gameover.setTextFill(Color.WHITE);
   }

   private void setScoreLabel() {
      lbl_endscore.setText("Endscore: " + score);
      lbl_endscore.setFont(customFontLoader.load(R.fontPixel,100));
      lbl_endscore.setTextFill(Color.WHITE);
   }

   public void onButtonClicked() throws IOException {
      SceneSwitcher.GAME_DESKTOP.load();
   }

   public void restartGame() {
      if(Main.getMainView().getViewID()==R.viewIdSplit){
         SceneSwitcher.GAME_DOUBLE.load();
      }else if(Main.getMainView().getViewID()==R.viewIdGameDesktop){
         SceneSwitcher.GAME_DESKTOP.load();
      }else if(Main.getMainView().getViewID()==R.viewIdGameLighthouse){
         SceneSwitcher.GAME_LH.load();
      }

   }

   public void exit(String nextViewID){
      SceneSwitcher.MENU.load();
   }

   private void LighthousEnd(){

      Color[][] color = new Color[R.lighthouseHeight][R.lighthouseWidth];
      for (int i = 0; i < R.lighthouseHeight; i++) {
         for (int j = 0; j < R.lighthouseWidth; j++) {
            color[i][j] = Color.DARKRED;
         }
      }

      LighthouseNetwork lighthouseNetwork = new LighthouseNetwork();
      try {
         lighthouseNetwork.connect();
         lighthouseNetwork.send(Helper.convertToByteArray(color));
      } catch (IOException e) {
         e.printStackTrace();
      }


   }
}
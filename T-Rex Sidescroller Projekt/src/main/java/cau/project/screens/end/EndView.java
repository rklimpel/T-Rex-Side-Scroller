package main.java.cau.project.screens.end;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.java.cau.project.*;
import main.java.cau.project.services.CustomFontLoader;
import main.java.cau.project.services.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;

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
   }

   private void setRestartButton() {
      btn_restart.setStyle("-fx-background-color: grey;");
      btn_restart.setStyle("-fx-text-fill: black;");
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
      SceneSwitcher.GAME_DESKTOP.load();
   }

   public void exit(int i){
      SceneSwitcher.MENU.load();
   }
}
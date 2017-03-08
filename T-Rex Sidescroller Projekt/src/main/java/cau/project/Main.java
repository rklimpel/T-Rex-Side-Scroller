package main.java.cau.project;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.services.LighthouseService;
import main.java.cau.project.services.SceneSwitcher;
import main.java.cau.project.services.SoundService;

import java.awt.*;

public class Main extends Application {

   public static Stage stage;

   private static View mainView;
   public static GameController gameController;
   private static LighthouseService lighthouseService = new LighthouseService();

   @Override
   public void start(Stage primaryStage) throws Exception {

      stage = primaryStage;

      SceneSwitcher.MENU.load();

   }

   /**
    * Centers the Window (Stage) on the users Desktop
    */
   public static void centerFrame() {

      Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

      stage.setX(primaryScreenBounds.getMaxX()/2-stage.getWidth()/2);
      stage.setY(primaryScreenBounds.getMaxY()/2-stage.getHeight()/2);


   }

   public static void main(String[] args) {
      launch(args);
   }

   public static View getMainView() {
      return mainView;
   }

   public static void setMainView(View mainView) {
      Main.mainView = mainView;
   }

   public static LighthouseService getLighthouseService() {
      return lighthouseService;
   }

   public static void setLighthouseService(LighthouseService lighthouseService) {
      Main.lighthouseService = lighthouseService;
   }

}

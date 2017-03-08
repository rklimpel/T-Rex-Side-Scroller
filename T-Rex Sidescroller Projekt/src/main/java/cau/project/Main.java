package main.java.cau.project;

import javafx.application.Application;
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
   private static SoundService soundService;

   @Override
   public void start(Stage primaryStage) throws Exception {

      stage = primaryStage;

      SceneSwitcher.MENU.load();

   }

   /**
    * Centers the Window (Stage) on the users Desktop
    */
   public static void centerFrame() {
      // Get the size of the screen
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      // Move the window to the Center of Desktop
      stage.setX(dim.width / 2 - stage.getWidth() / 2);
      stage.setY(dim.height / 2 - stage.getHeight() / 2);

      System.out.println("Desktop (dimension) width: " + dim.width);
      System.out.println("Desktop (dimension) height: " + dim.width);
      System.out.println("Window (stage) width: " + stage.getWidth());
      System.out.println("Window (satge) height: " + stage.getHeight());
      System.out.println("set Window to X: " + stage.getX());
      System.out.println("set Window to Y: " + stage.getY());

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

   public static SoundService getSoundService() {
      return soundService;
   }
}

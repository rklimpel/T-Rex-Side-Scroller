package main.java.cau.project;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.screens.game.model.GameModel;
import main.java.cau.project.services.SceneSwitcher;

import java.awt.*;

public class Main extends Application {

   public static Stage stage;

   private static View mainView;
   public static GameController gameController;

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
}

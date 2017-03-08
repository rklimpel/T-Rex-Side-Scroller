package main.java.cau.project.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.view.desktop.DesktopView;
import main.java.cau.project.screens.split.SplitChooserView;

import java.io.IOException;

public enum SceneSwitcher {

   MENU(R.viewPathMenu, 300, 300),
   GAME_DESKTOP(R.viewPathGameDesktop, 800, 400),
   GAME_LH(R.viewPathGameLighthouse, 200, 200),
   END(R.viewPathGameEnd, 800, 400),
   GAME_SPLIT(R.viewPathGameSplit, 1200, 400),
   SPLIT_CHOOSER(R.viewPathSplitChooser, 800, 400);

   public String path = null;
   private int width;
   private int height;


   SceneSwitcher(final String path, final int width, final int height) {
      this.path = path;
      this.width = width;
      this.height = height;
   }

   public void load() {
      Main.stage.setScene(loadSceneFromFXML());
      Main.stage.show();
      //Main.centerFrame();
   }

   public void load(Boolean objectsAsImages) {

      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(path));

      if (path.equals(R.viewPathGameDesktop)) {
         DesktopView desktopView = (DesktopView) fxmlLoader.getController();
         desktopView.init(objectsAsImages);
      }

      Main.stage.setScene(loadSceneFromFXML());
      Main.stage.show();
      //Main.centerFrame();
   }

   public Parent getRoot() {
      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
      Parent root = null;
      try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return root;
   }

   public Parent getRoot(int sub) {
      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
      Parent root = null;
      try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      if (path.equals(R.viewPathSplitChooser)) {
         SplitChooserView splitChooserView = (SplitChooserView) fxmlLoader.getController();
         splitChooserView.init(sub);
      }

      return root;
   }


   public Parent getRoot(Boolean objectsAsImages) {
      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
      Parent root = null;
      try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      if (path.equals(R.viewPathGameDesktop)) {
         DesktopView desktopView = (DesktopView) fxmlLoader.getController();
         desktopView.init(objectsAsImages);
      }

      return root;
   }

   public Scene loadSceneFromFXML() {

      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(path));
      Parent root = null;
      try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return new Scene(root, width, height);
   }

}

package main.java.cau.project.services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import main.java.cau.project.Main;

import java.io.IOException;

public enum SceneSwitcher {

   MENU("../screens/menu/MenuView.fxml",300,300),
   GAME_DESKTOP("../screens/game/view/desktop/DesktopView.fxml",800,400),
   GAME_LH("../screens/game/view/lh/LhView.fxml",800,400),
   END("../screens/end/Endview.fxml",800,400),
   GAME_DOUBLE("../screens/split/SplitView.fxml",1200,400);

   private String path = null;
   private int width;
   private int height;


   SceneSwitcher(final String path,final int width,final int height) {
      this.path = path;
      this.width = width;
      this.height = height;
   }

   public void load(){
      Main.stage.setScene(loadSceneFromFXML(path,width, height));
      Main.stage.show();
      Main.centerFrame();
   }

   private Scene loadSceneFromFXML(String scenepath, int sceneWidth, int sceneHeight) {

      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenepath));
      Parent root = null;try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return new Scene(root, sceneWidth, sceneHeight);
   }

}

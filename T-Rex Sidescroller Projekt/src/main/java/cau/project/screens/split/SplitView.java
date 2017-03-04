package main.java.cau.project.screens.split;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.View;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;

/**
 * Created by ricoklimpel on 02.03.17.
 */
public class SplitView extends View{

   @FXML
   SubScene sub1;
   @FXML
   SubScene sub2;


   public SplitView() {

      setViewID(R.viewIdSplit);
      Main.setMainView(this);

      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            sub1.setRoot(SceneSwitcher.GAME_DESKTOP.getRoot());
            sub2.setRoot(SceneSwitcher.GAME_LH.getRoot());
         }
      });

   }

   private Parent loadSceneFromFXML(String scenepath, int sceneWidth, int sceneHeight) {

      //Load fxml configuration for the GameScreen and set it as Parent
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenepath));
      Parent root = null;
      try {
         root = fxmlLoader.load();
      } catch (IOException e) {
         e.printStackTrace();
      }

      System.out.println(root);

      return root;
   }

   public void setSub1(Parent root) {
      this.sub1.setRoot(root);
   }

   public void setSub2(Parent root) {
      this.sub2.setRoot(root);
   }
}

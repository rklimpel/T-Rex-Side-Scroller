package main.java.cau.project.screens.split;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import main.java.cau.project.View;

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

      Platform.runLater(new Runnable() {
         @Override
         public void run() {
            sub1.setRoot(loadSceneFromFXML("../game/view/desktop/DesktopView.fxml",(int)
                    sub1.getWidth(),(int)sub1.getHeight()));
            sub2.setRoot(loadSceneFromFXML("../game/view/desktop/DesktopView.fxml",
                    (int)sub1.getWidth(),(int)sub1.getHeight()));
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
}

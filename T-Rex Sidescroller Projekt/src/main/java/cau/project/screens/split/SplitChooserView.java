package main.java.cau.project.screens.split;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import main.java.cau.project.*;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;


public class SplitChooserView extends View{

   @FXML
   GridPane  background;
   @FXML
   ComboBox comboBox;

   private View view;

   int sub;


   public SplitChooserView() {

      view = this;
      setViewID(R.viewIdSplitChooser);

      Platform.runLater(new Runnable() {
         @Override
         public void run() {


            background.setStyle("-fx-background-color:  #FFFFFF;");

            comboBox.setValue("Choose View");

            comboBox.valueProperty().addListener(new ChangeListener() {
               @Override
               public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                  SplitView splitView = (SplitView)Main.getMainView();
                  if(sub==1){
                     splitView.setSub1(SceneSwitcher.GAME_DESKTOP.getRoot());
                  }else if(sub == 2){
                       splitView.setSub2(SceneSwitcher.GAME_DESKTOP.getRoot());
                  }
               }
            });

         }
      });

   }

   public void init(int i){
      this.sub = i;
   }
}

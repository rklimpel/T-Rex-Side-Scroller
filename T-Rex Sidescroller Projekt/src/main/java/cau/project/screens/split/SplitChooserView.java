package main.java.cau.project.screens.split;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import main.java.cau.project.*;
import main.java.cau.project.services.SceneSwitcher;
import main.java.cau.project.services.listeners.KeyboardListener;

import java.io.IOException;


public class SplitChooserView extends View {


    @FXML
    GridPane background;
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

                new KeyboardListener(view);

                background.setStyle("-fx-background-color:  #FFFFFF;");

                comboBox.setValue("Choose View");

                comboBox.valueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                        System.out.println(R.lighthouseSelected);

                        SplitView splitView = (SplitView) Main.getMainView();

                        Parent root = null;
                        if (!R.lighthouseSelected) {
                            if (newValue.equals("Lighthouse")) {
                                R.lighthouseSelected = true;
                                System.out.println("if false schleife: " + R.lighthouseSelected);

                                root = SceneSwitcher.GAME_LH.getRoot();
                                System.out.println("if false schleife2: " + R.lighthouseSelected);

                            } else if (newValue.equals("Desktop Shapes")) {
                                root = SceneSwitcher.GAME_DESKTOP.getRoot(false);
                            } else if (newValue.equals("Desktop Images")) {
                                root = SceneSwitcher.GAME_DESKTOP.getRoot(true);
                            }
                        } else if (R.lighthouseSelected) {
                            System.out.println("if true schleife: " + R.lighthouseSelected);
                            R.lighthouseSelected = false;
                            if (newValue.equals("Desktop Shapes")) {
                                root = SceneSwitcher.GAME_DESKTOP.getRoot(false);
                            } else if (newValue.equals("Desktop Images")) {
                                root = SceneSwitcher.GAME_DESKTOP.getRoot(true);
                            }else  if (newValue.equals("Lighthouse")){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Waring!");
                                alert.setHeaderText(null);
                                alert.setContentText("Just open one Lighthouse-Game in the SplitView.");
                                alert.showAndWait();
                            }
                        }

                        if (sub == 1 && root!= null) {
                            splitView.setSub1(root);
                        } else if (sub == 2 && root != null) {
                            splitView.setSub2(root);
                        }

                    }
                });

            }
        });

    }

    public void init(int i) {
        this.sub = i;
    }
}

package main.java.cau.project.menu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.cau.project.LighthouseNetwork;
import main.java.cau.project.game.view_desktop.GameView;

import java.io.IOException;


public class MenuController {

    @FXML public Button btn_startDesktop;
    @FXML public Button btn_startLighthouse;

    private Boolean lighthouseConnected = false;

    public MenuController() {

        try {
            new LighthouseNetwork().connect();
            lighthouseConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
            lighthouseConnected = false;
        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(lighthouseConnected){
                    btn_startLighthouse.setStyle("-fx-background-color:  #4caf50;");
                }else{
                    btn_startLighthouse.setStyle("-fx-background-color:  #f44336;");
                }
            }
        });

    }

    public void onClick_btn_startDeskop() throws IOException {
        startDesktop();
    }

    public void startDesktop () throws IOException{
        new GameView();
    }

    public void KeyEventHandler(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            try {
                startDesktop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package main.java.cau.project.menu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.cau.project.Helper;
import main.java.cau.project.LighthouseNetwork;
import main.java.cau.project.game.view_desktop.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MenuController {

    @FXML public Button btn_startDesktop;
    @FXML public Button btn_startLighthouse;

    private Boolean lighthouseConnected = false;

    LighthouseNetwork lighthouseNetwork = new LighthouseNetwork();

    public MenuController() {

        try {
            lighthouseNetwork.connect();
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

    public void onClick_btn_startLighthouse(){
       /* Timer timer = new Timer();
        int begin = 1000; //timer starts after 1 second.
        int timeinterval = 1000; //timer executes every 10 seconds.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random rn = new Random();
                byte[] bytes = new byte[1176];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte)rn.nextInt();
                }

                try {
                    lighthouseNetwork.send(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },begin, timeinterval);*/

        try {
            lighthouseNetwork.send(Helper.convertLighthouseImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

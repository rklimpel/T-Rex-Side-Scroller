package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;


public class MenuController {

    public Button btn_startDesktop;

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

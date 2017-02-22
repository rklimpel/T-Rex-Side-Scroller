package programming.projekt.trex;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class Controller{

    public Button btn_startDesktop;

    public void onClick_btn_startDeskop(){

        if(btn_startDesktop.getText().equals("hallo")){
            btn_startDesktop.setText("Start Game Deksop");
        }else{
            btn_startDesktop.setText("hallo");
        }

    }

}

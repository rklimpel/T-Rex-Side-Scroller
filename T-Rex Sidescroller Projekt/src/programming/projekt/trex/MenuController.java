package programming.projekt.trex;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;


public class MenuController {

    public Button btn_startDesktop;

    public void onClick_btn_startDeskop() throws IOException {

        startDesktop();

    }

    public void startDesktop () throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Main.stage.setTitle("T-Rex Sidescroller Game");
        Main.stage.setScene(new Scene(root, 1080,720));
        Main.stage.setFullScreen(true);
        Main.stage.show();
    }

}

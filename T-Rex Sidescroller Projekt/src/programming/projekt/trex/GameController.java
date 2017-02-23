package programming.projekt.trex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameController {

    @FXML
    Button btn_backToMenu;

    public void OnClick_btn_backToMenu() throws IOException {

        startMenu();

    }

    public void startMenu () throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuView.fxml"));
        Main.stage.setTitle("T-Rex Sidescroller Game");
        Main.stage.setScene(new Scene(root, Main.MenuWidth,Main.MenuHeight));
        Main.stage.setFullScreen(false);
        Main.stage.show();
    }

}

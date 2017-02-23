package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.io.IOException;

/**
 * Constructor creates the Menu View and displays it to the user
 */
public class MenuView {

    int MenuWidth = 300;
    int MenuHeight = 275;

    GameController gameController = new GameController();

    public MenuView() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuView.fxml"));
        Main.stage.setTitle("T-Rex Sidescroller Game");
        Main.stage.setScene(new Scene(root, MenuWidth, MenuHeight));
        Main.stage.show();

    }
}

package programming.projekt.trex.menu;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import programming.projekt.trex.Main;

import java.awt.*;
import java.io.IOException;

/**
 * Constructor creates the Menu View and displays it to the user
 */
public class MenuView {

    int MenuWidth = 300;
    int MenuHeight = 275;

    MenuController menuController = new MenuController();

    public MenuView() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
        Parent root = fxmlLoader.load();
        menuController = fxmlLoader.getController();
        Scene scene = new Scene(root, MenuWidth, MenuHeight);
        Main.stage.setScene(scene);
        Main.stage.setTitle("T-Rex Sidescroller Game");

        Main.stage.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                menuController.KeyEventHandler(event);
            }
        });

        centerFrame();
        Main.stage.show();

    }

    private void centerFrame() {
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Move the window to the Center of Desktop
        Main.stage.setX(dim.width / 2 - MenuWidth /2);
        Main.stage.setY(dim.height / 2 - MenuHeight /2);
    }
}

package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;


public class GameView {

    int GameWidth = 1080;
    int GameHeight = 530;

    GameController gameController;

    public GameView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Parent root = fxmlLoader.load();
        gameController = fxmlLoader.getController();
        gameController.setView(this);
        Scene scene = new Scene(root, GameWidth, GameHeight);
        Main.stage.setScene(scene);
        centerFrame();


        //Main.stage.setFullScreen(true);
        Main.stage.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                gameController.KeyEventHandler(event);
            }
        });

        Main.stage.show();

    }

    private void centerFrame() {
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Move the window to the Center of Desktop
        Main.stage.setX(dim.width / 2 - GameWidth /2);
        Main.stage.setY(dim.height / 2 - GameHeight /2);
    }
}

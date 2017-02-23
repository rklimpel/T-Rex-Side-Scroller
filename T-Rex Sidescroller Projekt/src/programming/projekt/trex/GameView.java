package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


public class GameView {

    int GameWidth = 1080;
    int GameHeight = 920;

    GameController gameController = new GameController();

    public GameView() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        fxmlLoader.setController(gameController);

        Parent root = fxmlLoader.load();

        Main.stage.setTitle("T-Rex Sidescroller Game");
        Main.stage.setScene(new Scene(root, GameWidth, GameHeight));
        Main.stage.setFullScreen(true);

        Main.stage.getScene().setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                gameController.KeyEventHandler(event);
            }
        });

        Main.stage.show();

    }
}

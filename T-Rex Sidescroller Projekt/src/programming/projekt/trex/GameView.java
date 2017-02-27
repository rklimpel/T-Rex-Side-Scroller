package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;


public class GameView {

    //Game Scene Size
    int GameWidth = 1080;
    int GameHeight = 530;

    //the belonging game controller
    GameController gameController;

    /**
     * Game View constructor automatically starts switches Menu Scene to Game Scene and shows it to the user
     *
     * @throws IOException
     */
    public GameView() throws IOException {

        //Load fxml configuration for the GameScreen and set it as Parent
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameView.fxml"));
        Parent root = fxmlLoader.load();

        //extract Controller from fxml configuration
        gameController = fxmlLoader.getController();

        //Notify Game Controller about his caller View
        gameController.setView(this);

        //Create Game Scene and put it in the stage
        Scene scene = new Scene(root, GameWidth, GameHeight);
        Main.stage.setScene(scene);

        centerFrame();

        //Set Application to Fullscreen
        //Main.stage.setFullScreen(true);

        //Set Key Listener to the whole Scene and pass it to Game Controllers KeyEventHandler Method
        Main.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameController.KeyEventHandler(event);
            }
        });

        Main.stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gameController.KeyReleasedHandler(event);
            }
        });
        Main.stage.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameController.MouseEventHandler(event);
            }
        });
       Main.stage.getScene().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gameController.MouseClickReleased(event);
            }
        });

        //Show GameScene to USer
        Main.stage.show();
    }

    /**
     * Centers the Window (Stage) on the users Desktop
     */
    private void centerFrame() {
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Move the window to the Center of Desktop
        Main.stage.setX(dim.width / 2 - GameWidth /2);
        Main.stage.setY(dim.height / 2 - GameHeight /2);
    }
}

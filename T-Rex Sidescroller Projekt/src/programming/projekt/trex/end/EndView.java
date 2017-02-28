package programming.projekt.trex.end;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import programming.projekt.trex.Main;

import java.awt.*;
import java.io.IOException;

/**
 * Created by janek on 25.02.2017.
 */
public class EndView {

    int EndWidth=1080;
    int EndHeigth=530;

    EndController endController;

    private void centerFrame() {
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Move the window to the Center of Desktop
        Main.stage.setX(dim.width / 2 - EndWidth/2);
        Main.stage.setY(dim.height / 2 - EndHeigth /2);
    }

    public EndView(int score)throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EndView.fxml"));
        Parent root = fxmlLoader.load();
        endController = fxmlLoader.getController();

        endController.setView(this);
        endController.setScore(score);

        Scene scene = new Scene(root, EndWidth, EndHeigth);
        Main.stage.setScene(scene);
        //centerFrame();
        Main.stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                try {
                    endController.keyEventHandler(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Main.stage.show();

    }

}

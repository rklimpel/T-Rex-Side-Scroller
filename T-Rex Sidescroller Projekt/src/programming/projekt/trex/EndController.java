package programming.projekt.trex;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;

/**
 * Created by janek on 25.02.2017.
 */
public class EndController {
    int score = 13;

    @FXML
    Label lbl_endscore;

    public EndController(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lbl_endscore.setText("Endscore: " + score);
            }
        });

    }

    public void startNewGame() throws IOException {
        new GameView();
    }

    public void keyEventHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
            try {
                startNewGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    startMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startMenu() throws IOException {
        new MenuView();
    }
}
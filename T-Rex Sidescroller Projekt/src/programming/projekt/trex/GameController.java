package programming.projekt.trex;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import programming.projekt.trex.GameModel.*;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameController{

    @FXML
    Button btn_backToMenu;

    private GameModel gameModel;
    private Scene gameScene;

    public GameController(){
        this.gameModel = new GameModel();
    }

    /**
     * Leave Game, quit every running things and go back to Menu
     *
     * @throws IOException
     */
    public void OnClick_btn_backToMenu() throws IOException {
        startMenu();
    }

    public void startMenu () throws IOException {

        //Stop Game Timer
        gameModel.gameTimer.cancel();

        //Start Menu Scene
        new MenuView();
    }

    /**
     * This Methods gets and handles the Key event from GameView
     * @param event
     */
    public void KeyEventHandler(KeyEvent event){
        if(event.getCode() == KeyCode.SPACE){
            System.out.println("JUMP ");
            gameModel.startGameTimer();
        }
    }


}

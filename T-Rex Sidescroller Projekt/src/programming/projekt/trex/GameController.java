package programming.projekt.trex;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameController {

    @FXML
    Button btn_backToMenu;
    @FXML
    Pane pane;

    Rectangle rectangle;


    private GameModel gameModel;
    private GameView gameView;

    public GameController() {
        this.gameModel = new GameModel(this);
    }

    /**
     * Leave Game, quit every running things and go back to Menu
     *
     * @throws IOException
     */
    public void OnClick_btn_backToMenu() throws IOException {
        startMenu();
    }

    public void startMenu() throws IOException {
        //Stop Game Timer
        gameModel.stopGameTimer();
        //Start Menu Scene
        new MenuView();
    }

    /**
     * This Methods gets and handles the Key event from GameView
     *
     * @param event
     */
    public void KeyEventHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            if (!gameModel.gameTimerEnabled) {

                gameModel.createPlayer();
                gameModel.createObstacle();

                rectangle = new Rectangle(gameModel.obstacle.x, gameModel.obstacle.y,
                        gameModel.obstacle.width, gameModel.obstacle.height);
                pane.getChildren().addAll(rectangle);

                gameModel.startGameTimer();

            } else {
                System.out.println("JUMP ");
            }
        }else if(event.getCode() == KeyCode.ESCAPE){
            try {
                startMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Is called on GameModel Data changes
     * e.g. in gametick method
     */
    public void Update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                rectangle.setX(gameModel.obstacle.x);
            }
        });
    }


    public void setView(GameView gameView) {
        this.gameView = gameView;
    }

    public int getPaneHeight(){
        if(pane!=null){
            return (int) pane.getHeight();
        }
        return 0;
    }

    public int getPaneWidth(){
        if(pane!=null){
            return (int)pane.getWidth();
        }
        return 0;
    }
}

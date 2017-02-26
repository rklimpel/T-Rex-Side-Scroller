package programming.projekt.trex;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

import java.io.*;


/**
 * Created by ricoklimpel on 23.02.17.
 */
public class GameController {

    //objects from FXML configuration
    @FXML
    Button btn_backToMenu;
    @FXML
    Pane pane;
    @FXML
    BorderPane background;

    Rectangle rectangle;

    //Remember related mvc classes
    private GameModel gameModel;
    private GameView gameView;

    Font customScoreFont;

    public GameController() {
        this.gameModel = new GameModel(this);
        loadCustomFont();
    }

    /**
     * Leave Game, quit every running things and go back to Menu
     *
     * @throws IOException
     */
    public void OnClick_btn_backToMenu() throws IOException {
        startMenu();
    }

    /**
     * Go Back the menu (exit Game)
     *
     * switches to Menu scene and stops the game Timer
     *
     * @throws IOException
     */
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

        //On Space Pressed
        if (event.getCode() == KeyCode.SPACE) {
            if (!gameModel.gameTimerEnabled && !gameModel.gameOver) {

                gameModel.createPlayer();

                gameModel.startGameTimer();

            }else if (!gameModel.gameTimerEnabled && gameModel.gameOver){

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            new EndView(gameModel.score);
                        }catch (IOException e){
                            System.out.println("somehting failed...");
                        }

                    }
                });

            }else{
                System.out.println("JUMP ");
                gameModel.jump();
            }

        //On Escape Pressed:
        }else if(event.getCode() == KeyCode.ESCAPE){
            try {
                startMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //create Obstacles with o
        /*else if (event.getCode() == KeyCode.O){
            gameModel.createObstacle();
        }*/
    }

    /**
     * Is called on GameModel Data changes
     * e.g. in gametick method
     */
    public void Update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                pane.getChildren().clear();

                //Add Obstacles to Pane
                for (int i = 0; i < gameModel.obstacles.size(); i++) {

                    rectangle = new Rectangle(
                            gameModel.obstacles.get(i).x,
                            gameModel.obstacles.get(i).y,
                            gameModel.obstacles.get(i).width,
                            gameModel.obstacles.get(i).height);

                    pane.getChildren().addAll(rectangle);
                }

                //Add Player to Pane
                rectangle = new Rectangle(
                        gameModel.player.getX(),
                        gameModel.player.getY(),
                        gameModel.player.getWidth(),
                        gameModel.player.getHeight());

                rectangle.setRotate(gameModel.player.rotation);
                rectangle.setFill(gameModel.player.getColor());
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(2);
                rectangle.setStrokeLineCap(StrokeLineCap.ROUND);

                pane.getChildren().add(rectangle);

                //Add Score Label to Pane
                Label label = new Label();
                label.setFont(new Font("Arial",50));
                label.setText("Score: " + gameModel.score);
                label.setFont(customScoreFont);

                FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
                float labelWidth = fontLoader.computeStringWidth(label.getText(), label.getFont());

                label.setLayoutX((pane.getWidth()/2)-labelWidth/2);
                label.setLayoutY(pane.getHeight()/5);

                pane.getChildren().add(label);

            }
        });
    }

    /**
     * Tells the GameController about his relatted gameView.
     * Could'nt be set in constructor because constructor is called by fxml configuration
     *
     * @param gameView
     */
    public void setView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * getter for Pane Size (called by Model)
     *
     * @return
     */
    public int getPaneHeight(){
        if(pane!=null){
            return (int) pane.getHeight();
        }
        return 0;
    }

    /**
     * getter for Pane Size (called by Model)
     *
     * @return
     */
    public int getPaneWidth(){
        if(pane!=null){
            return (int)pane.getWidth();
        }
        return 0;
    }

    public void loadCustomFont(){
        customScoreFont = Helper.loadFont();
    }
}

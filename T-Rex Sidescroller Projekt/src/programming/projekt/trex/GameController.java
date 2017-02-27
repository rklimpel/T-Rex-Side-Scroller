package programming.projekt.trex;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

import java.io.*;

import static com.apple.eio.FileManager.getResource;


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

    ImageView imageView_player = new ImageView();
    ImageView imageView_obstacle;

    Image imagePlayer = null;
    Image imagePlayerCrouched = null;
    Image imageObstacle = null;

    //Remember related mvc classes
    private GameModel gameModel;
    private GameView gameView;

    Boolean gameObjectsAsImages = R.gameobjectsAsImages;

    Font customScoreFont;

    public GameController() {
        this.gameModel = new GameModel(this);
        loadCustomFont();
        loadImages();
    }

    private void loadImages() {

        try{
            imagePlayer = new Image(Main.class.getResourceAsStream("./assets/player_mexiko.png"));
            imagePlayerCrouched = new Image(Main.class.getResourceAsStream("./assets/playerCrouched_mexiko.png"));
            imageObstacle = new Image(Main.class.getResourceAsStream("./assets/obstacle_mexiko.png"));
        }catch(Exception e){
            System.out.println(e);
        }


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
            //IF Game is not over but it is not Running start the game
            if (!gameModel.gameTimerEnabled && !gameModel.gameOver) {

                gameModel.createPlayer();
                gameModel.startGameTimer();

            }
            //IF the Game is over (collision) space switches to menu
            else if (!gameModel.gameTimerEnabled && gameModel.gameOver){

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

            }
            //else space is there to jump
            else{
                gameModel.jump();
            }

        //On Escape Pressed:
        }else if(event.getCode() == KeyCode.ESCAPE){
            try {
                startMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(event.getCode() == KeyCode.CONTROL){
            if(event.getEventType() == KeyEvent.KEY_PRESSED){
                gameModel.player.crouch();
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

                if(gameObjectsAsImages){

                    addImageObastaclesToPane();

                    addImagePlayerToPane();

                }else{
                    //Add Obstacles to Pane
                    addObstaclesToPane();

                    //Add Player to Pane
                    addPlayerToPane();
                }

                //Add Score Label to Pane
                addScoreLabelToPane();

            }
        });
    }

    private void addImageObastaclesToPane() {

        for (int i = 0; i < gameModel.obstacles.size(); i++) {

            imageView_obstacle = new ImageView();
            imageView_obstacle.setImage(imageObstacle);
            imageView_obstacle.relocate(gameModel.obstacles.get(i).getX(),
                    gameModel.obstacles.get(i).getY());
            imageView_obstacle.setFitHeight(gameModel.obstacles.get(i).getHeight());
            imageView_obstacle.setFitWidth(gameModel.obstacles.get(i).getWidth());

            pane.getChildren().addAll(imageView_obstacle);
        }

    }

    private void addImagePlayerToPane(){

        if(gameModel.player.isCrouching){
            imageView_player.setImage(imagePlayerCrouched);
        }else{
            imageView_player.setImage(imagePlayer);
        }
        imageView_player.setFitWidth(gameModel.player.getWidth());
        imageView_player.setFitHeight(gameModel.player.getHeight());
        imageView_player.relocate(gameModel.player.getX(),gameModel.player.getY());

        pane.getChildren().addAll(imageView_player);


    }

    private void addObstaclesToPane() {
        for (int i = 0; i < gameModel.obstacles.size(); i++) {

            rectangle = new Rectangle(
                    gameModel.obstacles.get(i).x,
                    gameModel.obstacles.get(i).y,
                    gameModel.obstacles.get(i).width,
                    gameModel.obstacles.get(i).height);

            pane.getChildren().addAll(rectangle);
        }
    }

    private void addScoreLabelToPane() {
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

    private void addPlayerToPane() {
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
        customScoreFont = Helper.loadFont(200);
    }

    public void KeyReleasedHandler(KeyEvent event) {
        if(event.getCode() == KeyCode.CONTROL){
            gameModel.player.crouchEnd();
        }
    }
}

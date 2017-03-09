package main.java.cau.project.screens.end;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.java.cau.project.*;
import main.java.cau.project.services.loader.CustomFontLoader;
import main.java.cau.project.services.Helper;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;

/**
 * class who creates the layout of the end view and handels its actions
 */
public class EndView extends View {

    int score;
    //loads the fxml-data
    @FXML
    Label lbl_endscore;
    @FXML
    Label lbl_gameover;
    @FXML
    GridPane background;
    @FXML
    Label lbl_restart;
    @FXML
    Button btn_restart;
    //custom font is created
    CustomFontLoader customFontLoader = new CustomFontLoader();

    public Boolean continueAllowed = true;

    /**
     * creates the endview, shows the score and some hints and differs between desktop
     * and the lighthouse version for showing the endscreen
     */
    public EndView() {

        view = this;
        setViewID(R.viewIdEnd);
        //score is stored in the helper-class
        score = Helper.score;
        //performs the actions on the ui-thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                new KeyboardListener(view);
                //adding the hints, score and button
                setScoreLabel();
                setGameoverLabel();
                setRestartButton();

                background.setStyle("-fx-background-color: #000000;");
            }
        });
        //if the lighthousegame is running show the special lighthouse endscreen
        if (Main.getMainView().getViewID() == R.viewIdGameLighthouse) {
            continueAllowed = false;
            Main.getLighthouseService().lighthouseEnd(this);
        }

    }

    /**
     * setting the layout of the restart-button
     */
    private void setRestartButton() {
        btn_restart.setStyle("-fx-text-fill: grey; -fx-base: black;");
    }

    /**
     * setting the layout for the gameover-label
     */
    private void setGameoverLabel() {
        lbl_gameover.setFont(customFontLoader.load(R.fontPixel, 100));
        lbl_gameover.setTextFill(Color.WHITE);
    }

    /**
     * setting layout for the score label
     */
    private void setScoreLabel() {
        lbl_endscore.setText("Endscore: " + score);
        lbl_endscore.setFont(customFontLoader.load(R.fontPixel, 100));
        lbl_endscore.setTextFill(Color.WHITE);
    }

    /**
     * loads the new game view, there the view is switched in the scene-switcher-
     * class and the gamedeasktop-view is loaded, the new game starts
     * @throws IOException
     */
    public void onButtonClicked() throws IOException {
        restartGame();
    }

    /**
     * first is looked which kind of game is running, therefor the current viewID is
     * compared with the three existing ones. For each one the next gamestarting view is
     * loaded by the sceneswitcher-class
     */
    public void restartGame() {
        if(continueAllowed){
            if (Main.getMainView().getViewID() == R.viewIdSplit) {
                SceneSwitcher.GAME_SPLIT.load();
            } else if (Main.getMainView().getViewID() == R.viewIdGameDesktop) {
                SceneSwitcher.GAME_DESKTOP.load();
            } else if (Main.getMainView().getViewID() == R.viewIdGameLighthouse) {
                SceneSwitcher.GAME_LH.load();
            }
        }
    }

    /**
     * when exit is called your are switched to the menu-view
     * @param nextViewID viewID for the next shown view
     */
    public void exit(String nextViewID) {
        SceneSwitcher.MENU.load();
    }


}
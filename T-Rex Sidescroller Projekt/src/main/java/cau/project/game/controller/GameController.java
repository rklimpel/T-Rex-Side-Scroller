package main.java.cau.project.game.controller;

import main.java.cau.project.Helper;
import main.java.cau.project.Main;
import main.java.cau.project.View;
import main.java.cau.project.game.model.GameModel;

import java.util.ArrayList;

public class GameController {

    private ArrayList<View> listeningViews = new ArrayList<>();

    public GameModel gameModel;

    public GameController(View newListener) {
        this.listeningViews.add(newListener);
        this.gameModel = new GameModel(this,800,400);
    }

    public GameController(View newListener,int paneWidth, int paneHeight){
        this.listeningViews.add(newListener);
        this.gameModel = new GameModel(this,paneWidth,paneHeight);
    }

    public void update() {
        for (int i = 0; i < listeningViews.size(); i++) {
            listeningViews.get(i).Update();
        }
    }

    public void jump(View view) {

        //IF Game is not over but it is not Running start the game
        if (!gameModel.gameTimerEnabled && !gameModel.gameOver) {
            gameModel.createPlayer();
            gameModel.startGameTimer();
        }

        //IF the Game is over (collision) space switches to menu
        else if (!gameModel.gameTimerEnabled && gameModel.gameOver) {
            quitGame(view);
        }

        //else space is there to jump
        else if (gameModel.gameTimerEnabled) {
            gameModel.jump();
        }
    }

    public void crouch() {
        gameModel.player.crouch();
    }

    public void crouchEnd() {
        gameModel.player.crouchEnd();
    }

    public void quitGame(View view) {
        listeningViews.remove(view);
        if (listeningViews.size() == 0) {
            gameModel.stopGameTimer();
            Main.gameController = null;
        }
        Helper.score = gameModel.getScore();
        view.exit();
    }
}
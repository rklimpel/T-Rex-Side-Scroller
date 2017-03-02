package main.java.cau.project.screens.game.view.lh;


import javafx.scene.paint.Color;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.screens.game.view.GameView;
import main.java.cau.project.services.SceneSwitcher;

public class LhView extends GameView{

    private Color[][] pixelsUnscaled;
    private Color[][] pixelsReduced;

    private GameController controller;

    public LhView() {

        super.setController();

        pixelsUnscaled = new Color[controller.getModelPaneHeight()][controller.getModelPaneWidth()];
        pixelsReduced = new Color[R.lighthouseHeight][R.lighthouseWidth];
    }

    @Override
    public void Update() {

    }

    @Override
    public void exit(int i) {
        if (i == 0) {
            SceneSwitcher.END.load();
        } else if (i == -1) {
            SceneSwitcher.MENU.load();
        }
    }
}

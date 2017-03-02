package main.java.cau.project.screens.game.view.lh;


import javafx.application.Platform;
import javafx.scene.paint.Color;
import main.java.cau.project.Helper;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.screens.game.view.GameView;
import main.java.cau.project.services.KeyboardListener;
import main.java.cau.project.services.LighthouseNetwork;
import main.java.cau.project.services.MouseListener;
import main.java.cau.project.services.SceneSwitcher;

import java.io.IOException;

public class LhView extends GameView{

    private Color[][] pixelsUnscaled;
    private Color[][] pixelsReduced;

    private LighthouseNetwork lighthouseNetwork = new LighthouseNetwork();

    public LhView() {



        view = this;
        view.setViewID(R.viewIdGameDesktop);

        super.setController();

        pixelsUnscaled = new Color[controller.getModelPaneHeight()][controller.getModelPaneWidth()];
        pixelsReduced = new Color[R.lighthouseHeight][R.lighthouseWidth];

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new KeyboardListener(view);
                new MouseListener(view);
            }
        });

        try {
            lighthouseNetwork.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Update() {
        System.out.println("Lighthouse Update");
        try {
            lighthouseNetwork.send(Helper.oneLighthouseWindow(2,8,Color.RED));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

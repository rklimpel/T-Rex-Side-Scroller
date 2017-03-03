package main.java.cau.project.screens.game.view.lh;


import javafx.application.Platform;
import javafx.scene.paint.Color;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.view.GameView;
import main.java.cau.project.services.Helper;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.LighthouseNetwork;
import main.java.cau.project.services.listeners.MouseListener;

import java.io.IOException;

public class LhView extends GameView{

    private Color[][] pixelsReduced;

    private double scaleFactorWidth;
    private double scaleFactorHeight;

    private LhConverter lhConverter;

    private LighthouseNetwork lighthouseNetwork = new LighthouseNetwork();

    public LhView() {

        view = this;
        view.setViewID(R.viewIdGameLighthouse);

        Main.setMainView(this);

        super.setController();

        pixelsReduced = new Color[R.lighthouseHeight][R.lighthouseWidth];

        scaleFactorWidth = controller.getModelPaneWidth()/R.lighthouseWidth;
        scaleFactorHeight = controller.getModelPaneHeight()/R.lighthouseHeight;

        System.out.println("scaleFactorWidth: "+scaleFactorWidth);
        System.out.println("scaleFactorHeihgt: "+scaleFactorHeight);

        lhConverter = new LhConverter(scaleFactorWidth,scaleFactorHeight);

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

        super.calcAndShowFPS();

        try {
            if(pixelsReduced!=null
                    && controller.gameModel.player != null
                    && controller.gameModel.getObstacles() != null){

                lighthouseNetwork.send(Helper.convertToByteArray(
                        lhConverter.loadGamemodelData(controller.gameModel, pixelsReduced)));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

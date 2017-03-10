package main.java.cau.project.screens.game.view.lh;


import javafx.application.Platform;
import javafx.scene.paint.Color;
import main.java.cau.project.Main;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.model.GameModel;
import main.java.cau.project.screens.game.view.GameView;
import main.java.cau.project.services.LighthouseService;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.listeners.MouseListener;

import java.util.ArrayList;

public class LhView extends GameView{

    private Color[][] pixelsReduced;

    private double scaleFactorWidth;
    private double scaleFactorHeight;

    private LighthouseService lighthouseService;

    private ArrayList<Color[][]> lastPixels = new ArrayList<>();

    private Color colorLighthousePlayer = Color.DEEPSKYBLUE;
    private Color colorLigthhouseObstacles = Color.RED;
    private Color colorLigthhousePlatform = Color.GREEN;
    private Color colorLighthouseBackground = Color.BLACK;
    private Color colorLighthousePowerup =Color.YELLOW;

    public LhView() {

        view = this;
        view.setViewID(R.viewIdGameLighthouse);

        if(Main.getMainView() == null || Main.getMainView().getViewID()!=R.viewIdSplit){
            Main.setMainView(this);
        }

        super.setController();

        pixelsReduced = new Color[R.lighthouseHeight][R.lighthouseWidth];

        scaleFactorWidth = controller.getModelPaneWidth()/(R.lighthouseWidth);
        scaleFactorHeight = controller.getModelPaneHeight()/(R.lighthouseHeight-R.lighthouseBottomOffset);

        System.out.println("scaleFactorWidth: "+scaleFactorWidth);
        System.out.println("scaleFactorHeihgt: "+scaleFactorHeight);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new KeyboardListener(view);
                new MouseListener(view);
            }
        });

        lighthouseService = Main.getLighthouseService();
    }

    @Override
    public void Update() {

        super.calcAndShowFPS();

        if(pixelsReduced!=null
                && controller.gameModel.player != null
                && controller.gameModel.getObstacles() != null){

            lighthouseService.sendPixelsToLighthouse(loadGamemodelData(controller.gameModel, pixelsReduced));

        }

    }

    public Color[][] loadGamemodelData(GameModel gameModel, Color[][] pixelsReduced){

        //Make All Pixels Black
        for (int i = 0; i < pixelsReduced.length; i++) {

            for (int j = 0; j < pixelsReduced[0].length; j++) {

                pixelsReduced[i][j] = colorLighthouseBackground;

            }
        }

        //Paint Ground pixels
        for (int i = scaleY(gameModel.getGround().getY());
             i < scaleY(gameModel.getGround().getY())+1; i++) {

            for (int j = scaleX(gameModel.getGround().getX());
                 j < scaleX(gameModel.getGround().getX()+gameModel.getGround().getWidth()); j++) {

                try {
                    pixelsReduced[i][j] = colorLigthhousePlatform;
                }catch (ArrayIndexOutOfBoundsException e){
                    //Ground out of Lighthouse Bounds...
                }
            }
        }

        //Paint Platforms
        for (int i = 0; i < controller.getPlatforms().size(); i++) {

            for (int j = scaleY(gameModel.getPlatforms().get(i).getY());
                 j < scaleY(gameModel.getPlatforms().get(i).getY())+1; j++) {

                for (int k = scaleX(gameModel.getPlatforms().get(i).getX());
                     k < scaleX(gameModel.getPlatforms().get(i).getX()
                     + gameModel.getPlatforms().get(i).getWidth()); k++) {

                    try{
                        pixelsReduced[j][k] = colorLigthhousePlatform;
                    }catch (ArrayIndexOutOfBoundsException e){
                        //Remember this... the Array is out of bounds
                    }


                }

            }
        }

        //Paint obstacle Pixels
        for (int i = 0; i < gameModel.getObstacles().size(); i++) {

            for (int j = scaleY(gameModel.getObstacles().get(i).getY());
                 j < scaleY(gameModel.getObstacles().get(i).getY()
                         +gameModel.getObstacles().get(i).getHeight()); j++) {

                for (int k = scaleX(gameModel.getObstacles().get(i).getX());
                     k < scaleX(gameModel.getObstacles().get(i).getX()
                             +gameModel.getObstacles().get(i).getWidth()); k++) {

                    if(!(j>pixelsReduced.length||k>=pixelsReduced[0].length||j<0||k<0)){
                        pixelsReduced[j][k]=colorLigthhouseObstacles;
                    }
                }
            }
        }

        //Paint powerup Pixels
        for (int i = 0; i < gameModel.getPowerups().size(); i++) {

            for (int j = scaleY(gameModel.getPowerups().get(i).getY());
                 j < scaleY(gameModel.getPowerups().get(i).getY()
                 + gameModel.getPowerups().get(i).getHeight()); j++) {

                for (int k = scaleX(gameModel.getPowerups().get(i).getX());
                     k < scaleX(gameModel.getPowerups().get(i).getX()
                             + gameModel.getPowerups().get(i).getWidth()); k++) {

                    if(!(j>pixelsReduced.length||k>=pixelsReduced[0].length||j<0||k<0)){
                        pixelsReduced[j][k]=colorLighthousePowerup;
                    }

                }

            }
        }

        //Paint PlayerPixels Red
        for (int i = scaleY(gameModel.player.getY());
             i < scaleY(gameModel.player.getY()+gameModel.player.getHeight()); i++) {

            for (int j = scaleX(gameModel.player.getX());
                 j < scaleX(gameModel.player.getX()+gameModel.player.getWidth()); j++) {

                //System.out.println("PlayerPixels: " + i + " / " + j)
                try{
                    pixelsReduced[i][j] = colorLighthousePlayer;
                }catch(Exception e){

                }
            }
        }


        //Add Smothness to Ligthhouse by looking up last ligthhouse values, decrease opacity and add color to lighthouse
        //if lighthouse would be black

        Color[][] color = new Color[R.lighthouseHeight][R.lighthouseWidth];
        for (int i = 0; i < pixelsReduced.length; i++) {
            for (int j = 0; j < pixelsReduced[0].length; j++) {
                color[i][j] = pixelsReduced[i][j];
            }
        }

        lastPixels.add(color);

        double opacity = 1;

        for (int i = lastPixels.size()-2; i > 0; i--) {
            for (int j = 0; j < pixelsReduced.length; j++) {
                for (int k = 0; k < pixelsReduced[0].length; k++) {
                    if(pixelsReduced[j][k]==colorLighthouseBackground
                            && lastPixels.get(i)[j][k]!=colorLighthouseBackground){

                        pixelsReduced[j][k] = new Color((lastPixels.get(i)[j][k].getRed()*opacity),
                                (lastPixels.get(i)[j][k].getGreen()*opacity),
                                (lastPixels.get(i)[j][k].getBlue()*opacity),
                                1.0);

                    }

                }

            }

            if(opacity>0.6){
                opacity-=0.15;
            }else{
                opacity-=0.05;
            }

            if(opacity<=0){
                opacity=0;
            }

        }

        if(lastPixels.size()>=20){
            lastPixels.remove(0);
        }

        //Here ends the smoothness thing

        return pixelsReduced;
    }



    private int scaleX(int value){
        int scaledValue = (int)Math.round(value/scaleFactorWidth);
        if(scaledValue>=R.lighthouseWidth){
            scaledValue=R.lighthouseWidth;
        }
        return scaledValue;
    }

    private int scaleY(int value){
        return (int)Math.round(value/scaleFactorHeight);
    }
}

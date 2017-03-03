package main.java.cau.project.screens.game.view.lh;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.cau.project.R;
import main.java.cau.project.screens.game.model.GameModel;

/**
 * Created by Rico on 02.03.2017.
 */
public class LhConverter {

   private double scaleFactorWidth;
   private double scaleFactorHeight;

   public LhConverter(double scaleFactorWidth, double scaleFactorHeight) {
      this.scaleFactorWidth = scaleFactorWidth;
      this.scaleFactorHeight = scaleFactorHeight;
   }

   public Color[][] loadGamemodelData(GameModel gameModel, Color[][] pixelsReduced){

      //Make All Pixels Black
      for (int i = 0; i < pixelsReduced.length; i++) {

         for (int j = 0; j < pixelsReduced[0].length; j++) {

            pixelsReduced[i][j] = Color.BLACK;

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
                  pixelsReduced[j][k]=Color.BLUE;
               }
            }
         }
      }

      //Paint PlayerPixels White
      for (int i = scaleY(gameModel.player.getY());
           i < scaleY(gameModel.player.getY()+gameModel.player.getHeight()); i++) {

         for (int j = scaleX(gameModel.player.getX());
              j < scaleX(gameModel.player.getX()+gameModel.player.getWidth()); j++) {

            //System.out.println("PlayerPixels: " + i + " / " + j);

            pixelsReduced[i][j] = Color.RED;
         }
      }

      return pixelsReduced;
   }

   private int scaleX(int value){
      return (int)Math.round(value/scaleFactorWidth);
   }

   private int scaleY(int value){
      return (int)Math.round(value/scaleFactorHeight);
   }




}

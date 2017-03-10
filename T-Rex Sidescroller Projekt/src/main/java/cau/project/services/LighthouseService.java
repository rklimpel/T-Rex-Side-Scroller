package main.java.cau.project.services;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.cau.project.R;
import main.java.cau.project.screens.end.EndView;
import main.java.cau.project.services.loader.ImageLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Manages to Connection between the Game and the Lighthouse Network class
 */
public class LighthouseService {

   LighthouseNetwork lighthouseNetwork;
   Color[][] lastPixels;
   Random rand = new Random();
   ImageLoader imageLoader = new ImageLoader();

   /**
    * creates a new Lighthouse Network Instance with Real Ligthhouse or Mock Lighthouse Data
    */
   public LighthouseService() {
      if(R.realLighthouse){
         lighthouseNetwork = new LighthouseNetwork(R.username,R.password);
      }else {
         lighthouseNetwork = new LighthouseNetwork();
      }
   }

   /**
    * trys to connect to the lighthouse
    *
    * @return connection established = true else false
    */
   public Boolean connect(){
      try {
         lighthouseNetwork.connect();
      } catch (IOException e) {
         return false;
      }
      return true;
   }


   /**
    * Send a Color Array to the Ligthouse
    *
    * @param pixels
    * @return
    */
   public Boolean sendPixelsToLighthouse(Color[][] pixels){

      lastPixels = pixels;

      try {
         lighthouseNetwork.send(convertToByteArray(pixels));
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }
      return true;

   }


   /**
    * Lighthouse End is Displays the Lighthouse specific Gameover Screen on the Lighthouse
    */
   int x = 0;
   int y = 0;
   int img = 1;

   public void lighthouseEnd(EndView endview){

      try {
         lighthouseNetwork.connect();
      } catch (IOException e) {
         e.printStackTrace();
      }

      Color[][] color = lastPixels;

      Timer timer = new Timer();
      TimerTask task = new TimerTask() {
         public void run() {

            /*color[y][x] = Color.rgb(Helper.randInt(0,255),Helper.randInt(0,255),Helper.randInt(0,255));

            x+=1;

            if(x == R.lighthouseWidth){
               y+=1;
               x=0;
            }

            try {
               lighthouseNetwork.send(convertToByteArray(color));
            } catch (IOException e) {
               e.printStackTrace();
            }


            System.out.println("x: " + x);
            System.out.println("y: "+y);

            if(y == R.lighthouseHeight && x == 0){
               System.out.println("Lighthouse Endview Done");
               endview.continueAllowed = true;
               timer.purge();
            }*/

            String stylePackage = "mexiko";

            URL urlWalking1 = this.getClass().getResource(
                    "/main/res/assets/"+stylePackage+"/smileygrin.jpg");
            URL urlWalking2 = this.getClass().getResource(
                    "/main/res/assets/"+stylePackage+"/smileysmile.jpg");
            URL urlWalking3 = this.getClass().getResource(
                    "/main/res/assets/"+stylePackage+"/smileytoungue.jpg");

            Image image = null;
            try {

               switch (img){
                  case 1:
                     image = new Image(urlWalking1.openStream(),
                             R.lighthouseWidth, R.lighthouseHeight, false, false);
                     break;
                  case 2:
                     image = new Image(urlWalking2.openStream(),
                             R.lighthouseWidth, R.lighthouseHeight, false, false);
                     break;
                  case 3:
                     image = new Image(urlWalking3.openStream(),
                             R.lighthouseWidth, R.lighthouseHeight, false, false);
                     break;
               }

            } catch (IOException e) {
               e.printStackTrace();
            }

            try {
               lighthouseNetwork.send(convertLighthouseImage(image));
            } catch (IOException e) {
               e.printStackTrace();
            }

            img +=1;
            if(img>3){

               endview.continueAllowed=true;
               img = 3;
               timer.cancel();
               timer.purge();
            }
         }
      };
      timer.scheduleAtFixedRate(task, 200, 400);

      x = 0;
      y = 0;

   }


   /**
    *
    * Does what the Method names says. Converts a Color Array to a Byte Array.
    * Thats what the Lighthouse needs.
    *
    * @param pixelColors
    * @return
    */
   public byte[] convertToByteArray(Color[][] pixelColors) {

      byte[] returnBytes = new byte[1176];

      int color = 0;
      int positionY = 0;
      int positionX = 0;

      for (int i = 0; i < returnBytes.length; i++) {

         if (color == 0) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getRed() * 127));
         } else if (color == 1) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getGreen() * 127));
         } else if (color == 2) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getBlue() * 127));
         }

         color += 1;

         if (color == 3) {

            color = 0;

            positionX += 1;
            if (positionX >= R.lighthouseWidth) {
               positionX = 0;
               positionY += 1;
            }
         }

         returnBytes[i] *= 2;
      }

      return returnBytes;
   }


   /**
    * Converts an Image to a byte array so we can Display it on the Lighthouse
    *
    * @param image
    * @return
    */
   public static byte[] convertLighthouseImage(Image image) {

      Color[][] pixelColors = new Color[R.lighthouseHeight][R.lighthouseWidth];

      for (int i = 0; i < pixelColors.length; i++) {
         for (int j = 0; j < pixelColors[0].length; j++) {
            try{
               pixelColors[i][j] = image.getPixelReader().getColor(j, i);
            }catch (Exception e){
               System.out.println(e);
            }

         }
      }

      byte[] returnBytes = new byte[1176];

      int color = 0;
      int positionY = 0;
      int positionX = 0;

      for (int i = 0; i < returnBytes.length; i++) {

         if (color == 0) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getRed() * 127));
         } else if (color == 1) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getGreen() * 127));
         } else if (color == 2) {
            returnBytes[i] = (byte) ((int) (pixelColors[positionY][positionX].getBlue() * 127));
         }

         color += 1;

         if (color == 3) {

            color = 0;

            positionX += 1;
            if (positionX >= R.lighthouseWidth) {
               positionX = 0;
               positionY += 1;
            }
         }

         returnBytes[i] *= 2;
      }

      return returnBytes;
   }

}
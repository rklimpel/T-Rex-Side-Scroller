package main.java.cau.project;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.*;

public class Helper {

   public static int score;

   public static String readFile(String path) throws IOException {

      String returnString = null;

      File file = new File(path);

      FileReader reader = null;
      try {
         reader = new FileReader(file);
         char[] chars = new char[(int) file.length()];
         reader.read(chars);
         returnString = new String(chars);
         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (reader != null) {
            reader.close();
         }
      }

      return returnString;
   }


   public static String extractDigits(String src) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < src.length(); i++) {
         char c = src.charAt(i);
         if (Character.isDigit(c)) {
            builder.append(c);
         }
      }
      return builder.toString();
   }

   /**
    * method to color one pixel on the lighthouse
    *
    * @param x     row on the lighthouse
    * @param y     collum on the lighthouse
    * @param color color of the pixel
    * @return
    */
   public static byte[] oneLighthouseWindow(int x, int y, Color color) {
      byte[] data = new byte[1176];

      double redvalue = color.getRed() * 127;
      double greenvalue = color.getGreen() * 127;
      double bluevalue = color.getBlue() * 127;

      int row = (y - 1) * 84;
      int sum = row + 3 * (x - 1);

      data[sum] = (byte) redvalue;
      data[sum + 1] = (byte) greenvalue;
      data[sum + 2] = (byte) bluevalue;
      for (int i = 0; i < data.length; i++) {
         data[i] *= 2;
      }
      System.out.println("the first spot is: " + sum);
      System.out.println("red: " + data[sum]);
      System.out.println("green: " + data[sum + 1]);
      System.out.println("blue: " + data[sum + 2]);

      return data;
   }


   public static byte[] convertLighthouseImage() {

      Image image = new Image("file:src/main/res/assets/mexiko/player_mexiko.png",
              R.lighthouseWidth, R.lighthouseHeight, false, true);

      Color[][] pixelColors = new Color[R.lighthouseHeight][R.lighthouseWidth];

      //System.out.println(pixelColors[0].length);
      //System.out.println(pixelColors.length);

      System.out.println(Color.RED);

      for (int i = 0; i < pixelColors.length; i++) {
         for (int j = 0; j < pixelColors[0].length; j++) {
            pixelColors[i][j] = image.getPixelReader().getColor(j, i);
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

         System.out.println((int) returnBytes[i]);

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


   public static void centerFrame() {

      // Get the size of the screen
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      // Move the window to the Center of Desktop
      Main.stage.setX(dim.width / 2 - Main.stage.getScene().getWidth() / 2);
      Main.stage.setY(dim.height / 2 - Main.stage.getScene().getHeight() / 2);

   }

}

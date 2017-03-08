package main.java.cau.project.services;

import javafx.scene.paint.Color;

import java.io.*;

public class Helper {

   public static int score;

   public String readFile(String path) throws IOException {
      InputStream inputStream = this.getClass().getResourceAsStream(path);
      return getStringFromInputStream(inputStream);
   }

   // convert InputStream to String
   private static String getStringFromInputStream(InputStream is) {

      BufferedReader br = null;
      StringBuilder sb = new StringBuilder();

      String line;
      try {

         br = new BufferedReader(new InputStreamReader(is));
         while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
         }

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (br != null) {
            try {
               br.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }

      return sb.toString();

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


}

package main.java.cau.project;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.io.*;


/**
 * Created by ricoklimpel on 26.02.17.
 */
public class Helper {

    public static Font loadFont(int size) {


        Font returnFont = null;
        try {
            returnFont = Font.loadFont(new FileInputStream(
                    new File("src/main/res/fonts/game_over.ttf")), size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return returnFont;
    }

    public static String readFile(String path) throws IOException {

        String returnString = null;

        File file = new File(path);
        //System.out.println(file.toString());

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
     * @param x row on the lighthouse
     * @param y collum on the lighthouse
     * @param color color of the pixel
     * @return
     */
    public static byte[] oneLighthouseWindow(int x, int y, Color color) {
        byte[] data = new byte[1176];

        double redvalue =  color.getRed()*127;
        double greenvalue =  color.getGreen()*127;
        double bluevalue =  color.getBlue()*127;

        int row = (y - 1) * 84;
        int sum = row + 3 * (x - 1);

        data[sum] = (byte)redvalue;
        data[sum + 1] = (byte)greenvalue;
        data[sum + 2] = (byte)bluevalue;
        for(int i=0; i<data.length;i++){
            data[i]*=2;
        }
        System.out.println("the first spot is: " +sum);
        System.out.println("red: " +data[sum]);
        System.out.println("green: " +data[sum+1]);
        System.out.println("blue: " +data[sum+2]);

        return data;
    }


}

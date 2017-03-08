package main.java.cau.project.services;

import javafx.scene.paint.Color;

import java.io.*;

public class Helper {

    public static int score;

    /**
     * return string vom file in filesystem
     *
     * @param path
     * @return
     * @throws IOException
     */
    public String readFile(String path) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        return getStringFromInputStream(inputStream);
    }

    /**
     * Helper for readfile method, converts inputstream into a string
     *
     * @param is
     * @return
     */
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

    /**
     * @param src
     * @return
     */
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
        //calculates the values for the lighthouse (convertation)
        double redvalue = color.getRed() * 127;
        double greenvalue = color.getGreen() * 127;
        double bluevalue = color.getBlue() * 127;

        //calculation of the positon in the Array of the lighthouse
        //for collumpositon has every row on the lighthouse 84 windows
        int row = (y - 1) * 84;
        int sum = row + 3 * (x - 1);

        //setting the specific values for the RGB into the fields of the array
        //first for the red-value
        data[sum] = (byte) redvalue;
        //second for the green-value
        data[sum + 1] = (byte) greenvalue;
        //third for the blue-value
        data[sum + 2] = (byte) bluevalue;
        for (int i = 0; i < data.length; i++) {
            data[i] *= 2;
        }


        return data;
    }


}

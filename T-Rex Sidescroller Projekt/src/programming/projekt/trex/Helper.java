package programming.projekt.trex;

import javafx.scene.text.Font;
import sun.misc.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ricoklimpel on 26.02.17.
 */
public class Helper {

    public static Font loadFont(){

        Font returnFont = null;

        try{
            String currentFontFile = "./fonts/game_over.ttf";
            InputStream fontStream = GameController.class.getResourceAsStream(currentFontFile);
            if (fontStream != null) {
                returnFont = Font.loadFont(fontStream, 200);
                fontStream.close();


            } else {
                throw new IOException("Could not create font: " + currentFontFile);
            }
        }catch(Exception e){

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
            if(reader !=null){reader.close();}
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


}

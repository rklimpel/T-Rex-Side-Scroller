package programming.projekt.trex;

import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;

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
}

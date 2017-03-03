package main.java.cau.project.services.loader;

import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class CustomFontLoader {

   public static Font load( String fontID,int size) {

      Font returnFont = null;
      try {
         returnFont = Font.loadFont(new FileInputStream(
                 new File("src/main/res/fonts/"+fontID+".ttf")), size);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      return returnFont;
   }
}

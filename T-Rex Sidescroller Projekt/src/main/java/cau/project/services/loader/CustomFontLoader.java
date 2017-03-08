package main.java.cau.project.services.loader;

import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


public class CustomFontLoader {

   public Font load( String fontID,int size) {


      URL url = this.getClass().getResource("/main/res/fonts/"+fontID+".ttf");

      Font returnFont = null;

      try {
         returnFont = Font.loadFont(url.openStream(),size);
      } catch (IOException e) {
         e.printStackTrace();
      }

      return returnFont;
   }
}

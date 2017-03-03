package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

/**
 * Created by ricoklimpel on 03.03.17.
 */
public class Ground extends GameObject {

   public Ground(int paneWidth,int paneHeight) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;

      this.height = 2;
      this.width = paneWidth;
      this.x = 0;
      this.y = paneHeight;
   }
}

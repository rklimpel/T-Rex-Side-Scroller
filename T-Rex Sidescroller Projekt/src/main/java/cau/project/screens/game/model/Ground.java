package main.java.cau.project.screens.game.model;

/**
 *class for setting the ground
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

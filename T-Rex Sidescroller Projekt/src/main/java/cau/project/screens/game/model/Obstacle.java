package main.java.cau.project.screens.game.model;

import main.java.cau.project.R;

public class Obstacle extends GameObject {

   final int defaultHeight = R.obstacleHeight;
   final int defaultWidth = R.obstacleWidth;

   public Obstacle(int paneWidth, int paneHeight, int yOffset) {

      this.paneWidth = paneWidth;
      this.paneHeight = paneHeight;

      width = defaultWidth;
      height = defaultHeight;

      x = paneWidth;
      y = paneHeight - height - yOffset;

   }

   public Obstacle(int paneWidth, int paneHeight) {
      this(paneWidth, paneHeight, 0);
   }

   public Obstacle() {
      this(0, 0, 0);
   }

}

package main.java.cau.project.game.model;


import javafx.scene.paint.Color;
import main.java.cau.project.R;


public class GameObject {

   public int width;
   public int height;
   public int x;
   public int y;

   private int groundLvl = R.groundLvL;

   //yOffset for crouching player (else he would fly...)
   public int yOffset;

   public Color color;

   public int paneWidth;
   public int paneHeight;

   public Boolean checkCollision(GameObject clasher) {

      //Check Callers left side is inside clasher
      if (((this.getX() >= clasher.getX()
              && this.getX() <= clasher.getX() + clasher.getWidth())
              //Check Callers right side is inside clasher
              || (this.getX() + this.getWidth() >= clasher.getX()
              && this.getX() + this.getWidth() <= clasher.getX() + clasher.getWidth()))
              //Check Callers Bottom is inside clasher
              && ((this.getY() + this.getHeight() >= clasher.getY()
              && this.getY() + this.getHeight() <= clasher.getY() + clasher.getHeight())
              //Check Calles Top is inside clasher
              || (this.getY() >= clasher.getY()
              && this.getY() <= clasher.getY() + clasher.getHeight()))) {

            /*System.out.println("this x: " + this.getX());
            System.out.println("this x + width: " + (this.getX() + this.getWidth()));

            System.out.println("clasher x: " + clasher.getX());
            System.out.println("clasher x + width: " + (clasher.getX() + clasher.getWidth()));

            System.out.println();

            System.out.println("this y: " + this.getX());
            System.out.println("this y + heigth: " + (this.getY() + this.getHeight()));

            System.out.println("clasher y: " + clasher.getY());
            System.out.println("clasher y + height: " + (clasher.getY() + clasher.getHeight()));*/

         return true;
      }

      return false;
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public Color getColor() {
      return color;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      //Add Offset and Groundlvl variables to y value
      return y + yOffset - groundLvl;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }
}

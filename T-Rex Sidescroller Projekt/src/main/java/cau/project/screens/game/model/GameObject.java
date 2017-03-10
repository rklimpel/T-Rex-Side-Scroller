package main.java.cau.project.screens.game.model;


import javafx.scene.paint.Color;
import main.java.cau.project.R;

/**
 * superclass for every kind of gameobject(obstacle,platform,player,powerup and ground)
 * here the collision is checked and the movement to left is done
 */
public class GameObject {

   public int width;
   public int height;
   public int x;
   public int y;


   //players messurements
   int defaultHeight;
   int defaultWidth;


   //default y positon of the player (used just by player)
   int defaultY;

   public int groundLvl = R.groundLvL;

   public int platformOffset;

   //yOffset for crouching player (else he would fly...)
   public int yCrouchOffset;

   public Color color;

   public int paneWidth;
   public int paneHeight;

   /**
    * the collision engine of the game
    * @param clasher
    * @return
    */
   public Boolean checkCollision(GameObject clasher) {

      //Check Callers left side is inside clasher
      return (((this.getX() >= clasher.getX()
              && this.getX() <= clasher.getX() + clasher.getWidth())
              //Check Callers right side is inside clasher
              || (this.getX() + this.getWidth() >= clasher.getX()
              && this.getX() + this.getWidth() <= clasher.getX() + clasher.getWidth()))
              //Check Callers Bottom is inside clasher
              && ((this.getY() + this.getHeight() >= clasher.getY()
              && this.getY() + this.getHeight() <= clasher.getY() + clasher.getHeight())
              //Check Calles Top is inside clasher
              || (this.getY() >= clasher.getY()
              && this.getY() <= clasher.getY() + clasher.getHeight()))
              //Clahsers left side is inside caller
              || (((clasher.getX() >= this.getX()
              && clasher.getX() <= this.getX() + this.getWidth())
              //Check Clashers Right side is inside caller
              || (clasher.getX() + clasher.getWidth() >= this.getX()
              && clasher.getX() + clasher.getWidth() <= this.getX() + this.getWidth()))
              //Check Clashers Bottom is inside caller
              && ((clasher.getY() + clasher.getHeight() >= this.getY()
              && clasher.getY() + clasher.getHeight() <= this.getY() + this.getHeight())
              //Check Clashers Top is inside Caller
              || clasher.getY() >= this.getY()
              && clasher.getY() <= this.getY() + this.getHeight())));
   }

   /**
    * moving the obstacles to the left
    */
   public void moveLeft() {
      x -= 1;
   }

   public Boolean checkOutisde() {
      return (x + width) < 0;
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
      return y + yCrouchOffset - groundLvl;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getPlatformOffset() {
      return platformOffset;
   }

   public void setPlatformOffset(int platformOffset) {
      this.platformOffset = platformOffset;
   }
}

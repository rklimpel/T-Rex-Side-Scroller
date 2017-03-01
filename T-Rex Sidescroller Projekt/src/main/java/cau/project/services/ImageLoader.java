package main.java.cau.project.services;

import javafx.scene.image.Image;


public class ImageLoader {

   private Image img_player = null;
   private Image img_playerCrouched = null;
   private Image img_obstacle = null;

   String stylePackage = "mexiko";

   public void load() {

      try {
         img_player = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/player_mexiko.png");
         img_playerCrouched = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/playerCrouched_mexiko.png");
         img_obstacle = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/obstacle_mexiko.png");
      } catch (Exception e) {
         System.out.println(e);
      }
   }

   public Image getImg_player() {
      return img_player;
   }

   public Image getImg_playerCrouched() {
      return img_playerCrouched;
   }

   public Image getImg_obstacle() {
      return img_obstacle;
   }
}

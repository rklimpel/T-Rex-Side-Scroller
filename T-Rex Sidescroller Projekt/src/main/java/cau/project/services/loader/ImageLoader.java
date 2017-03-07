package main.java.cau.project.services.loader;

import javafx.scene.image.Image;


public class ImageLoader {

   private Image img_player = null;
   private Image img_playerCrouched = null;
   private Image img_obstacle = null;
   private Image img_powerupTaco = null;

   private Image img_walking1 = null;
   private Image img_walking2 = null;
   private Image img_walking3 = null;
   private Image img_walking4 = null;
   private Image img_jump = null;


   String stylePackage = "mexiko";

   public void load() {

      try {
         img_player = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/player_mexiko.png");
         img_playerCrouched = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/playerCrouched_mexiko.png");
         img_obstacle = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/obstacle_mexiko.png");
         img_powerupTaco = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/powerup_taco.png");

         img_walking1 = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/walking1.png");
         img_walking2 = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/walking2.png");
         img_walking3 = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/walking3.png");
         img_walking4 = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/walking4.png");
         img_jump = new Image(
                 "file:src/main/res/assets/"+stylePackage+"/jump.png");


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

   public Image getImg_powerupTaco() {
      return img_powerupTaco;
   }

   public Image getImg_walking1() {
      return img_walking1;
   }

   public Image getImg_walking2() {
      return img_walking2;
   }

   public Image getImg_walking3() {
      return img_walking3;
   }

   public Image getImg_walking4() {
      return img_walking4;
   }

   public Image getImg_jump() {
      return img_jump;
   }
}

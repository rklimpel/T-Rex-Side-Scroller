package main.java.cau.project.services.loader;

import javafx.scene.image.Image;

import java.net.URL;

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

   private Image img_cactus = null;

   private Image img_obstacleBottom = null;
   private Image img_obstacleAdler = null;
   private Image img_obstacleAdlerAM = null;
   private Image img_platform0 = null;


   String stylePackage = "mexiko";

   public void load() {

      try {

         URL urlPlayer = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/player_mexiko.png");
         URL urlPlayerCrouched = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/playerCrouched_mexiko.png");
         URL urlObstacle = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/obstacle_mexiko.png");
         URL urlPowerupTaco = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/powerup_taco.png");
         URL urlWalking1 = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/walking1.png");
         URL urlWalking2 = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/walking2.png");
         URL urlWalking3 = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/walking3.png");
         URL urlWalking4 = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/walking4.png");
         URL urlJump = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/jump.png");
         URL urlCactus = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/cactus.png");
         URL urlObstacleBot = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/stacheldraht.png");
         URL urlAdler = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/adler.png");
         URL urlAdlerAM = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/adlermurica.png");
         URL urlPlatform0 = this.getClass().getResource(
                 "/main/res/assets/"+stylePackage+"/platform0.png");

         img_player = new Image(urlPlayer.openStream());
         img_playerCrouched = new Image(urlPlayerCrouched.openStream());
         img_obstacle = new Image(urlObstacle.openStream());
         img_powerupTaco = new Image(urlPowerupTaco.openStream());
         img_walking1 = new Image(urlWalking1.openStream());
         img_walking2 = new Image(urlWalking2.openStream());
         img_walking3 = new Image(urlWalking3.openStream());
         img_walking4 = new Image(urlWalking4.openStream());
         img_jump = new Image(urlJump.openStream());
         img_cactus = new Image(urlCactus.openStream());
         img_obstacleBottom = new Image(urlObstacleBot.openStream());
         img_obstacleAdler = new Image(urlAdler.openStream());
         img_obstacleAdlerAM = new Image(urlAdlerAM.openStream());
         img_platform0 = new Image(urlPlatform0.openStream());


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

   public Image getImg_cactus() {
      return img_cactus;
   }

   public Image getImg_obstacleBottom() {
      return img_obstacleBottom;
   }

   public Image getImg_obstacleAdler() {
      return img_obstacleAdler;
   }

   public Image getImg_obstacleAdlerAM() {
      return img_obstacleAdlerAM;
   }

   public Image getImg_platform0() {
      return img_platform0;
   }
}

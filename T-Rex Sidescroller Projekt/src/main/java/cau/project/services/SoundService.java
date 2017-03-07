package main.java.cau.project.services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * Created by ricoklimpel on 04.03.17.
 */
public class SoundService {

   Media mediaJump;
   Media mediaCrouchDown;
   Media mediaCrouchUp;
   Media mediaGameOpenining;
   Media mediaGameLoop;
   Media mediaGameover;

   MediaPlayer playerJump;
   MediaPlayer playerCrouchDown;
   MediaPlayer playerCrouchUp;
   MediaPlayer playerGameOpening;
   MediaPlayer playerGameLoop;
   MediaPlayer playerGameover;


   public SoundService() {

      String bip = "src/main/res/sounds/jump.mp3";
      mediaJump = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/crouch_down.wav";
      mediaCrouchDown = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/crouch_up.wav";
      mediaCrouchUp = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/country_opening.mp3";
      mediaGameOpenining = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/country_loop.mp3";
      mediaGameLoop = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/gameover.wav";
      mediaGameover = new Media(new File(bip).toURI().toString());


      playerJump = new MediaPlayer(mediaJump);
      playerCrouchDown = new MediaPlayer(mediaCrouchDown);
      playerCrouchUp = new MediaPlayer(mediaCrouchUp);
      playerGameOpening = new MediaPlayer(mediaGameOpenining);
      playerGameLoop = new MediaPlayer(mediaGameLoop);
      playerGameover = new MediaPlayer(mediaGameover);

      playerJump.setVolume(1.0);
      playerCrouchDown.setVolume(0.2);
      playerCrouchUp.setVolume(0.2);
      playerGameLoop.setVolume(0.3);
      playerGameOpening.setVolume(0.3);
      playerGameover.setVolume(0.9);

   }

   public void playJump() {

      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            playerJump.stop();
            playerJump.seek(Duration.ZERO);
            playerJump.play();
         }
      });

      thread.start();

   }

   public void playCrouchDown() {

      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            playerCrouchDown.stop();
            playerCrouchDown.seek(Duration.ZERO);
            playerCrouchDown.play();
         }
      });

      thread.start();

   }

   public void playCrouchUp() {

      Thread threadCrouchUp = new Thread(new Runnable() {
         @Override
         public void run() {
            playerCrouchUp.stop();
            playerCrouchUp.seek(Duration.ZERO);
            playerCrouchUp.play();
         }
      });

      threadCrouchUp.start();
   }

   public void playGametrack() {

      Thread threadGameLoop = new Thread(new Runnable() {
         @Override
         public void run() {

            playerGameOpening.seek(Duration.ZERO);
            playerGameOpening.play();
            playerGameOpening.setOnEndOfMedia(new Runnable() {
               @Override
               public void run() {
                  playerGameLoop.seek(Duration.ZERO);
                  playerGameLoop.setCycleCount(MediaPlayer.INDEFINITE);
                  playerGameLoop.play();

               }
            });

         }
      });

      threadGameLoop.start();

   }

   public void stopGametrack() {
      playerGameOpening.stop();
      playerGameLoop.stop();
   }

   public void playGameover() {

      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            playerGameover.stop();
            playerGameover.seek(Duration.ZERO);
            playerGameover.play();
         }
      });

      thread.start();

   }

   public void stopGameover() {
      playerGameover.stop();
   }


}

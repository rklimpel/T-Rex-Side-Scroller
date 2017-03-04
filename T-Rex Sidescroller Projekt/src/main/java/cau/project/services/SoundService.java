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

   MediaPlayer playerJump;
   MediaPlayer playerCrouchDown;
   MediaPlayer playerCrouchUp;

   public SoundService() {

      String bip = "src/main/res/sounds/jump.mp3";
      mediaJump = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/crouch_down.wav";
      mediaCrouchDown = new Media(new File(bip).toURI().toString());

      bip = "src/main/res/sounds/crouch_up.wav";
      mediaCrouchUp = new Media(new File(bip).toURI().toString());

      playerJump = new MediaPlayer(mediaJump);
      playerCrouchDown = new MediaPlayer(mediaCrouchDown);
      playerCrouchUp = new MediaPlayer(mediaCrouchUp);

   }

   public void playJump(){
      playerJump.seek(Duration.ZERO);
      playerJump.play();
   }

   public void playCrouchDown(){
      playerCrouchDown.seek(Duration.ZERO);
     playerCrouchDown.play();
   }

   public void playCrouchUp(){
      playerCrouchUp.seek(Duration.ZERO);
      playerCrouchUp.play();
   }
}

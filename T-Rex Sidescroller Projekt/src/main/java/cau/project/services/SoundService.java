package main.java.cau.project.services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by ricoklimpel on 04.03.17.
 */
public class SoundService {

   Media mediaJump;

   public SoundService() {

      String bip = "src/main/res/sounds/jump.mp3";
      mediaJump = new Media(new File(bip).toURI().toString());

   }

   public void playJump(){
      MediaPlayer mediaPlayer = new MediaPlayer(mediaJump);
      mediaPlayer.play();
   }
}

package main.java.cau.project.game.view.desktop;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import main.java.cau.project.*;
import main.java.cau.project.game.controller.GameController;
import main.java.cau.project.services.*;

import java.io.*;


public class DesktopView extends View {

   //objects from FXML configuration
   @FXML
   Button btn_backToMenu;
   @FXML
   Pane pane;
   @FXML
   BorderPane background;

   private Label lbl_hints = new Label("Press Space to Jump \n Press Strg to crouch ");

   private Rectangle rectangle;

   private ImageView imageView_player = new ImageView();
   private ImageView imageView_obstacle;

   private ImageLoader imageLoader;
   private CustomFontLoader customFontLoader;

   private Font customFont;

   private GameController gameController;

   private Boolean gameObjectsAsImages = R.gameobjectsAsImages;

   private Boolean hintsShown = false;

   public DesktopView() {

      view = this;
      setViewID(R.viewIdGameDesktop);

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            if (Main.gameController == null) {
               Main.gameController = new GameController(view,(int)pane.getWidth(),(int)pane.getHeight());
               gameController = Main.gameController;
            }

            new KeyboardListener(view);
            new MouseListener(view);
         }
      });

      imageLoader = new ImageLoader();
      imageLoader.load();
      customFontLoader = new CustomFontLoader();
      customFont = customFontLoader.load(R.fontPixel, 160);

      //creates the hints at when is called and space isn't pressed yet
      showHints();
   }

   /**
    * Leave Game, quit every running things and go back to Menu
    *
    * @throws IOException
    */
   public void OnClick_btn_backToMenu() {
      gameController.quitGame(this);
   }

   /**
    * Is called on GameModel Data changes
    * e.g. in gametick method
    */
   public void Update() {

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            pane.getChildren().clear();

            if (gameObjectsAsImages) {

               drawImageObstacles();

               drawImagePlayer();

            } else {
               //Add Obstacles to Pane
               drawObstacles();

               //Add Player to Pane
               drawPlayer();
            }

            //Add Score Label to Pane
            drawScoreLabel();

            //Add One Big Ground
            drawGround();
         }
      });

   }

   private void drawGround() {

      Rectangle rectangle = new Rectangle();
      rectangle.relocate(0, pane.getHeight() - R.groundLvL);
      rectangle.setWidth(pane.getWidth());
      rectangle.setHeight(R.groundSize);

      pane.getChildren().addAll(rectangle);
   }

   private void drawImageObstacles() {


      for (int i = 0; i < gameController.gameModel.getObstacles().size(); i++) {

         imageView_obstacle = new ImageView();
         imageView_obstacle.setImage(imageLoader.getImg_obstacle());
         imageView_obstacle.relocate(gameController.gameModel.getObstacles().get(i).getX(),
                 gameController.gameModel.getObstacles().get(i).getY());
         imageView_obstacle.setFitHeight(gameController.gameModel.getObstacles().get(i).getHeight());
         imageView_obstacle.setFitWidth(gameController.gameModel.getObstacles().get(i).getWidth());

         pane.getChildren().addAll(imageView_obstacle);
      }


   }

   private void drawImagePlayer() {

      if (gameController.gameModel.player.getCrouching()) {
         imageView_player.setImage(imageLoader.getImg_playerCrouched());
      } else {
         imageView_player.setImage(imageLoader.getImg_player());
      }
      imageView_player.setFitWidth(gameController.gameModel.player.getWidth());
      imageView_player.setFitHeight(gameController.gameModel.player.getHeight());
      imageView_player.relocate(gameController.gameModel.player.getX(), gameController.gameModel.player.getY());

      pane.getChildren().addAll(imageView_player);

   }

   private void drawObstacles() {

      for (int i = 0; i < gameController.gameModel.getObstacles().size(); i++) {

         rectangle = new Rectangle(
                 gameController.gameModel.getObstacles().get(i).getX(),
                 gameController.gameModel.getObstacles().get(i).getY(),
                 gameController.gameModel.getObstacles().get(i).getWidth(),
                 gameController.gameModel.getObstacles().get(i).getHeight());

         pane.getChildren().addAll(rectangle);
      }
   }

   private void drawPlayer() {
      rectangle = new Rectangle(
              gameController.gameModel.player.getX(),
              gameController.gameModel.player.getY(),
              gameController.gameModel.player.getWidth(),
              gameController.gameModel.player.getHeight());

      rectangle.setRotate(gameController.gameModel.player.getRotation());
      rectangle.setFill(gameController.gameModel.player.getColor());
      rectangle.setStroke(Color.BLACK);
      rectangle.setStrokeWidth(2);
      rectangle.setStrokeLineCap(StrokeLineCap.ROUND);

      pane.getChildren().add(rectangle);

   }

   private void drawScoreLabel() {

      Label label = new Label();
      label.setText("Score: " + gameController.gameModel.getScore());
      label.setFont(customFont);

      FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
      float labelWidth = fontLoader.computeStringWidth(label.getText(), label.getFont());

      label.setLayoutX((pane.getWidth() / 2) - labelWidth / 2);
      label.setLayoutY(pane.getHeight() / 5);

      pane.getChildren().add(label);
   }

   public void exit() {
      if(gameController.gameModel.gameOver){
         SceneSwitcher.END.load();
      }else{
         SceneSwitcher.MENU.load();
      }
   }

   /**
    * shows hints and creates them
    */
   private void showHints() {

      hintsShown = true;

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            lbl_hints.setFont(customFontLoader.load(R.fontPixel,80));

            FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
            float btn_hintsWidth = fontLoader.computeStringWidth(lbl_hints.getText(), lbl_hints.getFont());

            lbl_hints.setLayoutX((pane.getWidth() / 2) - btn_hintsWidth / 2 / 2);
            lbl_hints.setLayoutY(pane.getHeight() / 5);

            pane.getChildren().addAll(lbl_hints);
         }
      });

   }

}
package main.java.cau.project.screens.game.view.desktop;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import main.java.cau.project.*;
import main.java.cau.project.screens.game.view.GameView;
import main.java.cau.project.services.listeners.KeyboardListener;
import main.java.cau.project.services.listeners.MouseListener;
import main.java.cau.project.services.loader.CustomFontLoader;
import main.java.cau.project.services.loader.ImageLoader;


public class DesktopView extends GameView {

   @FXML
   Pane pane;
   @FXML
   BorderPane background;

   private Label lbl_hints = new Label();

   private Rectangle rectangle;

   private ImageView iV_player = new ImageView();

   private ImageLoader imageLoader;
   private CustomFontLoader customFontLoader;

   private Font customFont;

   private Boolean gameObjectsAsImages = R.gameobjectsAsImages;

   private int paneWidth;
   private int paneHeight;

   /**
    * Deskotp View Constructor
    * <p>
    * set a view variable to use if "this" is not possible
    * sets the View ID
    * set Keyboard and Mouse Listeners
    * Load Images and Fonts
    * Show Hints
    */
   public DesktopView() {

      view = this;
      setViewID(R.viewIdGameDesktop);

      if(Main.getMainView() == null || Main.getMainView().getViewID()!=R.viewIdSplit){
         Main.setMainView(this);
      }

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            paneWidth = (int) pane.getWidth();
            paneHeight = (int) (pane.getHeight());

            DesktopView.super.setController(paneWidth, paneHeight);

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
    * Is called on GameModel Data changes
    * Creates a new "Frame" / Scene and creates all game components new
    */
   public void Update() {

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            pane.getChildren().clear();

            if (gameObjectsAsImages) {

               //Add Obstacles to Pane
               drawImageObstacles();

               //Add Player to Pane
               drawImagePlayer();

            } else {
               //Add Obstacles to Pane

               drawObstacles();

               //Add Player to Pane
               drawPlayer();
            }

            //Add One Big Ground
            drawGround();

            //Add Score Label to Pane
            drawScoreLabel();

            if (controller.gameModel.gameOver) {
               Main.stage.getScene().getRoot().setEffect(new GaussianBlur());
            }

            DesktopView.super.calcAndShowFPS();

         }
      });
   }

   /**
    * draw a single, not moving ground line over the whole pane width (at groundlvl)
    * add this ground rectangle to pane
    */
   private void drawGround() {

      Rectangle rectangle = new Rectangle();
      rectangle.relocate(controller.getGround().getX(), controller.getGround().getY());
      rectangle.setWidth(controller.getGround().getWidth());
      rectangle.setHeight(controller.getGround().getHeight());

      pane.getChildren().addAll(rectangle);
   }

   /**
    * create a new Image with obstacle values from gamecontroller -> gamemodell
    * add obstacle Image to pane
    * <p>
    * will be called for every obstacle in obstacle array list
    */
   private void drawImageObstacles() {

      for (int i = 0; i < controller.gameModel.getObstacles().size(); i++) {

         ImageView iv_Obstacle = new ImageView();
         iv_Obstacle.setImage(imageLoader.getImg_obstacle());
         iv_Obstacle.relocate(controller.getObstacles().get(i).getX(),
                 controller.getObstacles().get(i).getY());
         iv_Obstacle.setFitHeight(controller.getObstacles().get(i).getHeight());
         iv_Obstacle.setFitWidth(controller.getObstacles().get(i).getWidth());

         pane.getChildren().addAll(iv_Obstacle);
      }
   }

   /**
    * create a new rectangle with obstacle values from gamecontroller -> gamemodell
    * add obstacle rectangle to pane
    * <p>
    * will be called for every obstacle in obstacle array list
    */
   private void drawObstacles() {

      for (int i = 0; i < controller.gameModel.getObstacles().size(); i++) {

         rectangle = new Rectangle(
                 controller.getObstacles().get(i).getX(),
                 controller.getObstacles().get(i).getY(),
                 controller.getObstacles().get(i).getWidth(),
                 controller.getObstacles().get(i).getHeight());

         pane.getChildren().addAll(rectangle);
      }
   }

   /**
    * create a new Image with the players values from gamecontroller -> gamemodel
    * add player Image to pane
    */
   private void drawImagePlayer() {

      if (controller.getPlayer().getCrouching()) {
         iV_player.setImage(imageLoader.getImg_playerCrouched());
      } else {
         iV_player.setImage(imageLoader.getImg_player());
      }
      iV_player.setFitWidth(controller.getPlayer().getWidth());
      iV_player.setFitHeight(controller.getPlayer().getHeight());
      iV_player.relocate(controller.getPlayer().getX(), controller.getPlayer().getY());

      pane.getChildren().addAll(iV_player);

   }

   /**
    * create a new rectangle with the players values from gamecontroller -> gamemodel
    * add player rectangle to pane
    */
   private void drawPlayer() {

      rectangle = new Rectangle(
              controller.getPlayer().getX(),
              controller.getPlayer().getY(),
              controller.getPlayer().getWidth(),
              controller.getPlayer().getHeight());

      rectangle.setRotate(controller.getPlayer().getRotation());
      rectangle.setFill(controller.getPlayer().getColor());
      rectangle.setStroke(Color.BLACK);
      rectangle.setStrokeWidth(2);
      rectangle.setStrokeLineCap(StrokeLineCap.ROUND);

      pane.getChildren().add(rectangle);

   }

   /**
    * Create a new Label, set Style and Position on Pane
    * get Score from GameController -> gamemodel and wirte score inside it
    * add this score label to pane
    */
   private void drawScoreLabel() {

      Label label = new Label();
      label.setText("Score: " + controller.getScore());
      label.setFont(customFont);

      FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
      float labelWidth = fontLoader.computeStringWidth(label.getText(), label.getFont());

      label.setLayoutX((pane.getWidth() / 2) - labelWidth / 2);
      label.setLayoutY(pane.getHeight() / 5);

      pane.getChildren().add(label);
   }

   /**
    * Show Hints how to move the player with the Keyboard to the User
    * <p>
    * will automatically be destroyed on first update
    */
   private void showHints() {

      Platform.runLater(new Runnable() {
         @Override
         public void run() {

            lbl_hints.setText("Press Space to Jump \n Press Strg to crouch ");
            lbl_hints.setFont(customFontLoader.load(R.fontPixel, 80));

            FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
            float btn_hintsWidth = fontLoader.computeStringWidth(lbl_hints.getText(), lbl_hints.getFont());

            lbl_hints.setLayoutX((pane.getWidth() / 2) - btn_hintsWidth / 2 / 2);
            lbl_hints.setLayoutY(pane.getHeight() / 5);

            pane.getChildren().addAll(lbl_hints);
         }
      });
   }
}
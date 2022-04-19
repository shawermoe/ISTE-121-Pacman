import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.animation.*;
import java.io.*;
import java.util.*;

/**
 * PacmanGEOStarter with JavaFX and Thread
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class GameStarter extends Application implements EventHandler<KeyEvent> {
   // Window attributes
   private Stage stage;
   private Scene scene;
   private VBox root;

   private static String[] args;

   private final static String ICON_IMAGE = "pacman.png"; // file with icon for a racer

   private int iconWidth; // width (in pixels) of the icon
   private int iconHeight; // height (in pixels) or the icon
   private PacmanRacer racer = null; // array of racers
   private Image carImage = null;

   private AnimationTimer timer; // timer to control animation

   // main program
   public static void main(String[] _args) {
      args = _args;
      launch(args);
   }

   // start() method, called via launch
   public void start(Stage _stage) {
      // stage seteup
      stage = _stage;
      stage.setTitle("Game2D Starter");
      stage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
               public void handle(WindowEvent evt) {
                  System.exit(0);
               }
            });

      // root pane
      root = new VBox();

      // create an array of Racers (Panes) and start
      initializeScene();
   }

   // start the race
   public void initializeScene() {

      // Make an icon image to find its size
      try {
         carImage = new Image(new FileInputStream(ICON_IMAGE));
      } catch (Exception e) {
         System.out.println("Exception: " + e);
         System.exit(1);
      }

      // Get image size
      iconWidth = (int) carImage.getWidth();
      iconHeight = (int) carImage.getHeight();

      racer = new PacmanRacer();
      root.getChildren().add(racer);
      root.setId("pane");

      // display the window
      scene = new Scene(root, 800, 500);
      scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
      stage.setScene(scene);
      stage.show();

      scene.setOnKeyPressed(this);

      System.out.println("Starting race...");

      // Use an animation to update the screen
      timer = new AnimationTimer() {
         public void handle(long now) {
            racer.update();
            // System.out.println("He");
         }
      };

      // TimerTask to delay start of race for 2 seconds
      TimerTask task = new TimerTask() {
         public void run() {
            timer.start();
         }
      };
      Timer startTimer = new Timer();
      long delay = 1000L;
      startTimer.schedule(task, delay);
   }

   /**
    * Racer creates the race lane (Pane) and the ability to
    * keep itself going (Runnable)
    */
   protected class PacmanRacer extends Pane {
      private int racePosX = 0; // x position of the racer
      private int racePosY = 0; // x position of the racer
      private int raceROT = 0; // x position of the racer
      private ImageView aPicView; // a view of the icon ... used to display and move the image

      public PacmanRacer() {
         // Draw the icon for the racer
         aPicView = new ImageView(carImage);
         this.getChildren().add(aPicView);
      }

      /**
       * update() method keeps the thread (racer) alive and moving.
       */
      public void update() {
         // racePosX += (int) (Math.random() * iconWidth / 30);
         // racePosY += (int) (Math.random() * iconWidth / 30);
         aPicView.setTranslateX(racePosX);
         aPicView.setTranslateY(racePosY);
         aPicView.setRotate(raceROT);

         // if (racePosX > 800)
         // racePosX = 0;
         // if (racePosY > 500)
         // racePosY = 0;
         // raceROT += 1;

      } // end update()

      public void goUp() {
         racer.racePosY -= 10;
         raceROT = -90;
      }

      public void goRight() {
         racer.racePosX += 10;

         raceROT = 0;

      }

      public void goDown() {
         racer.racePosY += 10;
         raceROT = 90;

      }

      public void goLeft() {
         racer.racePosX -= 10;
         raceROT = 180;

      }
   } // end inner class Racer

   @Override
   public void handle(KeyEvent ke) {
      switch (ke.getCode()) {
         case W:
         case UP:
            racer.goUp();
            break;

         case D:
         case RIGHT:
            racer.goRight();
            break;

         case S:
         case DOWN:
            racer.goDown();
            break;

         case A:
         case LEFT:
            racer.goLeft();
            break;

         default:
            break;
      }
   }
} // end class Races
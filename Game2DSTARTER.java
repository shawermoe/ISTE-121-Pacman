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

public class Game2DSTARTER extends Application implements EventHandler<KeyEvent> {
   // Window attributes
   private Stage stage;
   private Scene scene;
   private VBox root;

   private static String[] args;

   private final static String ICON_IMAGE = "pacman.png"; // file with icon for a racer

   // private int iconWidth; // width (in pixels) of the icon
   // private int iconHeight; // height (in pixels) or the icon
   private Pacman racer = null; // array of racers
   // private Image carImage = null;

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

      racer = new Pacman();
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

   @Override
   public void handle(KeyEvent ke) {
      switch (ke.getCode()) {
         case W:
         case UP:
            racer.setDirection(-90);
            break;

         case D:
         case RIGHT:
            racer.setDirection(0);
            break;

         case S:
         case DOWN:
            racer.setDirection(90);
            break;

         case A:
         case LEFT:
            racer.setDirection(180);
            break;

         default:
            break;
      }
   }

} // end class Races
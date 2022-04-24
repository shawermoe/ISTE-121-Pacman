
// All neccessary imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;

import javafx.application.Application;

import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

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
   private StackPane root;

   // Pacman attributes
   private Pacman pacman; // The pacman
   private AnimationTimer timer; // Timer to control animation
   private PixelReader pr; // PixelReader to implement collision

   // main program
   public static void main(String[] args) {
      launch(args);
   }

   // start() method, called via launch
   public void start(Stage stage) {
      // stage seteup
      this.stage = stage;
      stage.setTitle("Pacman - Kiara & Moe");
      stage.setOnCloseRequest(
            evt -> System.exit(0));

      // Stackpane pane
      root = new StackPane();

      // create an array of pacmans (Panes) and start
      initializeScene();
   }

   // start the race
   public void initializeScene() {

      // The Pacman object
      pacman = new Pacman();

      try {
         // Adding the background
         Image map = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/test.jpeg")));
         pr = map.getPixelReader();
         root.getChildren().add(new ImageView(map));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      // Adding the pacman
      root.getChildren().add(pacman);
      root.getChildren().addAll(new Ghost());
      // display the window
      Scene scene = new Scene(root, 800, 500);
      stage.setScene(scene);
      stage.show();

      // Adding keyboard functionality
      scene.setOnKeyPressed(this);

      // Used for testing purposes
      System.out.println("Starting race...");

      // Use an animation to update the screen
      timer = new AnimationTimer() {
         public void handle(long now) {
            if (!checkCollision())
               pacman.update();
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

   public boolean checkCollision() {
      Color check1 = pr.getColor((int) pacman.nextX(), (int) pacman.nextY());
      Color check2 = pr.getColor((int) (pacman.nextX() + pacman.getIcon().getWidth()), (int) pacman.nextY());
      Color check3 = pr.getColor((int) (pacman.nextX() + pacman.getIcon().getWidth()),
            (int) (pacman.nextY() + pacman.getIcon().getHeight()));
      Color check4 = pr.getColor((int) pacman.nextX(), (int) (pacman.nextY() + pacman.getIcon().getHeight()));

      return check1.getRed() > 0.9 || check2.getRed() > 0.9 || check3.getRed() > 0.9 || check4.getRed() > 0.9;
   }

   /*
    * Keyhandler which is used to grab events from the keyboard
    * The functionality behind it revolves rotating the pacman
    * With every key press. The controls involve W,A,S,D and
    * the arrow keys.
    */
   @Override
   public void handle(KeyEvent ke) {
      switch (ke.getCode()) {
         case W:
         case UP:
            pacman.setDirection(-90);
            break;

         case D:
         case RIGHT:
            pacman.setDirection(0);
            break;

         case S:
         case DOWN:
            pacman.setDirection(90);
            break;

         case A:
         case LEFT:
            pacman.setDirection(180);
            break;

         default:
            break;
      }
   }

} // end class Races
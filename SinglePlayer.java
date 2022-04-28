
// All neccessary imports
import javafx.animation.AnimationTimer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

/**
 * PacmanGEOStarter with JavaFX and Thread
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class SinglePlayer extends Application implements EventHandler<KeyEvent> {
   // Window attributes
   private Stage stage;
   private StackPane root;
   private int width = 495;
   private int height = 660;

   private List<ImageView> coins = new ArrayList<>();

   // Pacman attributes
   private Pacman pacman; // The pacman
   // private Ghost ghost; // The ghost

   // Arrays of ghost
   private Ghost[] ghosts = new Ghost[4];

   private AnimationTimer timer; // Timer to control animation
   private PixelReader pr; // PixelReader to implement collision

   // main program
   public static void main(String[] args) {
      launch(args);
   }

   // start() method, called via launch
   public void start(Stage stage) {
      // stage set up
      this.stage = stage;
      stage.setTitle("Pacman - Kiara & Moe");
      stage.setOnCloseRequest(
            evt -> System.exit(0));

      // Stackpane pane
      root = new StackPane();
      root.setAlignment(Pos.TOP_LEFT);

      // create an array of pacmans (Panes) and start
      initializeScene();
   }

   // start the race
   public void initializeScene() {

      // The Ghost object

      try {
         // Adding the background
         Image map = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/mapppp.jpg")));
         pr = map.getPixelReader();
         root.getChildren().add(new ImageView(map));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      // The Pacman object
      try {
         pacman = new Pacman(pr);
      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      }

      // Adding the pacman
      root.getChildren().add(pacman);

      try {
         for (int i = 0; i < ghosts.length; i++) {
            ghosts[i] = new Ghost(pr);
            root.getChildren().add(ghosts[i]);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      generateCoins(10);

      // display the window
      Scene scene = new Scene(root, width, height);
      stage.setScene(scene);
      stage.show();

      // Adding keyboard functionality
      scene.setOnKeyPressed(this);

      // Used for testing purposes
      System.out.println("Starting race...");

      // Use an animation to update the screen
      timer = new AnimationTimer() {
         public void handle(long now) {

            if (!pacman.checkWallCollision()) {
               pacman.update();
            }

            tst();

            for (Ghost g : ghosts) {
               if (!g.checkWallCollision()) {
                  g.update();
                  test(g);
               } else {
                  g.randomDirection();
               }
            }

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

   public void test(Ghost ghost) {
      if (ghost.getIconView().getBoundsInParent().intersects(pacman.getIconView().getBoundsInParent())) {
         pacman.die();
      }
   }

   public void tst() {

      for (int i = 0; i < coins.size(); i++) {
         if (coins.get(i).getBoundsInParent().intersects(pacman.getIconView().getBoundsInParent())) {

            root.getChildren().remove(coins.get(i));
         }
      }

   }

   public void generateCoins(int numberOfCoins) {
      for (int i = 0; i < numberOfCoins; i++) {
         root.getChildren().addAll(new Coin(pr));

      }
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
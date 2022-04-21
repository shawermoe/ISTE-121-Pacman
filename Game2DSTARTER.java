
// All neccessary imports
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
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
   private StackPane root;

   private Pacman racer = null; // array of racers

   private AnimationTimer timer; // timer to control animation
   private Image map;
   private PixelReader pr;

   // main program
   public static void main(String[] args) {
      launch(args);
   }

   // start() method, called via launch
   public void start(Stage stage) {
      // stage seteup
      this.stage = stage;
      stage.setTitle("Game2D Starter");
      stage.setOnCloseRequest(
            evt -> System.exit(0));

      // root pane
      root = new StackPane();

      // create an array of Racers (Panes) and start
      initializeScene();
   }

   // start the race
   public void initializeScene() {

      racer = new Pacman();
      try {
         map = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/test.jpeg")));
         pr = map.getPixelReader();
         root.getChildren().add(new ImageView(map));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      root.getChildren().add(racer);
      // display the window
      Scene scene = new Scene(root, 800, 500);

      stage.setScene(scene);
      stage.show();

      scene.setOnKeyPressed(this);

      System.out.println("Starting race...");

      // Use an animation to update the screen
      timer = new AnimationTimer() {
         public void handle(long now) {
            if (!checkCollision())
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

   public boolean checkCollision() {
      Color check1 = pr.getColor((int) racer.nextX(), (int) racer.nextY());
      Color check2 = pr.getColor((int) (racer.nextX() + racer.getaPic().getWidth()), (int) racer.nextY());
      Color check3 = pr.getColor((int) (racer.nextX() + racer.getaPic().getWidth()),
            (int) (racer.nextY() + racer.getaPic().getHeight()));
      Color check4 = pr.getColor((int) racer.nextX(), (int) (racer.nextY() + racer.getaPic().getHeight()));

      return check1.getRed() > 0.9 || check2.getRed() > 0.9 || check3.getRed() > 0.9 || check4.getRed() > 0.9;
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
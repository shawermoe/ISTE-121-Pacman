
// All neccessary imports
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.animation.*;
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
   private VBox root;

   private Pacman racer = null; // array of racers

   private AnimationTimer timer; // timer to control animation

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
      Scene scene = new Scene(root, 800, 500);
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
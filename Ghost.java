import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class Ghost extends Player {

    private int[] ghostDirections = { -90, 0, 90, 180 };

    // Attributes to set the initial position of the Pacman
    private static int initialX = 234;
    private static int initialY = 250;

    // Constructor for the Pacman character
    public Ghost(PixelReader pr) throws FileNotFoundException {
        super(initialX, initialY, new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/ghost1.gif"))), pr);
    }

    public void randomDirection() {
        int direction = new Random().nextInt(ghostDirections.length);
        this.setDirection(ghostDirections[direction]);
    }

}

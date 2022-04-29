
// All neccessary imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * Ghosts class which are controlled by the computer
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class Ghost extends Player {
    // Attributes for displaying the coins on the map
    private static Image icon;

    // Static block to catch the exception for reading the ghost image
    static {
        try {
            icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/ghost.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // An array of all possible directions which the ghost can rotate towards
    private int[] ghostDirections = { -90, 0, 90, 180 };

    // Attributes to set the initial position of the Pacman
    private static int initialX = 234;
    private static int initialY = 250;

    // Constructor for the Pacman character
    public Ghost(PixelReader pr) {
        super(initialX, initialY, icon, pr);
    }

    // moves the ghost in a random direction
    public void randomDirection() {
        int direction = new Random().nextInt(ghostDirections.length);
        this.setDirection(ghostDirections[direction]);
    }

}

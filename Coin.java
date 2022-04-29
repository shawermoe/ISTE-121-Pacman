
// All neccessary imports
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

/**
 * Coin class which generates coins on the map
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class Coin extends ImageView {
    // Attributes for displaying the coins on the map
    private static Image icon;

    // Pixel Reader was used to read the map and see if it's possible to place the
    // coins there
    private PixelReader pr;

    // Randomizer was used to place the coins randomly on the map
    private Random rand = new Random();

    // Coordinates for the coins
    private int x;
    private int y;

    // Static block to catch the exception for reading the coins image
    static {
        try {
            icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/coin.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Constructor takes in the pixel reader which reads the pixels from the map
    public Coin(PixelReader pr) {
        // displays the icon
        super(icon);

        // Map reader
        this.pr = pr;

        // Formula to check whether a dot can spawn there
        while (pr.getColor(x, y).getRed() > 0.2
                || pr.getColor(x + (int) icon.getWidth(), y).getRed() > 0.2
                || pr.getColor(x, y + (int) icon.getHeight()).getRed() > 0.2
                || pr.getColor(x + (int) icon.getWidth(), y + (int) icon.getHeight()).getRed() > 0.2) {
            x = rand.nextInt((int) (495 - icon.getWidth()));
            y = rand.nextInt((int) (660 - icon.getHeight()));
        }

        // Moving the dots to the randomized coorindates
        super.setTranslateX(x);
        super.setTranslateY(y);
    }
}
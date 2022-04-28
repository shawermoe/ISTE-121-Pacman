
// All neccessary imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * Pacman Class with JavaFX
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class Pacman extends Player {
    // Attributes to set the initial position of the Pacman
    private static int initialX = 234;
    private static int initialY = 508;

    // Constructor for the Pacman character
    public Pacman(PixelReader pr) throws FileNotFoundException {
        super(initialX, initialY, new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/pacman.gif"))), pr);
    }

    public void die() {
        this.setX(234);
        this.setY(508);
    }

    @Override
    public void update() {
        super.update();
        super.getIconView().setRotate(this.getDirection());
    }
}
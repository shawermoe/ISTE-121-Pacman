
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
    // Attributes for displaying the pacman on the map
    private static Image icon;

    static {
        try {
            icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/pacman.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Attributes to set the initial position of the Pacman
    private static int initialX = 234;
    private static int initialY = 508;

    // Constructor for the Pacman character
    public Pacman(PixelReader pr) {
        super(initialX, initialY, icon, pr);
    }

    // Method which sets the position of the Pacman once they die
    public void die() {
        this.setX(234);
        this.setY(508);
    }

    @Override
    // Movement method
    public void update() {
        super.update();
        super.getIconView().setRotate(this.getDirection());
    }
}
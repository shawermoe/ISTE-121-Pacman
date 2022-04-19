
// All neccessary imports
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Pacman Class with JavaFX
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class Pacman extends Pane {
    // Attributes for the pacman character

    // Position attributes
    private double pacmanX;
    private double pacmanY;

    private int pacmanRotation;

    // Attributes to show the Pacman
    private Image aPic;
    private ImageView picView;

    // Constructor for the Pacman character
    public Pacman() {
        // Drawing the icon
        try {
            aPic = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/races.gif")));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        picView = new ImageView(aPic);

        // Adding the Pacman character to the pane
        this.getChildren().add(picView);
    }

    // Sets the direction of the Pacman character
    public void setDirection(int rotation) {
        this.pacmanRotation = rotation;
    }

    // Method to update the pacman's position and rotation
    public void update() {

        switch (this.pacmanRotation) {
            // Case when Pacman is facing up
            case -90:
                this.pacmanY -= 3.5;
                break;

            // Case when Pacman is facing right
            case 0:
                this.pacmanX += 3.5;
                break;

            // Case when Pacman is facing down
            case 90:
                this.pacmanY += 3.5;
                break;

            // Case when Pacman is facing left
            case 180:
                this.pacmanX -= 3.5;
                break;

            // Default is empty since there's no other possible values
            default:
                break;
        }

        // Setting the position and rotation of the Pacman character
        picView.setTranslateX(this.pacmanX);
        picView.setTranslateY(this.pacmanY);
        picView.setRotate(this.pacmanRotation);
    }
}

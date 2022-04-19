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

    public Pacman() {
        // Drawing the icon
        try {
            aPic = new Image(new FileInputStream(new File("pacman.png")));
        } catch (FileNotFoundException fnfe) {
            // TODO Auto-generated catch block
            fnfe.printStackTrace();
        }

        picView = new ImageView(aPic);

        this.getChildren().add(picView);
    }

    public void setDirection(int rotation) {
        this.pacmanRotation = rotation;
    }

    // Method to update the pacman's position and rotation
    public void update() {

        switch (this.pacmanRotation) {
            case -90:
                this.pacmanY -= 5;
                break;

            case 0:
                this.pacmanX += 5;
                break;

            case 90:
                this.pacmanY += 5;
                break;

            case 180:
                this.pacmanX -= 5;
                break;
            default:
                break;
        }

        picView.setTranslateX(this.pacmanX);
        picView.setTranslateY(this.pacmanY);
        picView.setRotate(this.pacmanRotation);
    }
}

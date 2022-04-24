import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Ghost extends Pane {

    // Position attributes
    private double ghostX = 70;
    private double ghostY = 80;

    private int speed = 4;

    // Attributes for displaying the ghosts
    private Image icon;
    private ImageView picView;

    public Ghost() {
        try {
            // Drawing the icon
            icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/ghost.gif")));
            picView = new ImageView(icon);
            picView.setTranslateX(ghostX);
            picView.setTranslateY(ghostY);

            // Adding the Pacman character to the pane
            this.getChildren().add(picView);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

}

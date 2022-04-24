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
    private int rotation;

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

    /**
     * @param rotation is direction of the pacman.
     */
    public void setDirection(int rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the image from the pacman object.
     */
    public Image getIcon() {
        return icon;
    }

    public void update() {
        switch (this.rotation) {
            // Case when Pacman is facing up
            case -90:
                this.ghostY -= speed;
                break;

            // Case when Pacman is facing right
            case 0:
                this.ghostX += speed;
                break;

            // Case when Pacman is facing down
            case 90:
                this.ghostY += speed;
                break;

            // Case when Pacman is facing left
            case 180:
                this.ghostX -= speed;
                break;

            // Default is empty since there's no other possible values
            default:
                break;
        }
        // Setting the position and rotation of the Pacman character
        picView.setTranslateX(this.ghostX);
        picView.setTranslateY(this.ghostY);
    }

    /**
     * This method is used to calculate the next X coordinate of the pacman object
     * based on the rotation or direction the pacman is facing
     */
    public double nextX() {
        switch (this.rotation) {
            // Case when Pacman is facing up
            case -90:
                return this.ghostX;

            // Case when Pacman is facing right
            case 0:
                return this.ghostX + speed;

            // Case when Pacman is facing down
            case 90:
                return this.ghostX;

            // Case when Pacman is facing left
            case 180:
                return this.ghostX - speed;

            // Default is empty since there's no other possible values
            default:
                return 0.0;
        }
    }

    /**
     * This method is used to calculate the next Y coordinate of the pacman object
     * based on the rotation or direction the pacman is facing
     */
    public double nextY() {
        switch (this.rotation) {
            // Case when Pacman is facing up
            case -90:
                return this.ghostY - speed;

            // Case when Pacman is facing right
            case 0:
                return this.ghostY;

            // Case when Pacman is facing down
            case 90:
                return this.ghostY + speed;

            // Case when Pacman is facing left
            case 180:
                return this.ghostY;

            // Default is empty since there's no other possible values
            default:
                return 0.0;
        }
    }

    /**
     * @return the ghostX
     */
    public double getGhostX() {
        return ghostX;
    }

    /**
     * @param ghostX the ghostX to set
     */
    public void setGhostX(double ghostX) {
        this.ghostX = ghostX;
    }

    /**
     * @return the ghostY
     */
    public double getGhostY() {
        return ghostY;
    }

    /**
     * @param ghostY the ghostY to set
     */
    public void setGhostY(double ghostY) {
        this.ghostY = ghostY;
    }

}

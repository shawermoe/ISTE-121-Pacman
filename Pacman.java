
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
    private static int initialY = 628;

    // Attribute to keep count of the number of lives of the pacman
    private int lives = 3;

    private int score = 0;

    private Ghost[] ghosts;

    // Constructor for the Pacman character
    public Pacman(PixelReader pr, Ghost[] ghosts) {
        super(initialX, initialY, icon, pr);
        this.ghosts = ghosts;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    // Method which sets the position of the Pacman once they die
    public void loseLife() {
        this.setX(initialX);
        this.setY(initialY);
        this.getIconView().setTranslateX(this.getX());
        this.getIconView().setTranslateY(this.getY());

        lives--;
        System.out.println("Number of lives is: " + lives);
    }

    @Override
    // Movement method
    public void update() {
        for (int i = 0; i < ghosts.length; i++) {
            if (ghosts[i].getIconView().getBoundsInParent().intersects(this.getIconView().getBoundsInParent())) {
                loseLife();
            }
        }

        if (!super.checkWallCollision()) {
            // Implemented dynamic movement for the pacman
            double speed = this.getSpeed();
            speed += 0.0001;
            this.setSpeed(speed);

            super.update();
            super.getIconView().setRotate(this.getDirection());
        }
    }
}
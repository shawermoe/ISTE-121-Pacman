
// All neccessary imports
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Pacman Class with JavaFX
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class Player extends Pane {
    // Position, direction and speed attributes
    private double x;
    private double y;
    private int direction = -90;
    private int speed = 2;

    // Attributes for reading and displaying the icons
    private PixelReader pr;
    private Image icon;
    private ImageView iconView;

    // Constructor takes in initial X and Y as well as the image
    public Player(int x, int y, Image icon, PixelReader pr) {
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.pr = pr;

        this.iconView = new ImageView(icon);
        iconView.setTranslateX(x);
        iconView.setTranslateY(y);
        this.getChildren().add(iconView);
    }

    /**
     * @return the X coordinate of the player.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return the icon
     */
    public Image getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    /**
     * @return the iconView
     */
    public ImageView getIconView() {
        return iconView;
    }

    /**
     * @param iconView the iconView to set
     */
    public void setIconView(ImageView iconView) {
        this.iconView = iconView;
    }

    public void update() {
        switch (this.direction) {
            // Case when Pacman is facing up
            case -90:
                this.y -= speed;
                break;

            // Case when Pacman is facing right
            case 0:
                this.x += speed;
                break;

            // Case when Pacman is facing down
            case 90:
                this.y += speed;
                break;

            // Case when Pacman is facing left
            case 180:
                this.x -= speed;
                break;

            // Default is empty since there's no other possible values
            default:
                break;
        }
        // Setting the position and rotation of the Pacman character
        iconView.setTranslateX(this.x);
        iconView.setTranslateY(this.y);
    }

    /**
     * This method is used to calculate the next X coordinate of the pacman object
     * based on the rotation or direction the pacman is facing
     */
    public double nextX() {
        switch (this.direction) {
            // Case when Pacman is facing up
            case -90:
                return this.x;

            // Case when Pacman is facing right
            case 0:
                return this.x + speed;

            // Case when Pacman is facing down
            case 90:
                return this.x;

            // Case when Pacman is facing left
            case 180:
                return this.x - speed;

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
        switch (this.direction) {
            // Case when Pacman is facing up
            case -90:
                return this.y - speed;

            // Case when Pacman is facing right
            case 0:
                return this.y;

            // Case when Pacman is facing down
            case 90:
                return this.y + speed;

            // Case when Pacman is facing left
            case 180:
                return this.y;

            // Default is empty since there's no other possible values
            default:
                return 0.0;
        }
    }

    public boolean checkWallCollision() {
        Color check1 = pr.getColor((int) this.nextX(), (int) this.nextY());
        Color check2 = pr.getColor((int) (this.nextX() + this.getIcon().getWidth()), (int) this.nextY());
        Color check3 = pr.getColor((int) (this.nextX() + this.getIcon().getWidth()),
                (int) (this.nextY() + this.getIcon().getHeight()));
        Color check4 = pr.getColor((int) this.nextX(), (int) (this.nextY() + this.getIcon().getHeight()));

        return check1.getRed() > 0.2 || check2.getRed() > 0.2 || check3.getRed() > 0.2 || check4.getRed() > 0.2;

    }

}

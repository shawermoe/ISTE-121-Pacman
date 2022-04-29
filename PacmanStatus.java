import java.io.Serializable;
//Serialization - conversion of the state of an object into a byte stream

public class PacmanStatus implements Serializable {
    private int ID;
    private int X;
    private int Y;
    private int direction;

    // parameterized constructor
    public PacmanStatus(int id, int X, int Y, int direction) {
        this.ID = id;
        this.X = X;
        this.Y = Y;
        this.direction = direction;
    }

    /**
     * @return the id
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the x value
     */
    public int getX() {
        return X;
    }

    /**
     * @return the y value
     */
    public int getY() {
        return Y;
    }

    /**
     * @return the direction of the pacman
     */
    public int getDirection() {
        return direction;
    }

}
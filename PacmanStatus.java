import java.io.Serializable;
//Serialization - conversion of the state of an object into a byte stream

public class PacmanStatus implements Serializable {
    private int ID;
    private int X;
    private int Y;
    private int direction;

    public PacmanStatus(int id, int X, int Y, int direction) {
        this.ID = id;
        this.X = X;
        this.Y = Y;
        this.direction = direction;
    }

    int getID() {
        return ID;
    }

    /**
     * @return the x
     */
    public int getX() {
        return X;
    }

    /**
     * @return the y
     */
    public int getY() {
        return Y;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

}
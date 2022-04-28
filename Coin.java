import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

public class Coin extends ImageView {
    private static Image icon;
    private PixelReader pr;
    private Random rand = new Random();

    private int x;
    private int y;

    static {
        try {
            icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/dot.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Coin(PixelReader pr) {
        super(icon);

        this.pr = pr;

        while (pr.getColor(x, y).getRed() > 0.2
                || pr.getColor(x + (int) icon.getWidth(), y).getRed() > 0.2
                || pr.getColor(x, y + (int) icon.getHeight()).getRed() > 0.2
                || pr.getColor(x + (int) icon.getWidth(), y + (int) icon.getHeight()).getRed() > 0.2) {
            x = rand.nextInt((int) (495 - icon.getWidth()));
            y = rand.nextInt((int) (660 - icon.getHeight()));
        }

        super.setTranslateX(x);
        super.setTranslateY(y);
    }
}
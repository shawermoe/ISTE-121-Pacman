import java.io.IOException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlScene {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // method for switching between scenes
    public void choosePlayers(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("players.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void switchToInstructions(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("instructions.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void chooseMaps(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("maps.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void backToMain(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("landing.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void backToPlayers(ActionEvent ae) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("players.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void exit(ActionEvent ae) {
        System.exit(0);
    }
}

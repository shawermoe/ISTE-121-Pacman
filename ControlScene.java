
import java.io.IOException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlScene {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // method for switching between scenes
    public void choosePlayers(ActionEvent ae) {
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/players.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/fxml/instructions.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/fxml/maps.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/fxml/landing.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("/fxml/players.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void singleEasy(ActionEvent ae) {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        SinglePlayerEasy sp = new SinglePlayerEasy();
        sp.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void singleHard(ActionEvent ae) {
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        SinglePlayerHard sh = new SinglePlayerHard();
        sh.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent ae) {
        System.exit(0);
    }
}

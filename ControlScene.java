
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

    // methods for switching between scenes
    public void choosePlayers(ActionEvent ae) {
        try {
            // switching to the players fxml and displaying that scene on the stage
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
            // switching to the instructions fxml and displaying that scene on the stage
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
            // switching to the maps for fxml single player and displaying that scene on the
            // stage
            root = FXMLLoader.load(getClass().getResource("/fxml/maps.fxml"));
            stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void chooseMapsMulti(ActionEvent ae) {
        try {
            // switching to the maps for fxml hard player and displaying that scene on the
            // stage
            root = FXMLLoader.load(getClass().getResource("/fxml/mapsMulti.fxml"));
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
            // switching to the main lobby fxml and displaying that scene on the stage
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
            // switching to the players lobby fxml and displaying that scene on the stage
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
        // switching to the java file for single player, easy mode and displaying that
        // scene on the stage
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        SinglePlayerEasy sp = new SinglePlayerEasy();
        sp.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void singleHard(ActionEvent ae) {
        // switching to the java file for single player, hard mode and displaying that
        // scene on the stage
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        SinglePlayerHard sh = new SinglePlayerHard();
        sh.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void multiEasy(ActionEvent ae) {
        // switching to the java file for multiplayer, easy mode and displaying that
        // scene on the stage
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        MultiEasy me = new MultiEasy();
        me.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void multiHard(ActionEvent ae) {
        // switching to the java file for multiplayer, hard mode and displaying that
        // scene on the stage
        stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        MultiHard mh = new MultiHard();
        mh.start(stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent ae) {
        // exiting the game
        System.exit(0);
    }
}

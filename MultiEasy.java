
// All neccessary imports
import javafx.animation.AnimationTimer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.*;
import java.io.*;

/**
 * Pacman Multiplayer with easy map with JavaFX and Thread
 * 
 * @ASSESSME.INTENSITY:LOW
 * @author - Kiara Toska
 * @author - Mohamed Amgad
 */

public class MultiEasy extends Application implements EventHandler<KeyEvent> {
    // Window attributes
    private Stage stage;
    private StackPane root;
    private int width = 495;
    private int height = 690;

    // araylists for coins and pellets
    private List<ImageView> coins = new ArrayList<>();
    private List<ImageView> pellets = new ArrayList<>();

    // Pacman attributes
    private Pacman pacman; // The pacman to control
    private Pacman pacman2; // Multiplayer pac man

    // Array of ghosts
    private Ghost[] ghosts = new Ghost[4];

    // Number of coins
    private int numCoins = 10;

    // Number of pellets
    private int numPellets = 2;

    private AnimationTimer timer; // Timer to control animation
    private PixelReader pr; // PixelReader to implement collision

    // Labels for the lives and points counters
    private Label lblLivesCounter = null;
    private Label lblPointsCounter = null;

    // Rand for random number generator
    private Random rand = new Random();

    // Chat GUI
    private TextField tfServer = new TextField("localhost");
    private TextField tfName = new TextField();
    private Button btnConnect = new Button("Connect");
    private TextArea taForMessages = new TextArea();
    private TextField tfMessageToSend = new TextField();

    // Chat attributes
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private static final int SERVER_PORT = 1234;

    // ClientID attribute for ID Handling
    private int currentID = -1;

    // main program
    public static void main(String[] args) {
        launch(args);
    }

    // start() method, called via launch
    public void start(Stage stage) {
        // stage set up
        this.stage = stage;
        stage.setTitle("Pacman - Kiara & Moe");
        stage.setOnCloseRequest(
                evt -> System.exit(0));

        // Stackpane pane
        this.root = new StackPane();
        root.setAlignment(Pos.TOP_LEFT);

        // Initializing all the attributes
        initializeScene();
    }

    // start the race
    public void initializeScene() {
        // Showing the chat
        showChat(new Stage());

        // Adding the map
        initializeMap();

        // Adding the pacman player
        initializePacman();

        // GUI for points and lives system
        Label lblLives = new Label("Lives: ");
        lblLivesCounter = new Label(pacman.getLives() + "");
        Label lblPoints = new Label("Points: ");
        lblPointsCounter = new Label(pacman.getScore() + "");
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(lblLives, lblLivesCounter, lblPoints, lblPointsCounter);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(hbox);

        // Adding the 4 ghosts
        initializeGhosts();

        // Adding the coins
        generateCoins(numCoins);

        // Adding the pellets
        generatePowerPellet(numPellets);

        // display the window
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();

        // Adding keyboard functionality
        scene.setOnKeyPressed(this);

        // Use an animation to update the screen
        timer = new AnimationTimer() {
            public void handle(long now) {
                // checks if any coins were picked up
                checkCoinCollision();

                // Checks if any of the pellets were picked up
                checkPowerpelletCollision();

                // moves the pacman
                pacman.update();
                pacman2.update();

                // Moves all the ghosts simultaneously
                for (Ghost ghost : ghosts) {
                    ghost.update();
                }

                // If there are no more coins left to collect
                if (coins.size() == 0) {
                    timer.stop();
                    Alert alert = new Alert(AlertType.INFORMATION, "You have sucessfully collected all the coins!");
                    alert.setHeaderText("Winner winner, chicken dinner!");
                    alert.setTitle("You Won!");
                    Platform.runLater(new Runnable() {
                        public void run() {
                            alert.showAndWait();
                        };
                    });
                }

                // Sets number of lives
                lblLivesCounter.setText(pacman.getLives() + "");

                if (pacman.getLives() == 0) {
                    timer.stop();

                    Alert alert = new Alert(AlertType.ERROR, "You Ran Out Of Lives!");
                    alert.setHeaderText("Better Luck Next Time!");
                    alert.setTitle("You Lost!");
                    Platform.runLater(new Runnable() {
                        public void run() {
                            alert.showAndWait();
                        };
                    });
                }
            }
        };

        // TimerTask to delay start of race for 2 seconds
        TimerTask task = new TimerTask() {
            public void run() {
                timer.start();
            }
        };
        Timer startTimer = new Timer();
        long delay = 1000L;
        startTimer.schedule(task, delay);

    }

    // Checking whether the pacman has collided with the coin
    public void checkCoinCollision() {

        // Grabbing the score from the pacman
        int score = pacman.getScore();

        // loops through all the coins and adds score to the pacman
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getBoundsInParent().intersects(pacman.getIconView().getBoundsInParent())) {
                root.getChildren().remove(coins.get(i));
                coins.remove(coins.get(i));
                score += 10;
                pacman.setScore(score);
                lblPointsCounter.setText(pacman.getScore() + "");
            }
        }
    }

    // checks if pacman collided with power pellet
    public void checkPowerpelletCollision() {
        int lives = pacman.getLives();

        for (int i = 0; i < pellets.size(); i++) {

            if (pellets.get(i).getBoundsInParent().intersects(pacman.getIconView().getBoundsInParent())) {
                root.getChildren().remove(pellets.get(i));
                pellets.remove(pellets.get(i));
                lives++;
                pacman.setLives(lives);
            }
        }

    }

    // Generate the pellets on the map
    public void generatePowerPellet(int numberOfPellets) {
        try {
            Image icon = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/strawberry.png")));

            for (int i = 0; i < numberOfPellets; i++) {

                ImageView iv = new ImageView(icon);
                pellets.add(iv);

                int x = 0;
                int y = 0;

                while (pr.getColor(x, y).getRed() > 0.2
                        || pr.getColor(x + (int) icon.getWidth(), y).getRed() > 0.2
                        || pr.getColor(x, y + (int) icon.getHeight()).getRed() > 0.2
                        || pr.getColor(x + (int) icon.getWidth(), y + (int) icon.getHeight())
                                .getRed() > 0.2) {
                    x = rand.nextInt((int) (495 - icon.getWidth()));
                    y = rand.nextInt((int) (660 - icon.getHeight()));
                }

                // Moving the dots to the randomized coorindates
                pellets.get(i).setTranslateX(x);
                pellets.get(i).setTranslateY(y);

                root.getChildren().add(pellets.get(i));
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Randomizing coins across map
    public void generateCoins(int numberOfCoins) {
        for (int i = 0; i < numberOfCoins; i++) {
            Coin coin = new Coin(pr);
            coins.add(coin);
            root.getChildren().add(coin);
        }
    }

    // Adding map to the GUI
    public void initializeMap() {
        try {
            // Adding the map to the game
            Image map = new Image(new FileInputStream(new File("ISTE-121-Pacman/assets/map-easy.jpg")));
            pr = map.getPixelReader();
            root.getChildren().add(new ImageView(map));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Adding the ghosts to the root
    public void initializeGhosts() {
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i] = new Ghost(pr);
            root.getChildren().add(ghosts[i]);
        }
    }

    // Adding the pacman to the root
    public void initializePacman() {
        // The Pacman object
        pacman = new Pacman(pr, ghosts);
        pacman2 = new Pacman(pr, ghosts);

        // Adding the pacman
        root.getChildren().addAll(pacman, pacman2);
    }

    public void showChat(Stage chatStage) {
        chatStage.setTitle("Client");
        final int WIDTH = 500;
        final int HEIGHT = 400;
        final int X = 50;
        final int Y = 100;
        chatStage.setX(X);
        chatStage.setY(Y);
        tfMessageToSend.setPromptText("Message to send");
        chatStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent evt) {
                System.exit(0);
            }
        });

        // Draw the GUI
        VBox chatRoot = new VBox();

        HBox hbTop = new HBox(10);
        hbTop.getChildren().addAll(new Label("Server"), tfServer, new Label("Name"), tfName, btnConnect);

        chatRoot.getChildren().addAll(hbTop, taForMessages, tfMessageToSend);
        for (Node n : root.getChildren()) {
            VBox.setMargin(n, new Insets(5));
        }

        btnConnect.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                doConnect();
            }

        });

        // Set the scene and show the stage
        Scene chatScene = new Scene(chatRoot, WIDTH, HEIGHT);
        chatStage.setScene(chatScene);
        chatStage.show();

        // Adjust sizes
        taForMessages.setPrefHeight(HEIGHT - hbTop.getPrefHeight() - tfMessageToSend.getPrefHeight());
        tfServer.setPrefColumnCount(12);
        tfName.setPrefColumnCount(7);

        tfMessageToSend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    sendMessage();
                }
            }

        });
    }

    // method for connecting with the server
    private void doConnect() {
        try {
            timer.start();
            this.socket = new Socket("localhost", SERVER_PORT);
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());

            this.oos.writeObject("REGISTER@" + tfName.getText());
            this.oos.flush();
            // System.out.println("Watiing for currenID");
            this.currentID = (Integer) this.ois.readObject();
            // System.out.println("Received currenID");
            // switch (this.currentID) {
            // case 0:
            // // slider2.setDisable(true);
            // break;
            // case 1:
            // // slider1.setDisable(true);
            // break;
            // }

            ClientThread cT = new ClientThread();
            cT.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // seding a message to the server
    private void sendMessage() {
        // NAME:MESSAGE
        try {
            this.oos.writeObject("CHAT@" + tfName.getText() + ":" + tfMessageToSend.getText());
            this.oos.flush();
            tfMessageToSend.clear();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class ClientThread extends Thread {
        @Override
        public void run() {

            try {
                while (true) {
                    Object obj = ois.readObject();
                    if (obj instanceof String) {
                        String message = (String) obj;// chat feedback
                        // appending the taLog with the message received
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                taForMessages.appendText(message + "\n");
                            }
                        });

                    } else if (obj instanceof PacmanStatus) {
                        PacmanStatus newStatus = (PacmanStatus) obj;
                        if (newStatus.getID() != currentID) {
                            // ID handling
                            switch (newStatus.getID()) {
                                case 0:
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            pacman.setDirection(newStatus.getDirection());
                                            pacman.setX(newStatus.getX());
                                            pacman.setY(newStatus.getY());
                                        }
                                    });
                                    break;
                                case 1:
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            pacman.setDirection(newStatus.getDirection());
                                            pacman.setX(newStatus.getX());
                                            pacman.setY(newStatus.getY());
                                        }
                                    });
                                    break;
                            }
                        }
                    }

                }
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
     * Keyhandler which is used to grab events from the keyboard
     * The functionality behind it revolves rotating the pacman
     * With every key press. The controls involve W,A,S,D and
     * the arrow keys.
     */
    @Override
    public void handle(KeyEvent ke) {
        switch (ke.getCode()) {
            case W:
            case UP:
                pacman.setDirection(-90);
                break;

            case D:
            case RIGHT:
                pacman.setDirection(0);
                break;

            case S:
            case DOWN:
                pacman.setDirection(90);
                break;

            case A:
            case LEFT:
                pacman.setDirection(180);
                break;

            default:
                break;
        }
    }

} // end class Races
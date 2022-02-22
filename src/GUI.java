//Imports all necessary packages to run the Main class
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.util.Duration;


public class GUI extends Application {

    public void textFieldSizeSet(ArrayList<TextField> allTexts){
        for (TextField i: allTexts){
            i.setPrefSize(70,70);
            i.setFont(Font.loadFont("file:scrabble-font" + File.separator + "Scramble-KVBe.ttf", 25));

            i.setPadding(new Insets(0,0,0,0));

            i.lengthProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (i.getText().length() >= 1) {
                        i.setText(i.getText().substring(0, 1));
                    }
                }
            });
        }

    }

    //Creates dimension of the window
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    //Initial Mouse X and Y position
    public double mouseX = (double)WIDTH/2;
    public double mouseY = (double)HEIGHT/2;

    //Colour of the firework
    protected static double redValue = 1;
    protected static double blueValue = 1;
    protected static double greenValue = 1;

    //Type of firework being launched
    protected static int fireworkType = 0;

    @Override
    public void start(Stage stage) throws IOException {
        //Creates root and scene
        Group root = new Group();
        Scene scene = new Scene(root);

        //Creates new Canvas and sets width and height
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Font scrabbleFont = Font.loadFont(GUI.class.getResource("scrabble-font/Scramble-KVBe.ttf").toExternalForm(), 10);

        HBox hbox = new HBox ();
        hbox.setPadding(new Insets(0, 12, 0, 12) );
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-colour: #336699; ");

        TextField letterOne = new TextField();
        TextField letterTwo = new TextField();
        TextField letterThree = new TextField();
        TextField letterFour = new TextField();
        TextField letterFive = new TextField();
        TextField letterSix = new TextField();
        TextField letterSeven = new TextField();
        TextField letterEight = new TextField();

        ArrayList<TextField> allLetters = new ArrayList<>();
        Collections.addAll(allLetters, letterOne, letterTwo, letterThree, letterFour,
                letterFive, letterSix, letterSeven, letterEight);

        textFieldSizeSet(allLetters);


        hbox.setPrefSize(600,100);
        hbox.setLayoutX(100);
        hbox.setLayoutY(100);
        hbox.setAlignment(Pos.CENTER);
        for (TextField i: allLetters){
            hbox.getChildren().add(i);
        }

        root.getChildren().add(hbox);

        gc.setFill(new Color(0.71764705882,0,0.02352941176, 1));
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        //Updates the X and Y location of the mouse when it is moved
        root.setOnMouseMoved(event -> {
            mouseX = event.getSceneX()-10;
            mouseY = event.getSceneY()-10;
        });


        //Setup Animation
        Timeline loop = new Timeline();
        loop.setCycleCount(Timeline.INDEFINITE);

        // Define what to do for our animation and how often
        // to switch to the next frame
        KeyFrame frames = new KeyFrame(
                Duration.seconds(0.017),
                new EventHandler<ActionEvent>(){

                    public void handle(ActionEvent event){

                        GraphicsContext gc = canvas.getGraphicsContext2D();

                        //Clears the controller section at the bottom of the Scene
                        gc.clearRect(0, 0, WIDTH, HEIGHT);

                        //Repaints the scene
                        gc.setFill(new Color(0.71764705882,0,0.02352941176, 1));
                        gc.fillRect(0, 0, WIDTH, HEIGHT);

                    }
                }
        );

        loop.getKeyFrames().add(frames);
        loop.play();

        stage.setScene(scene);
        stage.setTitle("Fireworks");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

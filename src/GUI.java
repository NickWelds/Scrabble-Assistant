//Imports all necessary packages to run the Main class
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.util.Duration;


public class GUI extends Application {



    public void textFieldSizeSet(ArrayList<TextField> allTexts, ArrayList<Character> acceptableValues){
        for (TextField i: allTexts){
            i.setPrefSize(50,50);

            i.setFont(Font.loadFont("file:scrabble-font" + File.separator + "Scramble-KVBe.ttf", 30));

            i.setPadding(new Insets(0,0,0,0));

            i.setAlignment(Pos.CENTER);

            i.lengthProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (i.getText().length() >= 1) {
                        i.setText(i.getText().substring(0, 1));
                    }
                }
            });

            i.textProperty().addListener((observable, oldValue, newValue) ->{
                if (!newValue.matches("\\sa-zA-Z*")) {
                    i.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
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

    public int numOfRestrictions = 7;

    @Override
    public void start(Stage stage) {
        //Creates root and scene
        Group root = new Group();
        Scene scene = new Scene(root);

        //Creates new Canvas and sets width and height
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        ArrayList<Character> acceptableValues = new ArrayList<>();
        for (int i = 65; i <= 26 + 65; i ++){
            acceptableValues.add((char)i);
        }

        //Font scrabbleFont = Font.loadFont(GUI.class.getResource("scrabble-font/Scramble-KVBe.ttf").toExternalForm(), 10);


        //TODO Input Letters
        HBox letterInput = new HBox ();
        letterInput.setPadding(new Insets(0, 12, 0, 12) );
        letterInput.setSpacing(10);
        letterInput.setStyle("-fx-background-colour: #336699; ");

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

        textFieldSizeSet(allLetters, acceptableValues);

        letterInput.setPrefSize(600,100);
        letterInput.setLayoutX(100);
        letterInput.setLayoutY(100);
        letterInput.setAlignment(Pos.CENTER);
        for (TextField i: allLetters){
            letterInput.getChildren().add(i);
        }

        root.getChildren().add(letterInput);

        //TODO Input Length of word

        HBox lengthInput = new HBox ();
        lengthInput.setPadding(new Insets(0, 12, 0, 12) );
        lengthInput.setSpacing(10);
        lengthInput.setStyle("-fx-background-colour: #336699; ");

        Text textWordLength = new Text();
        textWordLength.setText("Input the Length of Desired Word:");

        Slider sliderWordLength = new Slider(2,15, 7);

        sliderWordLength.setStyle("-fx-tick-label-fill: #336699;");

        sliderWordLength.setShowTickLabels(true);
        sliderWordLength.setShowTickMarks(true);
        sliderWordLength.setSnapToTicks(true);

        sliderWordLength.setMajorTickUnit(1);
        sliderWordLength.setMinorTickCount(0);
        sliderWordLength.setBlockIncrement(1);

        sliderWordLength.setPrefSize(300,50);

        textWordLength.setFill(Color.WHITE);

        textWordLength.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        lengthInput.setPrefSize(700,100);
        lengthInput.setLayoutX(50);
        lengthInput.setLayoutY(200);
        lengthInput.setAlignment(Pos.CENTER);
        lengthInput.getChildren().add(textWordLength);
        lengthInput.getChildren().add(sliderWordLength);

        root.getChildren().add(lengthInput);



        //TODO Letter Restrictions

        HBox restrictionsInput = new HBox ();
        restrictionsInput.setPadding(new Insets(0, 12, 0, 12) );
        restrictionsInput.setSpacing(10);
        restrictionsInput.setStyle("-fx-background-colour: #336699; ");

        ArrayList<TextField> letterRestrictions = new ArrayList<>();
        for(int i = 0; i < sliderWordLength.getValue(); i++){
            letterRestrictions.add(new TextField());
        }

        textFieldSizeSet(letterRestrictions, acceptableValues);

        restrictionsInput.setPrefSize(700,100);
        restrictionsInput.setLayoutX(50);
        restrictionsInput.setLayoutY(300);
        restrictionsInput.setAlignment(Pos.CENTER);
        for (TextField i: letterRestrictions){
            restrictionsInput.getChildren().add(i);
        }
        root.getChildren().add(restrictionsInput);

        /*
        TextField textFieldWordLength = new TextField();
        textFieldWordLength.setPrefSize(50,50);
        textFieldWordLength.setPadding(new Insets(0,0,0,0));
        textFieldWordLength.setAlignment(Pos.CENTER);

        textFieldWordLength.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (textFieldWordLength.getText().length() >= 2) {
                    if((Integer.parseInt(textFieldWordLength.getText()) > 15 || Integer.parseInt(textFieldWordLength.getText()) < 2)){
                        textFieldWordLength.setText(textFieldWordLength.getText().substring(0, 2));
                    }

                }
            }
        });

        textFieldWordLength.textProperty().addListener((observable, oldValue, newValue) ->{
            if(!newValue.matches("\\d*")){
                textFieldWordLength.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        if(Integer.parseInt(textFieldWordLength.getText()) > 15) {
            textFieldWordLength.setText("15");
        }

        if(Integer.parseInt(textFieldWordLength.getText()) < 2) {
            textFieldWordLength.setText("2");
        }


        textFieldWordLength.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
         */











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
                event -> {

                    GraphicsContext gc1 = canvas.getGraphicsContext2D();

                    //Clears the controller section at the bottom of the Scene
                    gc1.clearRect(0, 0, WIDTH, HEIGHT);

                    //Repaints the scene
                    gc1.setFill(new Color(0.71764705882,0,0.02352941176, 1));
                    gc1.fillRect(0, 0, WIDTH, HEIGHT);

                    numOfRestrictions = (int)sliderWordLength.getValue();

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

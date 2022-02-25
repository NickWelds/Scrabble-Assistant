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
import javafx.scene.control.Button;
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

    public HBox textSet(HBox hbox, Text text, int y, int size, String textDisplay){
        hbox.setPrefSize(800, 50);
        hbox.setLayoutY(y);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        text.setText(textDisplay);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(text);
        return hbox;
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

        root.getChildren().add(textSet(new HBox(), new Text(), 80, 15, "Input Your Scrabble Letters"));

        //TODO Input Letters
        HBox letterInput = new HBox ();
        letterInput.setPadding(new Insets(0, 12, 0, 12) );
        letterInput.setSpacing(10);
        letterInput.setStyle("-fx-background-colour: #336699; ");

        ArrayList<TextField> allLetters = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            allLetters.add(new TextField());
        }

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

        textSet(lengthInput, new Text(), 0, 20, "Input the Length of Desired Word:");

        Slider sliderWordLength = new Slider(2,15, 8);

        sliderWordLength.setStyle("-fx-tick-label-fill: #ffffff;");

        sliderWordLength.setShowTickLabels(true);
        sliderWordLength.setShowTickMarks(true);
        sliderWordLength.setSnapToTicks(true);

        sliderWordLength.setMajorTickUnit(1);
        sliderWordLength.setMinorTickCount(0);
        sliderWordLength.setBlockIncrement(1);

        sliderWordLength.setPrefSize(300,50);

        lengthInput.setPrefSize(700,100);
        lengthInput.setLayoutX(50);
        lengthInput.setLayoutY(200);
        lengthInput.setAlignment(Pos.CENTER);
        lengthInput.getChildren().add(sliderWordLength);

        root.getChildren().add(lengthInput);



        //TODO Letter Restrictions

        HBox restrictionsInput = new HBox ();
        restrictionsInput.setPadding(new Insets(0, 12, 0, 12) );
        restrictionsInput.setSpacing(10);
        restrictionsInput.setStyle("-fx-background-colour: #336699; ");

        ArrayList<TextField> letterRestrictions = new ArrayList<>();

        restrictionsInput.setPrefSize(700,100);
        restrictionsInput.setLayoutX(50);
        restrictionsInput.setLayoutY(400);
        restrictionsInput.setAlignment(Pos.CENTER);


        //TODO Commit button for restrictions

        Button buttonWordLengthConfirm = new Button();
        buttonWordLengthConfirm.setPrefSize(200,50);
        buttonWordLengthConfirm.setLayoutX(300);
        buttonWordLengthConfirm.setLayoutY(300);
        buttonWordLengthConfirm.setText("Confirm Word Length");

        buttonWordLengthConfirm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

        root.getChildren().add(buttonWordLengthConfirm);

        buttonWordLengthConfirm.setOnMouseClicked(event -> {
            for (TextField i: letterRestrictions){
                restrictionsInput.getChildren().remove(i);
            }

            letterRestrictions.clear();

            for(int i = 0; i < sliderWordLength.getValue(); i++){
                letterRestrictions.add(new TextField());
            }

            textFieldSizeSet(letterRestrictions, acceptableValues);

            for (TextField i: letterRestrictions){
                restrictionsInput.getChildren().add(i);
            }

            root.getChildren().add(textSet(new HBox(), new Text(), 380, 15, "Input Your Restrictions"));

        });

        root.getChildren().add(restrictionsInput);

        gc.setFill(new Color(0.71764705882,0,0.02352941176, 1));
        gc.fillRect(0, 0, WIDTH, HEIGHT);

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

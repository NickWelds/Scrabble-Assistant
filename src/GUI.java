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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;

import javafx.util.Duration;


public class GUI extends Application {



    public void textFieldSizeSet(ArrayList<TextField> allTexts){
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

    public void HBoxSettings(HBox hbox, int x, int y){
        hbox.setPadding(new Insets(0, 12, 0, 12) );
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-colour: #336699; ");
        hbox.setPrefSize(700,100);
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        hbox.setAlignment(Pos.CENTER);
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

        textFieldSizeSet(allLetters);

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

        HBox hBoxRestrictionsInput = new HBox ();
        HBoxSettings(hBoxRestrictionsInput, 50, 400);


        ArrayList<TextField> letterRestrictions = new ArrayList<>();

        //TODO Point Multiplier Button HBox

        HBox hBoxMultiplierButton = new HBox ();
        HBoxSettings(hBoxMultiplierButton, 50, 450);

        ArrayList<Button> multiplierButtons = new ArrayList<>();

        //TODO Commit button for restrictions
        Button buttonWordLengthConfirm = new Button();
        buttonWordLengthConfirm.setPrefSize(200,50);
        buttonWordLengthConfirm.setLayoutX(300);
        buttonWordLengthConfirm.setLayoutY(300);
        buttonWordLengthConfirm.setText("Confirm Word Length");

        buttonWordLengthConfirm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));


        buttonWordLengthConfirm.setOnMouseClicked(event -> {

            hBoxRestrictionsInput.getChildren().clear();
            letterRestrictions.clear();

            for(int i = 0; i < sliderWordLength.getValue(); i++){
                letterRestrictions.add(new TextField());
            }

            textFieldSizeSet(letterRestrictions);

            for (TextField i: letterRestrictions){
                hBoxRestrictionsInput.getChildren().add(i);
            }

            root.getChildren().add(textSet(new HBox(), new Text(), 380, 15, "Input Your Restrictions"));


            hBoxMultiplierButton.getChildren().clear();
            multiplierButtons.clear();

            for(int i = 0; i < sliderWordLength.getValue(); i++){
                multiplierButtons.add(new Button());
            }

            for(Button i: multiplierButtons){
                i.setPrefSize(50, 20);
                i.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                i.setText("-");
                i.setPadding(new Insets(5, 0, 5, 0));
                i.setAlignment(Pos.CENTER);
            }

            for (Button i : multiplierButtons) {
                i.setOnMouseClicked(event1 -> {
                    if (i.getText().equals("-")) {
                        i.setText("2X");
                    } else if (i.getText().equals("2X")) {
                        i.setText("3X");
                    } else if (i.getText().equals("3X")) {
                        i.setText("-");
                    }
                });
            }

            for (Button i: multiplierButtons){
                hBoxMultiplierButton.getChildren().add(i);
            }
        });

        root.getChildren().add(buttonWordLengthConfirm);
        root.getChildren().add(hBoxRestrictionsInput);
        root.getChildren().add(hBoxMultiplierButton);

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

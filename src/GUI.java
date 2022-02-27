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
import java.util.Objects;

import javafx.util.Duration;


public class GUI extends Application {


    public void textFieldSettings(ArrayList<TextField> textFields){
        for (TextField i: textFields){
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

    public void setTextFields(ArrayList<TextField> textFields, HBox hbox, int size){
        hbox.getChildren().clear();
        textFields.clear();

        for(int i = 0; i < size; i++){
            textFields.add(new TextField());
        }

        textFieldSettings(textFields);

        for (TextField i: textFields){
            hbox.getChildren().add(i);
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

    public void HBoxSettings(HBox hbox, int x, int y, int prefHeight){
        hbox.setPadding(new Insets(0, 12, 0, 12) );
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-colour: #336699; ");
        hbox.setPrefSize(700, prefHeight);
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        hbox.setAlignment(Pos.CENTER);
    }

    public void buttonSettings(Button button, int x, int y, String text){
        button.setPrefSize(200,50);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setText(text);
        button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(5, 0, 5, 0));
    }

    //Creates dimension of the window
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

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
        HBox hBoxLetterInput = new HBox ();
        HBoxSettings(hBoxLetterInput, 50, 125, 50);

        ArrayList<TextField> allLetters = new ArrayList<>();

        setTextFields(allLetters, hBoxLetterInput, 8);


        root.getChildren().add(hBoxLetterInput);

        //TODO Input Length of word

        HBox hBoxLengthInput = new HBox ();

        textSet(hBoxLengthInput, new Text(), 0, 20, "Input the Length of Desired Word:");

        HBoxSettings(hBoxLengthInput, 50, 200, 100);

        Slider sliderWordLength = new Slider(2,15, 8);

        sliderWordLength.setShowTickLabels(true);
        sliderWordLength.setShowTickMarks(true);
        sliderWordLength.setSnapToTicks(true);

        sliderWordLength.setMajorTickUnit(1);
        sliderWordLength.setMinorTickCount(0);
        sliderWordLength.setBlockIncrement(1);

        sliderWordLength.setPrefSize(300,50);


        hBoxLengthInput.getChildren().add(sliderWordLength);

        root.getChildren().add(hBoxLengthInput);

        //TODO Input Restrictions text HBox

        HBox hBoxRestrictionsText = new HBox();
        textSet(hBoxRestrictionsText, new Text(), 330, 15, "Input Your Restrictions");


        //TODO Letter Restrictions

        HBox hBoxRestrictionsInput = new HBox ();
        HBoxSettings(hBoxRestrictionsInput, 50, 375, 50);


        ArrayList<TextField> letterRestrictions = new ArrayList<>();

        //TODO Point Multiplier Button HBox

        HBox hBoxMultiplierButton = new HBox ();
        HBoxSettings(hBoxMultiplierButton, 50, 435, 20);

        ArrayList<Button> multiplierButtons = new ArrayList<>();

        //TODO Final Result HBox

        HBox hBoxResults = new HBox ();
        HBoxSettings(hBoxResults, 50, 600, 50);

        ArrayList<TextField> results = new ArrayList<>();

        //TODO Commit button for restrictions
        //Button buttonWordLengthConfirm = new Button();
        //buttonSettings(buttonWordLengthConfirm, 300, 300, "Confirm Word Length");

        sliderWordLength.valueProperty().addListener((observable, oldValue, newValue) ->{

            if (!Objects.equals(newValue.intValue(), oldValue.intValue())) {
                hBoxResults.getChildren().clear();
                results.clear();
                root.getChildren().remove(hBoxRestrictionsText);

                setTextFields(letterRestrictions, hBoxRestrictionsInput, (int)sliderWordLength.getValue());

                root.getChildren().add(hBoxRestrictionsText);


                hBoxMultiplierButton.getChildren().clear();
                multiplierButtons.clear();

                for(int i = 0; i < letterRestrictions.size(); i++){
                    multiplierButtons.add(new Button());
                }

                for(Button i: multiplierButtons){
                    i.setPrefSize(50, 20);
                    i.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                    i.setText("-");
                    i.setPadding(new Insets(5, 0, 5, 0));
                    i.setAlignment(Pos.CENTER);
                }

                for (Button i: multiplierButtons){
                    hBoxMultiplierButton.getChildren().add(i);
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

                Button buttonConfirmRestrictions = new Button();
                buttonSettings(buttonConfirmRestrictions, 300, 500, "Confirm Restrictions");
                root.getChildren().add(buttonConfirmRestrictions);

                buttonConfirmRestrictions.setOnMouseClicked(event1 -> {
                    setTextFields(results, hBoxResults, letterRestrictions.size());
                });
            }
        });

        /*
        buttonWordLengthConfirm.setOnMouseClicked(event -> {

            hBoxResults.getChildren().clear();
            results.clear();

            setTextFields(letterRestrictions, hBoxRestrictionsInput, (int)sliderWordLength.getValue());

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

            for (Button i: multiplierButtons){
                hBoxMultiplierButton.getChildren().add(i);
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

            Button buttonConfirmRestrictions = new Button();
            buttonSettings(buttonConfirmRestrictions, 300, 550, "Confirm Restrictions");
            root.getChildren().add(buttonConfirmRestrictions);

            buttonConfirmRestrictions.setOnMouseClicked(event1 -> {
                setTextFields(results, hBoxResults, letterRestrictions.size());
            });

        });

         */



        //root.getChildren().add(buttonWordLengthConfirm);
        root.getChildren().add(hBoxRestrictionsInput);
        root.getChildren().add(hBoxMultiplierButton);
        root.getChildren().add(hBoxResults);

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

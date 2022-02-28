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
                if (newValue.matches(" ")) {
                    i.setText(newValue.replaceAll(" ", ""));
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

    public HBox textSet(HBox hbox, Text text, int y, int size){
        hbox.setPrefSize(800, 50);
        hbox.setLayoutY(y);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(text);
        return hbox;
    }

    public HBox HBoxSettings(HBox hbox, int x, int y, int prefHeight){
        hbox.setPadding(new Insets(0, 12, 0, 12) );
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-colour: #336699; ");
        hbox.setPrefSize(700, prefHeight);
        hbox.setLayoutX(x);
        hbox.setLayoutY(y);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    public void buttonSettings(Button button, int x, int y, int prefWidth, int prefHeight){
        button.setPrefSize(prefWidth,prefHeight);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(5, 0, 5, 0));
    }

    public void sliderSettings(Slider slider){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setPrefSize(300,50);
    }

    public void setTextFieldEditState(ArrayList<TextField> textFields, boolean state){
        for(TextField i: textFields){
            i.setEditable(state);
        }
    }

    public int countTextFieldInputs(ArrayList<TextField> textFields){
        int count = 0;
        for(TextField i: textFields){
            if(!i.getText().equals("")){
                count++;
            }
        }
        return count;
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

        root.getChildren().add(textSet(new HBox(), new Text("Input Your Scrabble Letters"), 80, 15));

        //TODO Input Letters
        HBox hBoxLetterInput = new HBox ();
        HBoxSettings(hBoxLetterInput, 50, 125, 50);
        ArrayList<TextField> allLetters = new ArrayList<>();
        setTextFields(allLetters, hBoxLetterInput, 8);
        root.getChildren().add(hBoxLetterInput);

        //TODO Invalid Amount of Letters HBox
        HBox hBoxInvalidLetters = textSet(new HBox(), new Text("Invalid Amount of Scrabble Letters"), 400,25);

        //TODO Input Length of word

        HBox hBoxLengthInput = new HBox ();
        textSet(hBoxLengthInput, new Text("Input the Length of Desired Word:"), 0, 20);
        HBoxSettings(hBoxLengthInput, 50, 200, 100);
        Slider sliderWordLength = new Slider(2,15, 8);
        sliderSettings(sliderWordLength);
        hBoxLengthInput.getChildren().add(sliderWordLength);
        root.getChildren().add(hBoxLengthInput);

        //TODO Input Restrictions text HBox

        HBox hBoxRestrictionsText = new HBox();
        textSet(hBoxRestrictionsText, new Text("Input Your Restrictions"), 330, 15);


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

        //TODO Slider Result Rank

        HBox hBoxResultRankInput = new HBox ();
        ArrayList<String> stringResults = new ArrayList<>();

        //TODO Commit Restriction button
        Button buttonConfirmRestrictions = new Button("Find Words");
        buttonSettings(buttonConfirmRestrictions, 300, 500, 200, 50);

        //TODO Commit button for restrictions
        //Button buttonWordLengthConfirm = new Button();
        //buttonSettings(buttonWordLengthConfirm, 300, 300, "Confirm Word Length");

        //TODO Invalid Amount of Restrictions HBox
        HBox hBoxInvalidRestrictions  = textSet(new HBox(), new Text("Invalid Amount of Restrictions"), 625,25);

        sliderWordLength.valueProperty().addListener((observable, oldValue, newValue) ->{
            if (!Objects.equals(newValue.intValue(), oldValue.intValue())) {

                if(countTextFieldInputs(allLetters) == allLetters.size()) {

                    root.getChildren().remove(hBoxInvalidLetters);
                    root.getChildren().remove(hBoxResultRankInput);
                    hBoxResults.getChildren().clear();
                    results.clear();
                    root.getChildren().remove(hBoxRestrictionsText);

                    setTextFieldEditState(allLetters, false);



                    setTextFields(letterRestrictions, hBoxRestrictionsInput, (int) sliderWordLength.getValue());


                    root.getChildren().add(hBoxRestrictionsText);


                    hBoxMultiplierButton.getChildren().clear();
                    multiplierButtons.clear();

                    for (int i = 0; i < letterRestrictions.size(); i++) {
                        multiplierButtons.add(new Button("-"));
                    }

                    for (Button i : multiplierButtons) {
                        buttonSettings(i, 300, 500, 50, 20);
                    }

                    for (Button i : multiplierButtons) {
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

                    root.getChildren().remove(buttonConfirmRestrictions);
                    root.getChildren().add(buttonConfirmRestrictions);

                    buttonConfirmRestrictions.setOnMouseClicked(event1 -> {

                        if (!(letterRestrictions.size() > allLetters.size() && countTextFieldInputs(letterRestrictions) < letterRestrictions.size() - allLetters.size())) {
                            root.getChildren().remove(hBoxInvalidRestrictions);
                            hBoxResultRankInput.getChildren().clear();
                            stringResults.clear();

                            Score score = new Score();

                            int[] multiplier_list = new int[letterRestrictions.size()];
                            char[] characters = new char[allLetters.size()];
                            char[] restrictions = new char[letterRestrictions.size()];
                            int w_length = letterRestrictions.size();


                            for(int i = 0; i < letterRestrictions.size(); i++){
                                if (multiplierButtons.get(i).getText().equals("-")) {
                                    multiplier_list[i] = 1;
                                } else if (multiplierButtons.get(i).getText().equals("2X")) {
                                    multiplier_list[i] = 2;
                                } else if (multiplierButtons.get(i).getText().equals("3X")) {
                                    multiplier_list[i] = 3;
                                }
                            }

                            for(int i = 0; i < allLetters.size(); i++){
                                characters[i] = allLetters.get(i).getText().charAt(0);
                            }

                            for(int i = 0; i < letterRestrictions.size(); i++){
                                if (letterRestrictions.get(i).getText().length() == 0){
                                    restrictions[i] = '\0';
                                } else {
                                    restrictions[i] = letterRestrictions.get(i).getText().charAt(0);
                                }
                            }

                            ArrayList<String> other = score.sort_words(multiplier_list, characters, restrictions, w_length);
                            stringResults.addAll(other);

                            for (String i: stringResults){
                                System.out.println(i);
                            }

                            textSet(hBoxResultRankInput, new Text("Input Desired Word Rank:"), 0, 20);
                            HBoxSettings(hBoxResultRankInput, 50, 700, 50);

                            if(stringResults.size() != 0){
                                int maxSliderSize = Math.min(stringResults.size(), 5);

                                Slider sliderResultsRank = new Slider(1, maxSliderSize, 1);

                                sliderSettings(sliderResultsRank);

                                hBoxResultRankInput.getChildren().add(sliderResultsRank);

                                root.getChildren().remove(hBoxResultRankInput);
                                root.getChildren().add(hBoxResultRankInput);

                                setTextFields(results, hBoxResults, letterRestrictions.size());

                                setTextFieldEditState(letterRestrictions, false);
                                setTextFieldEditState(results, false);

                                for (int i = 0; i < stringResults.get(0).length(); i++) {
                                    results.get(i).setText(String.valueOf(stringResults.get(0).charAt(i)));
                                }

                                sliderResultsRank.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                                    if (!Objects.equals(newValue1.intValue(), oldValue1.intValue()) && sliderResultsRank.getMax() > 1) {
                                        int resultRank = newValue1.intValue() - 1;

                                        for (int i = 0; i < stringResults.get(resultRank).length(); i++) {
                                            results.get(i).setText(String.valueOf(stringResults.get(resultRank).charAt(i)));
                                        }
                                    }
                                });
                            } else {
                                root.getChildren().remove(hBoxInvalidRestrictions);
                                root.getChildren().add(hBoxInvalidRestrictions);
                            }

                        } else {
                            root.getChildren().remove(hBoxInvalidRestrictions);
                            root.getChildren().add(hBoxInvalidRestrictions);
                        }

                    });
                } else {
                    root.getChildren().remove(hBoxInvalidLetters);
                    root.getChildren().add(hBoxInvalidLetters);
                }


            }
        });

        root.getChildren().add(hBoxRestrictionsInput);
        root.getChildren().add(hBoxMultiplierButton);
        root.getChildren().add(hBoxResults);


        //TODO Reset button for restrictions
        Button buttonReset = new Button("Reset");
        buttonSettings(buttonReset, 670, 130, 80, 40);
        root.getChildren().add(buttonReset);

        buttonReset.setOnMouseClicked(event -> {

            for(TextField i: allLetters){
                i.setText("");
            }

            root.getChildren().remove(hBoxInvalidLetters);
            root.getChildren().remove(hBoxResultRankInput);
            hBoxResults.getChildren().clear();
            results.clear();
            root.getChildren().remove(hBoxRestrictionsText);
            root.getChildren().remove(buttonConfirmRestrictions);

            hBoxRestrictionsInput.getChildren().clear();
            letterRestrictions.clear();

            hBoxMultiplierButton.getChildren().clear();
            multiplierButtons.clear();

            root.getChildren().remove(hBoxInvalidRestrictions);

            root.getChildren().remove(hBoxInvalidLetters);

            setTextFieldEditState(allLetters, true);

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
        root.getChildren().add(buttonWordLengthConfirm);
         */


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
        stage.setTitle("Scrabble Assistant");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

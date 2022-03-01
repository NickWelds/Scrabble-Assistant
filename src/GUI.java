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


    /**
     * Adds the settings to each TextField, this includes size, font, padding, alignment, max length and acceptable characters
     * Got max length from first link
     * Got acceptable characters from second link
     * Used the internet as I was
     *
     * @param textFields ArrayList<TextField>
     * @link https://stackoverflow.com/questions/41107912/javafx-input-numbers-or-letters-only-and-allow-to-press-tab-escape-backspace-etc
     * @link https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
     */
    protected void textFieldSettings(ArrayList<TextField> textFields){
        for (TextField i: textFields){
            i.setPrefSize(50,50);

            i.setFont(Font.loadFont("file:scrabble-font" + File.separator + "Scramble-KVBe.ttf", 30));

            i.setPadding(new Insets(0,0,0,0));

            i.setAlignment(Pos.CENTER);

            //Makes the max length of the TextField 1 character long
            i.lengthProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (i.getText().length() >= 1) {
                        i.setText(i.getText().substring(0, 1));
                    }
                }
            });

            //Makes the TextField only accept a-z and A-Z
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


    /**
     * Adds # of TextFields and sets their settings and adds them to HBox
     *
     * @param textFields ArrayList<TextField>
     * @param hbox HBox
     * @param size Number of Text Fields to add
     */
    protected void setTextFields(ArrayList<TextField> textFields, HBox hbox, int size){
        //Clears HBox and arraylist
        hbox.getChildren().clear();
        textFields.clear();

        //Adds TextFields to ArrayList
        for(int i = 0; i < size; i++){
            textFields.add(new TextField());
        }

        //Sets Settings of all TextFields
        textFieldSettings(textFields);

        //Adds all TextFields to HBox
        for (TextField i: textFields){
            hbox.getChildren().add(i);
        }
    }

    /**
     * Creates HBox for basic titles of regular text
     *
     * @param hbox HBox
     * @param text Text
     * @param y position of text (HBox)
     * @param size of text
     * @return HBox
     */
    public HBox textSet(HBox hbox, Text text, int y, int size){
        hbox.setPrefSize(800, 50);
        hbox.setLayoutY(y);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, size));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(text);
        return hbox;
    }

    /**
     * Sets Settings for HBox
     *
     * @param hbox HBox
     * @param x position of HBox
     * @param y position of HBox
     * @param prefHeight of HBox
     * @return HBox
     */
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

    /**
     * Settings for buttons
     *
     * @param button Button
     * @param x position of Button
     * @param y position of Button
     * @param prefWidth of Button
     * @param prefHeight of Button
     */
    public void buttonSettings(Button button, int x, int y, int prefWidth, int prefHeight){
        button.setPrefSize(prefWidth,prefHeight);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(5, 0, 5, 0));
    }

    /**
     * Settings for Slider
     *
     * @param slider Slider
     */
    public void sliderSettings(Slider slider){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setBlockIncrement(1);
        slider.setPrefSize(300,50);
    }

    /**
     * Sets the edit status of all TextFields in Array List
     *
     * @param textFields ArrayList<TextField>
     * @param state of editable status
     */
    public void setTextFieldEditState(ArrayList<TextField> textFields, boolean state){
        for(TextField i: textFields){
            i.setEditable(state);
        }
    }

    /**
     * Counts and returns the amount of TextFields that contain text
     *
     * @param textFields ArrayList<TextField>
     * @return int
     */
    public int countTextFieldInputs(ArrayList<TextField> textFields){
        int count = 0;
        for(TextField i: textFields){
            if(!i.getText().equals("")){
                count++;
            }
        }
        return count;
    }

    /**
     * Adds and Removes HBox from root to avoid overlapping objects
     *
     * @param root Group
     * @param hBox HBox
     */
    public void removeAddHBox (Group root, HBox hBox){
        root.getChildren().remove(hBox);
        root.getChildren().add(hBox);
    }


    //Creates dimension of the window
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    //Number of Restrictions
    public int numOfRestrictions = 7;

    /**
     * Runs all the Graphical Process of the code
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        //Creates root and scene
        Group root = new Group();
        Scene scene = new Scene(root);

        //Creates new Canvas and sets width and height
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Prompt for User to input scrabble letters
        root.getChildren().add(textSet(new HBox(), new Text("Input Your Scrabble Letters"), 80, 15));

        //HBox for all the users scrabble letters
        HBox hBoxLetterInput = new HBox ();
        HBoxSettings(hBoxLetterInput, 50, 125, 50);
        ArrayList<TextField> allLetters = new ArrayList<>();
        setTextFields(allLetters, hBoxLetterInput, 8);
        root.getChildren().add(hBoxLetterInput);

        //Prompt for when user does not include all the letters they have
        HBox hBoxInvalidLetters = textSet(new HBox(), new Text("Invalid Amount of Scrabble Letters"), 400,25);

        //HBox for the Length of word prompt and slider
        HBox hBoxLengthInput = new HBox ();
        textSet(hBoxLengthInput, new Text("Input the Length of Desired Word:"), 0, 20);
        HBoxSettings(hBoxLengthInput, 50, 200, 100);
        Slider sliderWordLength = new Slider(2,15, 8);
        sliderSettings(sliderWordLength);
        hBoxLengthInput.getChildren().add(sliderWordLength);
        root.getChildren().add(hBoxLengthInput);

        //Prompt for User to input their restrictions
        HBox hBoxRestrictionsText = new HBox();
        textSet(hBoxRestrictionsText, new Text("Input Your Restrictions"), 330, 15);

        //All variables for letter restrictions
        HBox hBoxRestrictionsInput = new HBox ();
        HBoxSettings(hBoxRestrictionsInput, 50, 375, 50);
        ArrayList<TextField> letterRestrictions = new ArrayList<>();

        //All variables for Multiplier Button HBox
        HBox hBoxMultiplierButton = new HBox ();
        HBoxSettings(hBoxMultiplierButton, 50, 435, 20);
        ArrayList<Button> multiplierButtons = new ArrayList<>();

        //All variables for final result
        HBox hBoxResults = new HBox ();
        HBoxSettings(hBoxResults, 50, 600, 50);
        ArrayList<TextField> results = new ArrayList<>();

        //All variables for Result Rank Input
        HBox hBoxResultRankInput = new HBox ();
        ArrayList<String> stringResults = new ArrayList<>();

        //Button for program to find the best words
        Button buttonConfirmRestrictions = new Button("Find Words");
        buttonSettings(buttonConfirmRestrictions, 300, 500, 200, 50);

        //Prompt for Invalid Restrictions
        HBox hBoxInvalidRestrictions  = textSet(new HBox(), new Text("Invalid Amount of Restrictions"), 625,25);

        //Prompt for Invalid Restrictions
        HBox hBoxNoResults  = textSet(new HBox(), new Text("No Matches Found"), 625,25);

        //Updates everytime the word length slider changes value
        sliderWordLength.valueProperty().addListener((observable, oldValue, newValue) ->{
            if (!Objects.equals(newValue.intValue(), oldValue.intValue())) {


                //Checks if the user has input all of their scrabble letters
                if(countTextFieldInputs(allLetters) == allLetters.size()) {

                    //Removes HBoxes and clears arraylists/HBox to avoid duplicates
                    root.getChildren().remove(hBoxInvalidLetters);
                    root.getChildren().remove(hBoxResultRankInput);
                    hBoxResults.getChildren().clear();
                    results.clear();
                    root.getChildren().remove(hBoxRestrictionsText);

                    //Sets users scrabble letters input uneditable
                    setTextFieldEditState(allLetters, false);

                    //Loads and adds letterRestrictions to hBoxRestrictionsInput
                    setTextFields(letterRestrictions, hBoxRestrictionsInput, (int) sliderWordLength.getValue());

                    root.getChildren().add(hBoxRestrictionsText);

                    //Clears multiplierButton Arraylist and HBox
                    hBoxMultiplierButton.getChildren().clear();
                    multiplierButtons.clear();

                    //Add Buttons to multiplierButtons with "-" as the text
                    for (int i = 0; i < letterRestrictions.size(); i++) {
                        multiplierButtons.add(new Button("-"));
                    }

                    //Adds button settings to multiplierButtons
                    for (Button i : multiplierButtons) {
                        buttonSettings(i, 300, 500, 50, 20);
                    }

                    //Adds all multiplierButtons to hBoxMultiplierButton
                    for (Button i : multiplierButtons) {
                        hBoxMultiplierButton.getChildren().add(i);
                    }

                    //Edits the text of the button based on how many times it is pressed
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

                    //Removes and adds buttonConfirmRestrictions to root
                    root.getChildren().remove(buttonConfirmRestrictions);
                    root.getChildren().add(buttonConfirmRestrictions);

                    //When buttonConfirmRestrictions is clicked
                    buttonConfirmRestrictions.setOnMouseClicked(event1 -> {

                        //Checks the amount of letter Restrictions is larger than the 8 characters the user has
                        //If amount of letter Restrictions is larger than 8, makes the user input extra letters until
                        //The amount of restrictions they added + the amount of scrabble letters they have is larger
                        //or equal to their desired length of word
                        if (!(letterRestrictions.size() > allLetters.size() && countTextFieldInputs(letterRestrictions) < letterRestrictions.size() - allLetters.size())) {
                            //Removes HBoxes and clears arraylists/HBox to avoid duplicates
                            root.getChildren().remove(hBoxInvalidRestrictions);
                            hBoxResultRankInput.getChildren().clear();
                            stringResults.clear();

                            Score score = new Score();

                            //All variables to comply with Score's Parameter Lists
                            int[] multiplier_list = new int[letterRestrictions.size()];
                            char[] characters = new char[allLetters.size()];
                            char[] restrictions = new char[letterRestrictions.size()];
                            int w_length = letterRestrictions.size();

                            //Converts button text to integers for multipliers
                            for(int i = 0; i < letterRestrictions.size(); i++){
                                if (multiplierButtons.get(i).getText().equals("-")) {
                                    multiplier_list[i] = 1;
                                } else if (multiplierButtons.get(i).getText().equals("2X")) {
                                    multiplier_list[i] = 2;
                                } else if (multiplierButtons.get(i).getText().equals("3X")) {
                                    multiplier_list[i] = 3;
                                }
                            }

                            //Gets Character of all scrabble letter inputs
                            for(int i = 0; i < allLetters.size(); i++){
                                characters[i] = allLetters.get(i).getText().charAt(0);
                            }

                            //Gets Character of all letter restrictions inputs
                            for(int i = 0; i < letterRestrictions.size(); i++){
                                if (letterRestrictions.get(i).getText().length() == 0){
                                    restrictions[i] = '\0';
                                } else {
                                    restrictions[i] = letterRestrictions.get(i).getText().charAt(0);
                                }
                            }

                            //Gets the top 5 results
                            stringResults.addAll(score.sort_words(multiplier_list, characters, restrictions, w_length));

                            //Sets Desired word rank text and adds settings to HBox
                            textSet(hBoxResultRankInput, new Text("Input Desired Word Rank:"), 0, 20);
                            HBoxSettings(hBoxResultRankInput, 50, 700, 50);

                            //Checks of there are any results is not equal to zero
                            if(stringResults.size() != 0){
                                //Gets amount of stringResults if under 5
                                int maxSliderSize = Math.min(stringResults.size(), 5);

                                //Creates slider
                                Slider sliderResultsRank = new Slider(1, maxSliderSize, 1);

                                sliderSettings(sliderResultsRank);

                                hBoxResultRankInput.getChildren().add(sliderResultsRank);

                                removeAddHBox(root, hBoxResultRankInput);

                                setTextFields(results, hBoxResults, stringResults.get(0).length());
                                setTextFieldEditState(letterRestrictions, false);
                                setTextFieldEditState(results, false);

                                for (int i = 0; i < stringResults.get(0).length(); i++) {
                                    results.get(i).setText(String.valueOf(stringResults.get(0).charAt(i)));
                                }

                                sliderResultsRank.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                                    if (!Objects.equals(newValue1.intValue(), oldValue1.intValue()) && sliderResultsRank.getMax() > 1) {

                                        //Gets rank relative to arraylist index
                                        int resultRank = newValue1.intValue() - 1;

                                        setTextFields(results, hBoxResults, stringResults.get(resultRank).length());
                                        setTextFieldEditState(letterRestrictions, false);
                                        setTextFieldEditState(results, false);

                                        //Clears all TextField Texts
                                        for(TextField i: results){
                                            i.setText("");
                                        }

                                        //Sets each text field to the
                                        for (int i = 0; i < stringResults.get(resultRank).length(); i++) {
                                            results.get(i).setText(String.valueOf(stringResults.get(resultRank).charAt(i)));
                                        }
                                    }
                                });
                            } else {
                                //Prompts user that no words were found for letters and restrictions
                                removeAddHBox(root, hBoxNoResults);
                            }
                        } else {
                            //Prompts user that their amount of restrictions is invalid
                            removeAddHBox(root, hBoxInvalidRestrictions);
                        }
                    });
                } else {
                    //Prompts user that they haven't filled out all their letters
                    removeAddHBox(root, hBoxInvalidLetters);
                }
            }
        });

        //Adds HBoxes to root
        root.getChildren().add(hBoxRestrictionsInput);
        root.getChildren().add(hBoxMultiplierButton);
        root.getChildren().add(hBoxResults);


        //All variable for Reset Button
        Button buttonReset = new Button("Reset");
        buttonSettings(buttonReset, 670, 130, 80, 40);
        root.getChildren().add(buttonReset);

        //When Reset button is pressed
        buttonReset.setOnMouseClicked(event -> {

            //Clears all scrabble input
            for(TextField i: allLetters){
                i.setText("");
            }

            //Sets all scrabble letters editable
            setTextFieldEditState(allLetters, true);

            //Removes everything on screen past the word length input
            //Clears arraylists and HBoxes
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
        });

        //Sets background of program to Official Scrabble Red
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

                    //Updates value numOfRestrictions from slider
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

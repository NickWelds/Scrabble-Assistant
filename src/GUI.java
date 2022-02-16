/**
 * Main
 * @author: Nicholas Milin
 *
 * This class represents the Display of the Firework Simulation.
 * Users will be able to use the space bar to launch fireworks wherever their mouse is located.
 * At the bottom of the window, there is an area with controls for the firework which the user
 * can use to change the colour of the firework, the type of firework and clear all fireworks.
 *
 */

//Imports all necessary packages to run the Main class
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Duration;


public class GUI extends Application {

    /**
     * Creates and fills in rectangles which are used for the background,
     * controls and the colour selector preview.
     *
     * @param gc GraphicsContext
     */
    public void sceneColourFill(GraphicsContext gc){
        //Fills background of the simulation area semi transparent black
        gc.setFill(new Color(0,0,0,0.1));
        gc.fillRect(0, 0, WIDTH, 600);

        //Bottom of the Canvas which contains the controls
        gc.setFill(Color.GREY);
        gc.fillRect(0, 600, WIDTH, 100);

        //Visual Box for the colour of the firework
        gc.setFill((new Color(redValue, greenValue, blueValue,1)));
        gc.fillRect(12.5, 612.5, 75, 75);
    }


    //Creates dimension of the window
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    //Initial Mouse X and Y position
    public double mouseX = 500;
    public double mouseY = 500;

    //Colour of the firework
    protected static double redValue = 1;
    protected static double blueValue = 1;
    protected static double greenValue = 1;

    //Type of firework being launched
    protected static int fireworkType = 0;

    /**
     * Processes and adds everything to the scene
     *
     * @param stage stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Creates root and scene
        Group root = new Group();
        Scene scene = new Scene(root);

        //Creates new Canvas and sets width and height
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        sceneColourFill(gc);

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

                    /**
                     * Displays everything on the screen.
                     *
                     * @param event ActionEvent
                     */
                    @Override
                    public void handle(ActionEvent event){

                        GraphicsContext gc = canvas.getGraphicsContext2D();

                        //Clears the controller section at the bottom of the Scene
                        gc.clearRect(0, 601, WIDTH, 100);

                        //Repaints the scene
                        sceneColourFill(gc);

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


package Bomberman.Display;

import Bomberman.Map.Map;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display { 
    
    private int width;
    private int height;
    private final String title;
    private GraphicsContext gc;
    private Canvas canvas;
    private Stage stage;
    private Scene scene;
    
    private Map map;
    
    public Display(Stage stage ,String title ,int width, int height){
        this.stage = stage;
        this.width = width;
        this.height = height;
        this.title = title;
        Rectangle rect1;
        // Create the Canvas
        this. canvas = new Canvas(this.width, this.height);
        // Set the width of the Canvas
        canvas.setWidth(this.width);
        // Set the height of the Canvas
        canvas.setHeight(this.height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Create the Pane
        Pane root = new Pane();
        // Add the Canvas to the Pane
        
        root.getChildren().add(this.canvas);

        // Create the Scene
        this.scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle(this.title);
        
        //stage.show();
    }
    
    public Stage getStage(){
        return this.stage;
    }
    
    
        
    public void Launch(Stage stage)
    {
        stage.show();
    }
    
    public void Close(Stage stage){
        stage.close();
    }
    
    public Canvas getCanvas(){
        return this.canvas;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public void onKeyPress(EventHandler<KeyEvent> handler) {
        this.stage.getScene().setOnKeyPressed(handler);
    }
    
    public void onKeyRelease(EventHandler<KeyEvent> handler) {
        this.stage.getScene().setOnKeyReleased(handler);
    }
   
}

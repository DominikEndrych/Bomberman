
package Bomberman;

import Bomberman.Display.Display;
import Bomberman.Game.Game;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class MainApp extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) throws IOException
    { 
        Pane menuPane = (Pane) FXMLLoader.load(MainApp.class.getResource("../Menu.fxml"));
        stage.setScene(new Scene(menuPane));
        stage.show();
        /*
        Display display = new Display(stage ,"Bomberman", 720, 720);
        
        Game game = new Game(display);
        
        game.startGame();
        */
        
    }
}

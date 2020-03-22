/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import Bomberman.Game.Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class MenuController {
    
    @FXML
    private Slider enemiesCounter;
    
    @FXML
    void playButtonPressed(ActionEvent event) throws FileNotFoundException {
        
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
        
        Display display = new Display(new Stage() ,"Bomberman", 720, 720);
        Game game = new Game(display, (int)enemiesCounter.getValue());
        game.startGame();
    }
    
    @FXML
    void aboutButtonPressed(ActionEvent event){
        //System.out.println("About");
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About");
        Text aboutText = new Text();
        aboutText.setText("Bomberman \n END0013");
        
        StackPane layout = new StackPane();
        layout.getChildren().add(aboutText);
        Scene aboutScene = new Scene(layout, 220,150);
        aboutStage.setScene(aboutScene);
        aboutStage.show();
    }
    
    @FXML
    void settingsButtonPressed() throws IOException{
        File configFile = new File("config.txt");
        java.awt.Desktop.getDesktop().edit(configFile);
    }
    

}


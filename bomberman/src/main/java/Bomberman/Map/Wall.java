/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public class Wall extends Tile{
    
    private String src;
    
    public Wall(int x, int y){
        
        this.dimension = 80;
        this.src = "../../groundTile.png";
        
        //chceme aby se cihly nepøekrývaly      [0,0] -> [40,40]
        this.x = x;
        this.y = y;
        //souøadnice v matici
    }
    
    @Override
    public void draw(GraphicsContext gc){
        Image groundImage = new Image(getClass().getResourceAsStream(this.src));
        gc.drawImage(
                groundImage,
                (this.x * this.dimension),
                (this.y * this.dimension)
        );
    }
    
}

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
 * @author endry
 */
public class Exit extends Tile {
    private final String src;
    private final int dimension;
    
    public Exit(int x, int y){
        this.x = x;
        this.y = y;
        this.dimension = 80;
        this.src = "../../exit_placeholder.png";
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

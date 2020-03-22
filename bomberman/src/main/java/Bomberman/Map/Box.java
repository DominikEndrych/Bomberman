/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Map;

import Bomberman.Interfaces.Destroyable;
import Bomberman.Interfaces.Explodable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public class Box extends Tile implements Destroyable {
    
    private final String src;
    
    public Box(int x, int y){
        
        this.dimension = 80;
        this.src = "../../boxPlaceholder.png";
        
        //chceme aby se cihly nepøekrývaly      [0,0] -> [40,40]
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean isHit(Explodable bomb){
        if((bomb.getX() / 80) + 1 == this.x && (bomb.getY() / 80) == this.y){
            return true;
        }
        else if((bomb.getX() / 80) - 1 == this.x && (bomb.getY() / 80) == this.y){
            return true;
        }
        else if((bomb.getX() / 80) == this.x && (bomb.getY() / 80) + 1 == this.y){
            return true;
        }
        else if((bomb.getX() / 80) == this.x && (bomb.getY() / 80) - 1 == this.y){
            return true;
        }
        else
            return false;
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.GameObjects;

import Bomberman.Interfaces.Drawable;
import Bomberman.Interfaces.Explodable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */

public class Bomb implements Explodable{
    
    private int x;
    private int y;
    
    private int time;
    
    private final String src;
    
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
        this.src = "../../bomb_placeholder.png";
        
        this.time = 55;
    }
    
    @Override
    public void draw(GraphicsContext gc){
        Image enemyImage = new Image(getClass().getResourceAsStream(this.src));
        gc.drawImage(
                enemyImage,
                this.x - 25,
                this.y - 25
        );
    }
    
    @Override
    public void tick(){
        if(this.time > 0){
            this.time--;
            //System.out.println();
        }
    }
    
    @Override
    public boolean exploded(){
        if(this.time == 0)
            return true;
        else
            return false;
    }
    
    @Override
    public int getX(){
        return this.x;
    }
    
    @Override
    public int getY(){
        return this.y;
    }
    
    
}

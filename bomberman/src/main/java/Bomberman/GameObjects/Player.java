/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.GameObjects;

import Bomberman.Interfaces.Movable;
import Bomberman.Interfaces.Destroyable;
import Bomberman.Interfaces.Explodable;
import Bomberman.Interfaces.Killable;
import Bomberman.Map.Map;
import java.util.Collection;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public class Player implements Killable {
    
    private final int playerDimension;
    
    private int x;
    private int y;
    private int speed;
    private final String src;
    
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.speed = 6;
        this.playerDimension = 60;
        this.src = "../../playerPlaceholder.png";
    }
    
    @Override
    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }
    
    
    @Override
    public boolean isHit(Explodable bomb){
        
        if(bomb.getX() / 80 == this.x / 80 && bomb.getY() / 80 == this.y / 80){
            return true;
        }
        else if((bomb.getX() / 80) + 1 == this.x / 80 && bomb.getY() / 80 == this.y / 80){
            return true;
        }
        else if((bomb.getX() / 80) - 1 == this.x / 80 && bomb.getY() / 80 ==  this.y / 80){
            return true;
        }
        else if(bomb.getX() / 80 == this.x / 80 && (bomb.getY() / 80) + 1 == this.y / 80){
            return true;
        }
        else if(bomb.getX() / 80 == this.x / 80 && (bomb.getY() / 80) - 1 == this.y / 80){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean isHitByEnemy(Enemy enemy){
        if(enemy.getX() / 80 == this.x / 80 && enemy.getY() / 80 == this.y / 80){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void die(){}
    
    @Override
    public void draw(GraphicsContext gc){
        Image groundImage = new Image(getClass().getResourceAsStream(this.src));
        gc.drawImage(
                groundImage,
                this.x - 30,
                this.y - 30
        );
    }
    
    public void goUp(int displayWidth, int displayHeight, Map map){
        
        if( this.y - this.playerDimension/2 - 2 <= 0 )
            return;
        else if(map.checkMatrix((this.y - 30 - 3) / 80, this.x / 80) == 0){
            if(map.checkMatrix((this.y - 30 - 3) / 80,(this.x + 30) / 80) == 0 && map.checkMatrix((this.y - 30 - 3) / 80,(this.x - 30) / 80) == 0)
              this.move(0, -this.speed);  
        }
    }
    
    public void goDown(int displayWidth, int displayHeight, Map map){
        
        if( this.y + this.playerDimension/2 + 2 >= displayHeight )
            return;
        else if(map.checkMatrix((this.y + 30 + 2) / 80, this.x / 80) == 0){
            if(map.checkMatrix((this.y + 30 + 2) / 80,(this.x + 30) / 80) == 0 && map.checkMatrix((this.y + 30 + 2) / 80,(this.x - 30) / 80) == 0)
              this.move(0, this.speed);  
        }
    }
    
    public void goRight(int displayWidth, int displayHeight, Map map){

        if( this.x + this.playerDimension/2 + 2 >= displayWidth )
            return;
        
        else if(map.checkMatrix(this.y / 80, (this.x + 30 + 2) / 80) == 0){
            if(map.checkMatrix((this.y + 30) / 80,(this.x + 30 + 2) / 80) == 0 && map.checkMatrix((this.y - 30) / 80,(this.x + 30 + 2) / 80) == 0 )
              this.move(this.speed, 0);  
        }     
    }
    
    public void goLeft(int displayWidth, int displayHeight, Map map){
        
        if( this.x - this.playerDimension/2 - 2 <= 0 )
            return;
  
        else if(map.checkMatrix(this.y / 80, (this.x - 30 - 3) / 80) == 0){
            if(map.checkMatrix((this.y + 30) / 80,(this.x - 30 - 3) / 80) == 0 && map.checkMatrix((this.y - 30) / 80,(this.x - 30 - 3) / 80) == 0 )
              this.move(-this.speed, 0);  
        } 
        
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}

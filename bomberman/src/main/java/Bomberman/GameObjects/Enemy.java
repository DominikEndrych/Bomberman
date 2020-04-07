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
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public class Enemy implements Killable {
    
    private int x;
    private int y;
    private String src;
    
    private int osa;
    private int smer;
    
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.src = "../../enemyPlaceholder.png";
        
        this.osa = -1;
        this.smer = -1;
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
    
    @Override
    public void draw(GraphicsContext gc){
        Image enemyImage = new Image(getClass().getResourceAsStream(this.src));
        gc.drawImage(
                enemyImage,
                this.x - 40,
                this.y - 40
        );
    }
    
    @Override
    public void die(){}
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    private boolean canMoveHere(int x, int y, int displayWidth, int displayHeight, Map map){
        
        //check if enemy is not out of canvas
        if(this.getX() + x - 40 < 0 || this.getX() + x + 40 >= displayWidth || this.getY() + y - 40 < 0 || this.getY() + y + 40 >= displayHeight){
            return false;
        }
        
        //moivng up
        if(x != 0 && x < 0){
            if(map.checkMatrix((this.getY() + y) / 80, (this.getX() + x - 40) / 80) < 1){
                return true;
            }
            else{
                return false;
            } 
        }
        
        //moving down
        if(x != 0 && x > 0){
            if(map.checkMatrix((this.getY() + y) / 80, (this.getX() + x + 40) / 80) < 1){
                return true;
            }
            else{
                return false;
            } 
        }
        
        //moving left
        if(y != 0 && y < 0){
            if(map.checkMatrix((this.getY() + y - 40) / 80, (this.getX() + x) / 80) < 1){
                return true;
            }
            else{
                return false;
            } 
        }
        
        //moving right
        if(y != 0 && y > 0){
            if(map.checkMatrix((this.getY() + y + 40) / 80, (this.getX() + x) / 80) < 1){
                return true;
            }
            else{
                return false;
            } 
        }
        else
            return false;
    }
    
    
    public void moveEnemy(int displayWidth, int dispalyHeight, Map map){

        boolean onStred;
        
            Random rand = new Random();
            onStred = false;
            
            //sledujeme, jestli je enemy na rozcestí
            if( (this.getX() - 40) % 80 == 0 && (this.getY() - 40) % 80 == 0){
                osa = rand.nextInt(2);
                smer = rand.nextInt(2);
                //System.out.println("Stred");
                onStred = true;
            }
            
            if(osa == 0 && smer == 0){
                if(this.canMoveHere(-2, 0, displayWidth, dispalyHeight, map))
                    this.move(-2, 0);
                else if(!onStred){
                    this.move(-1, 0);
                }
            }
            else if(osa == 0 && smer == 1){
                if(this.canMoveHere(2, 0, displayWidth, dispalyHeight, map))
                    this.move(2, 0);
                else if(!onStred){
                    this.move(1, 0);
                }
            }
            else if(osa == 1 && smer == 0){
                if(this.canMoveHere(0, -2, displayWidth, dispalyHeight, map))
                    this.move(0, -2);
                else if(!onStred){
                    this.move(0, -1);
                }
            }
            else if(osa == 1 && smer == 1){
                if(this.canMoveHere(0, 2, displayWidth, dispalyHeight, map))
                    this.move(0, 2);
                else if(!onStred){
                    this.move(0, 1);
                }
            }
    }
 
}

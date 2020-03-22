/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Map;

import Bomberman.Interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dominik
 */
public class Map implements Drawable{
    
    private final GraphicsContext gc;
    private int Matrix[][];
    private final int rows;
    private final int cols;
    
    public Map(GraphicsContext gc){
        this.gc = gc;
        
        this.rows = 9;
        this.cols = 9;
        
        /*
            metrix mapy
            0 = walkable ground, 1 = wall, 2 = box
        */
        this.Matrix = new int[][]{
            new int[]{0,0,0,0,0,0,0,0,0,},
            new int[]{0,1,0,1,0,1,0,1,0,},
            new int[]{0,0,0,0,0,0,0,0,0,},
            new int[]{0,1,0,1,0,1,0,1,0,},
            new int[]{0,0,0,0,0,0,0,0,0,},
            new int[]{0,1,0,1,0,1,0,1,0,},
            new int[]{0,0,0,0,0,0,0,0,0,},
            new int[]{0,1,0,1,0,1,0,1,0,},
            new int[]{0,0,0,0,0,0,0,0,0,}
        };
    }
    
    public int checkMatrix(int i, int j){
        return this.Matrix[i][j];
    }
    
    public boolean addBox(int i, int j){
        if(this.Matrix[j][i] == 0){
            this.Matrix[j][i] = 2;
            return true;
        }
        else
            return false;
    }
    
    public void removeBox(int i, int j){
        this.Matrix[j][i] = 0;
    }
    
    public void clearMap(){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                if(this.Matrix[i][j] != 1){
                    this.Matrix[i][j] = 0;
                }
            }
        }
    }
    
    public int getRows(){
        return this.rows;
    }
    
    public int getCols(){
        return this.cols;
    }
    
    @Override
    public void draw(GraphicsContext gc){
        
        Image groundPlane = new Image(getClass().getResourceAsStream("../../groundPlane.png"));
        gc.drawImage(groundPlane,0,0);
        
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                if(this.Matrix[i][j] == 1){
                    
                    //souøadnice urèitì matice
                    Wall wall = new Wall(j,i);
                    wall.draw(this.gc);
                    //kontrola
                    //System.out.println(ground.mx + " " + ground.my);
                    
                }
            }
        }
    }

    
    
    
}

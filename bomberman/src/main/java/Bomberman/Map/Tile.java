/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Map;

import Bomberman.Interfaces.Drawable;

/**
 *
 * @author Dominik
 */
public abstract class Tile implements Drawable {
    
    protected int x;
    protected int y;
    protected int dimension;
    
    //protected int mx;   //X v matici
    //protected int my;   //Y v matici
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getDimension(){
        return this.dimension;
    }
    
    /*
    public int getMX(){
        return this.mx;
    }
    
    public int getMY(){
        return this.my;
    }
    */
}

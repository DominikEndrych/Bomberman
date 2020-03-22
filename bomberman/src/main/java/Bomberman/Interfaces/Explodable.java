/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Interfaces;

/**
 *
 * @author Dominik
 */
public interface Explodable extends Drawable{
    void tick();
    boolean exploded();
    int getX();
    int getY();
}

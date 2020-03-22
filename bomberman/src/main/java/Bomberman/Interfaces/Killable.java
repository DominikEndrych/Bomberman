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
public interface Killable extends Destroyable, Movable{
    void die();
}

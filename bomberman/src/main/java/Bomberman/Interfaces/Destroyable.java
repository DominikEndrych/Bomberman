/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman.Interfaces;
import Bomberman.GameObjects.Bomb;

/**
 *
 * @author Dominik
 */
public interface Destroyable {
    boolean isHit(Explodable explodable);
}

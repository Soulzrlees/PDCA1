/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fatehbhular
 */
package action;
import entity.Entity;

public interface Action_Interface {
    // attack function for the player and enemy
    void attack(Entity user, Entity target, int round);
    // movement functions for player and enemy
    void moveForward(Entity entityattacker, Entity entity);

    void moveBackward(Entity entityattacker, Entity entity);
    // heal function for player and enemy
    void heal(Entity entity);

}


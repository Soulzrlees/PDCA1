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
    
    void attack(Entity entity);

    void moveForward(Entity entityattacker, Entity entity);

    void moveBackward(Entity entityattacker, Entity entity);

    void heal(Entity entity);

    boolean evadetrue(Entity entity);
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Shawn lee
 */
public class BattleInterfaceAction implements ActionListener {
    private final BattleButtonsPanel BattleButtonsPanel;
    private final BattleController battleController;

    public BattleInterfaceAction(BattleController battleController, BattleButtonsPanel BattleButtonsPanel) {
        this.battleController = battleController;
        this.BattleButtonsPanel = BattleButtonsPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BattleButtonsPanel.getAttackButton()){ //If the attack button is clicked
            battleController.playerAttack();
        }
        else if(e.getSource() == BattleButtonsPanel.getHealButton()){ //If the heal button is clicked
            battleController.playerHeal();
        }
        else if(e.getSource() == BattleButtonsPanel.getMoveForwardButton()){ //If the move forward button is clicked
            battleController.playerMoveForward();
        }
        else{
            battleController.playerMoveBackward(); //If the move backward button is clicked
        }
    }
}

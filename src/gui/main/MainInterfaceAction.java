/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.main;
import gui.battle.BattleInterface;
import entity.Player;
import entity.PlayerStats;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Shawn lee
 */
public class MainInterfaceAction implements ActionListener {
    // Add in the main interface and menu interface for the actions
    private final MenuPanel menu;
    private final MainInterface mainInterface;

    public MainInterfaceAction(MenuPanel menu, MainInterface mainInterface) {
        this.menu = menu;
        this.mainInterface = mainInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == menu.getInventoryButton()) {
            // TODO: Inventory logic
        } else if (source == menu.getBattleButton()) {
            mainInterface.dispose();
            new BattleInterface(mainInterface.getPlayer(), mainInterface.getPlayerStats()).createBattleInterface(mainInterface.getPlayer(), mainInterface.getPlayerStats());
        } else if (source == menu.getStatsButton()) {
            mainInterface.toggleStatsPanel();
        } else if (source == menu.getExitButton()) {
            System.exit(0);
        }
    }
}
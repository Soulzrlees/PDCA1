/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.main;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Shawn lee
 */
public class BackgroundPanel extends JPanel {

    private final Image backgroundImage;
    private final Image characterImage;
    private final Image logo;
    private int yOffset = 0;
    private int dy = 2;
    private final int maxBounce = 20;

    public BackgroundPanel(String backgroundPath, String characterPath, String LogoPath) {
        backgroundImage = new ImageIcon(backgroundPath).getImage();
        characterImage = new ImageIcon(characterPath).getImage();   
        logo = new ImageIcon(LogoPath).getImage();

        Timer timer = new Timer(30, e -> updateBounce());
        timer.start();
    }

    @Override
    //Paint the background image, character image and the logo onto the background panel on main interface
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, this);

        int charWidth = panelWidth / 4;
        int charHeight = panelHeight / 2;
        int x = (panelWidth - charWidth) / 2;
        int y = (panelHeight - charHeight) / 2 + panelHeight / 10 + yOffset;

        g.drawImage(characterImage, x, y, charWidth, charHeight, this);
        
        int logoWidth = panelWidth / 3;
        int logoHeight = panelHeight / 4;
        int logoX = panelWidth / 2 - logoWidth / 2;   // center horizontally
        int logoY = panelHeight / 20;                 // near top
        g.drawImage(logo, logoX, logoY, logoWidth, logoHeight, this);
        
    }

    //The Animation for the character moving vertically down and up
    private void updateBounce() {
        yOffset += dy;
        if (yOffset > maxBounce || yOffset < -maxBounce) dy = -dy;
        repaint();
    }
}

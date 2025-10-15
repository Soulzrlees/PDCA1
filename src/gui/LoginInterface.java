package gui;

import javax.swing.*;
import java.awt.*;

public class LoginInterface {
    JTextField TextField1, TextField2;
    JButton Button1, Button2;
    JComboBox ComboBox;
    JFrame frame;
    JPanel loginPanel;
    String[] classes = {"range", "melee", "mage"};

    public LoginInterface() {
        frame = new JFrame("Login");
        createLoginInterface();
    }

    private void createLoginInterface() {
        // === Frame setup ===
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(new GridBagLayout()); // center panel in frame

        // === Panel setup ===
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.GRAY);
        loginPanel.setPreferredSize(new Dimension(400, 700));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === Add components to panel ===
        addLabel(loginPanel, "Login", 0, 0, 2, 30);

        addLabel(loginPanel, "Name :", 0, 1);
        TextField1 = addTextField(loginPanel, 1, 1, 15);

        Button1 = addButton(loginPanel, null, 0, 2, 2);

        addLabel(loginPanel, "New Account", 0, 3, 2, 30);

        addLabel(loginPanel, "Name :", 0, 4);
        TextField2 = addTextField(loginPanel, 1, 4, 15);

        addLabel(loginPanel, "Class:", 0, 5);
        ComboBox = addComboBox(loginPanel, 1, 5);

        Button2 = addButton(loginPanel, null, 0, 6, 2);

        // === Add panel to frame ===
        frame.add(loginPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        LoginInterfaceAction listener = new LoginInterfaceAction(this);
        Button1.addActionListener(listener);
        Button2.addActionListener(listener);
    }


    private void addLabel(JPanel panel, String text, int x, int y) {
        addLabel(panel, text, x, y, 1, 25);
    }

    private void addLabel(JPanel panel, String text, int x, int y, int gridWidth, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 10, 25, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(label, gbc);
    }

    private JTextField addTextField(JPanel panel, int x, int y, int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Monospaced", Font.PLAIN, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(textField, gbc);
        return textField;
    }

    private JButton addButton(JPanel panel, String text, int x, int y, int gridWidth) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 20));
        button.setBackground(Color.GREEN);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(button, gbc);
        return button;
    }

    private JComboBox addComboBox(JPanel panel, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(classes);
        comboBox.setFont(new Font("Monospaced", Font.PLAIN, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(comboBox, gbc);
        return comboBox;
    }

    // Getters for action listener
    public JButton getButton1() { return Button1; }
    public JButton getButton2() { return Button2; }
    public JTextField getTextField1() { return TextField1; }
    public JTextField getTextField2() { return TextField2; }
    public String getSelectedComboBox() { return (String) ComboBox.getSelectedItem(); }
    
    
    public static void main(String[] args) {
        new LoginInterface();
    }
}

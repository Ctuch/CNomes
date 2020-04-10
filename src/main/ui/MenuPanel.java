package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class MenuPanel extends JPanel{

    //MODIFIES: this
    //EFFECTS: creates a menu label with text to display on the panel
    protected void createMenuLabel(String text) {
        JLabel mainMenu = new JLabel(text);
        mainMenu.setPreferredSize(new Dimension(800,
                100));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setHorizontalAlignment(JLabel.CENTER);
        add(mainMenu);
    }

    //MODIFIES: this
    //EFFECTS: adds buttons to this
    protected void createMenuButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            add(button);
        }
    }

    /**
     * @param buttons
     * @param listener
     */
    /*
     * @effects: adds the ActionListener listener to each button
     */
    protected void addActionListener(ArrayList<JButton> buttons, ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }
}

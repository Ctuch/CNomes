package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//represents a menu panel with a display of buttons for the user to interact with
public class MenuPanel extends JPanel {

    //EFFECTS: sets the size, layout, and background color for the panel
    protected MenuPanel(ActionListener listener, int width) {
        setPreferredSize(new Dimension(width, 100));
        setLayout(new GridLayout(1, 0, 10, 5));
        setBackground(Color.GRAY);
        createMenuLabel("Menu");
        createMenuButtons(listener);
    }

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

    private void createMenuButtons(ActionListener listener) {
        JButton addButton = new JButton("Turn Red");
        JButton moveButton = new JButton("Turn Blue");
        JButton attackButton = new JButton("Turn Neutral");
        JButton specialActionButton = new JButton("Turn Black");
        JButton displayButton = new JButton("Load New Word list");
        JButton helpButton = new JButton("Reset Board");
        ArrayList<JButton> menuButtons = new ArrayList<>();
        menuButtons.add(addButton);
        menuButtons.add(moveButton);
        menuButtons.add(attackButton);
        menuButtons.add(specialActionButton);
        menuButtons.add(displayButton);
        menuButtons.add(helpButton);

        addActionListener(menuButtons, listener);

        createMenuButtons(menuButtons);
    }
    // EFFECTS: adds the ActionListener listener to each button
    protected void addActionListener(ArrayList<JButton> buttons, ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }

}

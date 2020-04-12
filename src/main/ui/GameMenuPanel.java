package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//represents a menu panel with a display of buttons for the user to interact with
public class GameMenuPanel extends MenuPanel {

    private JButton firstButton;
    private static boolean redFirst;

    //EFFECTS: sets the size, layout, and background color for the panel
    protected GameMenuPanel(ActionListener listener, int width) {
        setPreferredSize(new Dimension(width, 200));
        setLayout(new GridLayout(2, 0, 10, 5));
        setBackground(Color.GRAY);
        createMenuLabel("Menu");
        createMenuButtons(listener);
        redFirst = true;
    }

    private void createMenuButtons(ActionListener listener) {
        firstButton = new JButton("  ");
        firstButton.setBackground(Color.RED);
        firstButton.setOpaque(true);
        firstButton.setBorderPainted(false);

        JButton addButton = new JButton("Turn Red");
        JButton moveButton = new JButton("Turn Blue");
        JButton attackButton = new JButton("Turn Neutral");
        JButton specialActionButton = new JButton("Turn Black");
        JButton displayButton = new JButton("Load New Word list");
        JButton helpButton = new JButton("Reset Board");
        JButton switchView = new JButton("Switch View");
        ArrayList<JButton> menuButtons = new ArrayList<>();
        menuButtons.add(firstButton);
        menuButtons.add(addButton);
        menuButtons.add(moveButton);
        menuButtons.add(attackButton);
        menuButtons.add(specialActionButton);
        menuButtons.add(displayButton);
        menuButtons.add(helpButton);
        menuButtons.add(switchView);

        addActionListener(menuButtons, listener);

        createMenuButtons(menuButtons);
    }

    public void switchFirstColor() {
        if (firstButton.getBackground().equals(Color.RED)) {
            firstButton.setBackground(Color.BLUE);
            redFirst = false;
        } else {
            firstButton.setBackground(Color.RED);
            redFirst = true;
        }
    }

    public static boolean isRedFirst() {
        return redFirst;
    }

}
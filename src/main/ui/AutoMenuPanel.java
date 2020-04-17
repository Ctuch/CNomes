package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AutoMenuPanel extends MenuPanel {

    private static boolean redFirst;


    public AutoMenuPanel(ActionListener listener, int width) {
        setPreferredSize(new Dimension(width, 200));
        setLayout(new GridLayout(1, 0, 10, 5));
        setBackground(Colors.MENU_PANEL);
        createMenuLabel("Menu");
        createColorLabel();
        createMenuButtons(listener);
        redFirst = true;
    }

    private void createColorLabel() {
        JLabel colorLabel = new JLabel();
        if (redFirst) {
            colorLabel.setBackground(Colors.RED_COVER);
        } else {
            colorLabel.setBackground(Colors.BLUE_COVER);
        }
        colorLabel.setOpaque(true);
        add(colorLabel);
    }

    private void createMenuButtons(ActionListener listener) {
        //TODO: Add label for color of starting team
        ArrayList<JButton> menuButtons = new ArrayList<>();
        JButton switchViewButton = new JButton("Switch View");
        menuButtons.add(switchViewButton);

        addActionListener(menuButtons, listener);

        createMenuButtons(menuButtons);
    }

    public static void setRedFirst(boolean redFirst) {
        AutoMenuPanel.redFirst = redFirst;
    }
}
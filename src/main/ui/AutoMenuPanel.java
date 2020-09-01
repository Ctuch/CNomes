package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * creates the menu for the automatic version
 */
public class AutoMenuPanel extends MenuPanel {

    private static boolean redFirst; // determines if red team goes first
    private static JLabel colorLabel;

    /**
     * creates the meny for the automatic version
     * @param listener
     * @param width
     */
    public AutoMenuPanel(ActionListener listener, int width) {
        setPreferredSize(new Dimension(width, 200));
        setLayout(new GridLayout(1, 0, 10, 5));
        setBackground(Colors.MENU_PANEL);
        add(createMenuLabel("Menu"));
        createColorLabel();
        createMenuButtons(listener);
        redFirst = true;
    }

    private void createColorLabel() {
        colorLabel = new JLabel();
        if (redFirst) {
            colorLabel.setBackground(Colors.RED_COVER);
        } else {
            colorLabel.setBackground(Colors.BLUE_COVER);
        }
        colorLabel.setOpaque(true);
        add(colorLabel);
    }

    private void createMenuButtons(ActionListener listener) {
        ArrayList<JButton> menuButtons = new ArrayList<>();
        JButton switchViewButton = new JButton("Switch View");
        JButton quitButton = new JButton("Quit");
        menuButtons.add(switchViewButton);
        menuButtons.add(quitButton);

        addActionListener(menuButtons, listener);

        createMenuButtons(menuButtons);
    }

    /**
     *
     * @param redFirst
     */
    public static void setRedFirst(boolean redFirst) {
        AutoMenuPanel.redFirst = redFirst;
        if (redFirst) {
            colorLabel.setBackground(Colors.RED_COVER);
        } else {
            colorLabel.setBackground(Colors.BLUE_COVER);
        }
    }

    /**
     *
     * @return
     */
    public static boolean isRedFirst() {
        return redFirst;
    }
}
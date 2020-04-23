package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * menu panel for the manual version
 */
public class GameMenuPanel extends MenuPanel {

    private JButton firstButton; // tile for which team goes first
    private static boolean redFirst; // determines if red team goes first
    private static final int HEIGHT = 200;

    /**
     *
     * @param listener
     * @param width
     */
    public GameMenuPanel(ActionListener listener, int width) {
        setPreferredSize(new Dimension(width, HEIGHT));
        setLayout(new GridLayout(2, 0, 10, 5));
        setBackground(Colors.MENU_PANEL);
        createMenuLabel("Menu");
        createMenuButtons(listener);
        redFirst = true;
    }

    private void createMenuButtons(ActionListener listener) {
        firstButton = new JButton("  ");
        firstButton.setBackground(Colors.RED_COVER);
        firstButton.setOpaque(true);
        firstButton.setBorderPainted(false);

        JButton redButton = new JButton("Turn Red");
        JButton blueButton = new JButton("Turn Blue");
        JButton neutralButton = new JButton("Turn Neutral");
        JButton blackButton = new JButton("Turn Black");
        JButton newWordButton = new JButton("Load New Word list");
        JButton resetButton = new JButton("Reset Board");
        JButton switchViewButton = new JButton("Switch View");
        JButton quitButton = new JButton("Quit");
        ArrayList<JButton> menuButtons = new ArrayList<>();
        menuButtons.add(firstButton);
        menuButtons.add(redButton);
        menuButtons.add(blueButton);
        menuButtons.add(neutralButton);
        menuButtons.add(blackButton);
        menuButtons.add(newWordButton);
        menuButtons.add(resetButton);
        menuButtons.add(switchViewButton);
        menuButtons.add(quitButton);

        addActionListener(menuButtons, listener);

        createMenuButtons(menuButtons);
    }

    /**
     * Switches the display of which team goes first
     */
    public void switchFirstColor() {
        if (firstButton.getBackground().equals(Colors.RED_COVER)) {
            firstButton.setBackground(Colors.BLUE_COVER);
            redFirst = false;
        } else {
            firstButton.setBackground(Colors.RED_COVER);
            redFirst = true;
        }
    }

    /**
     * returns if the red team has the first turn
     * @return if red has first turn
     */
    public static boolean isRedFirst() {
        return redFirst;
    }

}

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Creates the menu for the starting screen
 */
public class StartPanel extends MenuPanel {

    /**
     * Constructs the starting menu
     * @param listener listens to button actions from client
     * @param width width of the starting menu
     * @param height height of the starting menu
     * @param gameOverMessage message displayed at end of game
     */
    public StartPanel(ActionListener listener, int width, int height, JLabel gameOverMessage) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridLayout(0, 1, 10, 5));
        setBackground(Colors.START_PANEL);
        createMenuLabel("Start Menu");
        gameOverMessage.setHorizontalAlignment(JLabel.CENTER);
        gameOverMessage.setForeground(Colors.MENU_TEXT);
        createMenuButtons(listener);
        add(gameOverMessage);
    }

    // adds buttons to listener and creates them
    private void createMenuButtons(ActionListener listener) {
        JButton addButton = new JButton("Start Manual Game");
        JButton moveButton = new JButton("Start Auto Game");
        ArrayList<JButton> startButtons = new ArrayList<>();
        startButtons.add(addButton);
        startButtons.add(moveButton);

        addActionListener(startButtons, listener);

        createMenuButtons(startButtons);
    }
}

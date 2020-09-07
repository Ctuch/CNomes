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
        setLayout(new GridBagLayout());
        setBackground(Colors.START_PANEL);
        JLabel text = createMenuLabel("Start Menu");
        addLabelConstraints(text);
        createMenuButtons(listener);
        addGameOver(gameOverMessage);
    }

    private void addGameOver(JLabel gameOverMessage) {
        gameOverMessage.setHorizontalAlignment(JLabel.CENTER);
        gameOverMessage.setForeground(Colors.MENU_TEXT);
        GridBagConstraints c = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = insets;
        c.gridx = 0;
        c.gridy = 3;
        add(gameOverMessage, c);
    }

    private void addLabelConstraints(JLabel text) {
        text.setFont(new Font(text.getFont().getName(), Font.PLAIN, 50));
        GridBagConstraints c = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);
        c.weightx = .7;
        c.weighty = .7;
        c.fill = GridBagConstraints.BOTH;
        c.insets = insets;
        c.gridx = 0;
        c.gridy = 0;
        add(text, c);
    }

    // adds buttons to listener and creates them
    private void createMenuButtons(ActionListener listener) {
        JButton addButton = new JButton("Start Manual Game");
        JButton moveButton = new JButton("Start Auto Game");
        ArrayList<JButton> startButtons = new ArrayList<>();
        startButtons.add(addButton);
        startButtons.add(moveButton);

        GridBagConstraints c = new GridBagConstraints();
        Insets insets = new Insets(10, 75, 10, 75);
        c.weightx = .5;
        c.weighty = .5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = insets;

        addActionListener(startButtons, listener);

        createMenuButtons(startButtons, c);
    }
}

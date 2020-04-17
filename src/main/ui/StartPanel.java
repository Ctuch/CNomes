package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartPanel extends MenuPanel {

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

package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartPanel extends JPanel {

    public StartPanel(ActionListener listener, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setLayout(new GridLayout(0, 1, 10, 5));
        setBackground(Color.ORANGE);
        createMenuLabel("Start Menu");
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
    private void createMenuButtons(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
            add(button);
        }
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

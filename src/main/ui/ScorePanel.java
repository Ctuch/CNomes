package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * creates panel to display current game score
 */
public class ScorePanel extends JPanel {

    private static JLabel redLabel; // Label for displaying remaining tiles of the red team
    private static JLabel blueLabel; // Label for displaying remaining tiles of the blue team

    /**
     * Creates the score panel display
     * @param width Width of container frame
     */
    public ScorePanel(int width) {
        setPreferredSize(new Dimension(width, 50));
        setBackground(Colors.MENU_PANEL);
        setLayout(new GridBagLayout());
        createLabels();
    }

    private void createLabels() {
        redLabel = new JLabel("Red: 0", SwingConstants.CENTER);
        JLabel blankLabel = new JLabel();
        blueLabel = new JLabel("Blue: 0", SwingConstants.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.weightx = .3;
        c.gridx = 0;
        c.gridy = 0;
        add(redLabel, c);

        c.weightx = .5;
        c.gridx = 1;
        add(blankLabel, c);

        c.weightx = .3;
        c.gridx = 2;
        add(blueLabel, c);
    }

    /**
     * Updates text for score labels
     * @param red New score for red team
     * @param blue New score for blue team
     */
    public static void updateScore(int red, int blue) {
        if (GameMenuPanel.isRedFirst()) {
            red = 9 - red;
            blue = 8 - blue;
        } else {
            red = 8 - red;
            blue = 9 - blue;
        }
        redLabel.setText("Red: " + red);
        blueLabel.setText("Blue: " + blue);
    }

}

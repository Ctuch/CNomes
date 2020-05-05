package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Mainframe class for displaying UI
 */
public class CadeNomesApp extends JFrame {

    ManualGamePanel manualGamePanel; // Displays word tiles
    GameMenuPanel gameMenuPanel; // Manual game menu panel displayed below gamePanel
    StartPanel startPanel; // Displays manual and auto game choices
    AutoMenuPanel autoMenuPanel; // Auto game menu panel displayed below gamePanel
    ScorePanel scorePanel; //Displays game score
    JLabel gameOverLabel; // End game description on startPanel

    /**
     * Constructor for class
     */
    public CadeNomesApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 650));

        gameMenuPanel = new GameMenuPanel(new MenuButtonActionListener(), this.getWidth());
        scorePanel = new ScorePanel(this.getWidth());
        manualGamePanel = new ManualGamePanel(this.getWidth(), this.getHeight());


        gameOverLabel = new JLabel("");
        startPanel = new StartPanel(new StartButtonActionListener(), this.getWidth(), this.getHeight(), gameOverLabel);
        autoMenuPanel = new AutoMenuPanel(new MenuButtonActionListener(), this.getWidth());


        add(manualGamePanel);
        add(scorePanel, BorderLayout.NORTH);
        add(gameMenuPanel, BorderLayout.SOUTH);
        add(autoMenuPanel, BorderLayout.SOUTH);
        add(startPanel, BorderLayout.WEST);
        revealGame(false, true);
        pack();
        centerOnScreen();
        setVisible(true);
    }

    /**
     * Determines which panels, buttons, etc. are hidden to the user
     * @param gameReveal determines if the game screen is shown
     * @param isAuto determines whether the auto game screen is shown
     */
    private void revealGame(boolean gameReveal, boolean isAuto) {
        //TODO: reveal auto vs. manual boards
        startPanel.setVisible(!gameReveal);
        scorePanel.setVisible(gameReveal);
        if (isAuto && gameReveal) {
            remove(gameMenuPanel);
            add(autoMenuPanel, BorderLayout.SOUTH);
            autoMenuPanel.setVisible(gameReveal);
        } else if (gameReveal) {
            remove(autoMenuPanel);
            add(gameMenuPanel, BorderLayout.SOUTH);
            gameMenuPanel.setVisible(gameReveal);
        } else {
            autoMenuPanel.setVisible(gameReveal);
            gameMenuPanel.setVisible(gameReveal);
        }

        manualGamePanel.setVisible(gameReveal);
        if (gameReveal) {
            gameOverLabel.setText("");
        }
    }

    // centers .this
    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: selects and runs the method associated with the triggered action from the menu buttons
    private class MenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equalsIgnoreCase("  ")) {
                gameMenuPanel.switchFirstColor();
            } else if (command.equalsIgnoreCase("Turn Red")) {
                manualGamePanel.changeColor(Colors.RED_COVER);
            } else if (command.equalsIgnoreCase("Turn Blue")) {
                manualGamePanel.changeColor(Colors.BLUE_COVER);
            } else if (command.equalsIgnoreCase("Turn Neutral")) {
                manualGamePanel.changeColor(Colors.NEUTRAL_COVER);
            } else if (command.equalsIgnoreCase("Turn Black")) {
                manualGamePanel.changeColor(Colors.BLACK_COVER);
            } else if (command.equalsIgnoreCase("Load New Word list")) {
                manualGamePanel.addWordsToTiles();
            } else if (command.equalsIgnoreCase("Reset Board")) {
                manualGamePanel.resetBoard();
            } else if (command.equalsIgnoreCase("Switch View")) {
                manualGamePanel.setMasterView();
            } else if (command.equalsIgnoreCase("Quit")) {
                manualGamePanel.resetBoard();
                revealGame(false, true);
            }
            String gameOver = manualGamePanel.isGameOver();
            if (!gameOver.equals("")) {
                gameOverLabel.setText(gameOver);
                revealGame(false, true);

            }
            manualGamePanel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: selects and runs the method associated with the triggered action from the start buttons
    private class StartButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equalsIgnoreCase("Start Manual Game")) {
                revealGame(true, false);
            } else if (command.equalsIgnoreCase("Start Auto Game")) {
                revealGame(true, true);
            }
        }
    }
}

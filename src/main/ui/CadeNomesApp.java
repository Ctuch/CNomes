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
    AutoGamePanel autoGamePanel; // Auto game panel that displays word tiles
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
        autoMenuPanel = new AutoMenuPanel(new AutoMenuButtonActionListener(), this.getWidth());
        autoGamePanel = new AutoGamePanel(this.getWidth(), this.getHeight());

        gameOverLabel = new JLabel("");
        startPanel = new StartPanel(new StartButtonActionListener(), this.getWidth(), this.getHeight(), gameOverLabel);



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
            remove(manualGamePanel);
            remove(gameMenuPanel);
            add(autoGamePanel);
            add(autoMenuPanel, BorderLayout.SOUTH);
            autoGamePanel.setVisible(true);
            autoMenuPanel.setVisible(true);
        } else if (gameReveal) {
            remove(autoMenuPanel);
            remove(autoGamePanel);
            add(manualGamePanel);
            add(gameMenuPanel, BorderLayout.SOUTH);
            gameMenuPanel.setVisible(true);
            manualGamePanel.setVisible(true);
        } else {
            autoMenuPanel.setVisible(false);
            gameMenuPanel.setVisible(false);
            autoGamePanel.setVisible(false);
            manualGamePanel.setVisible(false);
        }

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
                Timer timer = new Timer(750, event -> {
                    gameOverLabel.setText(gameOver);
                    revealGame(false, false);
                    manualGamePanel.resetBoard();
                });
                timer.setRepeats(false);
                timer.start();

            }
            manualGamePanel.repaint();
        }
    }

    private class AutoMenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equalsIgnoreCase("Switch View")) {
                autoGamePanel.setMasterView();
            } else if (command.equalsIgnoreCase("Quit")) {
                autoGamePanel.resetBoard();
                revealGame(false, true);
            }
            String gameOver = autoGamePanel.isGameOver();
            if (!gameOver.equals("")) {
                Timer timer = new Timer(750, event -> {
                    gameOverLabel.setText(gameOver);
                    revealGame(false, true);
                    autoGamePanel.resetBoard();
                });
                timer.setRepeats(false);
                timer.start();

            }
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

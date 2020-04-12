package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadeNomesApp extends JFrame {

    GamePanel gamePanel;
    GameMenuPanel gameMenuPanel;
    StartPanel startPanel;
    JLabel gameOverLabel;

    public CadeNomesApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 800));
        gamePanel = new GamePanel(this.getWidth(), this.getHeight());
        gameMenuPanel = new GameMenuPanel(new MenuButtonActionListener(), this.getWidth());

        gameOverLabel = new JLabel("");
        startPanel = new StartPanel(new StartButtonActionListener(), this.getWidth(), this.getHeight(), gameOverLabel);


        add(gamePanel);
        add(gameMenuPanel, BorderLayout.SOUTH);
        add(startPanel, BorderLayout.WEST);
        revealGame(false);
        pack();
        centerOnScreen();
        setVisible(true);
    }

    private void revealGame(boolean reveal) {
        startPanel.setVisible(!reveal);
        gamePanel.setVisible(reveal);
        gameMenuPanel.setVisible(reveal);
        if (reveal) {
            gameOverLabel.setText("");
        }
    }

    private void centerOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    //MODIFIES: this
    //EFFECTS: selects and runs the method associated with the triggered action from the game buttons
    protected class MenuButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equalsIgnoreCase("  ")) {
                gameMenuPanel.switchFirstColor();
            } else if (command.equalsIgnoreCase("Turn Red")) {
                gamePanel.changeColor(Color.RED);
            } else if (command.equalsIgnoreCase("Turn Blue")) {
                gamePanel.changeColor(Color.BLUE);
            } else if (command.equalsIgnoreCase("Turn Neutral")) {
                gamePanel.changeColor(Color.GREEN);
            } else if (command.equalsIgnoreCase("Turn Black")) {
                gamePanel.changeColor(Color.BLACK);
            } else if (command.equalsIgnoreCase("Load New Word list")) {
                gamePanel.loadWords();
            } else if (command.equalsIgnoreCase("Reset Board")) {
                gamePanel.setReset(true);
            } else if (command.equalsIgnoreCase("Switch View")) {
                gamePanel.setMasterView();
            }
            String gameOver = gamePanel.isGameOver();
            if (!gameOver.equals("")) {
                gameOverLabel.setText(gameOver);
                revealGame(false);

            }
            gamePanel.repaint();
        }
    }

    //MODIFIES: this
    //EFFECTS: selects and runs the method associated with the triggered action from the game buttons
    protected class StartButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equalsIgnoreCase("Start Manual Game")) {
                revealGame(true);
            } else if (command.equalsIgnoreCase("Start Auto Game")) {
                revealGame(true);
            }
            gamePanel.repaint();
        }
    }
}

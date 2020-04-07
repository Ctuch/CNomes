package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadeNomesApp extends JFrame {

    GamePanel gamePanel;
    MenuPanel menuPanel;

    public CadeNomesApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 800));
        gamePanel = new GamePanel(this.getWidth(), this.getHeight());
        menuPanel = new MenuPanel(new MenuButtonActionListener(), this.getWidth());

        add(gamePanel);
        add(menuPanel, BorderLayout.SOUTH);
        pack();
        centerOnScreen();
        setVisible(true);
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

            if (command.equalsIgnoreCase("Turn Red")) {
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
            }
            gamePanel.repaint();
        }
    }
}

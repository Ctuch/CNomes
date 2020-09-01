package main.ui;

import main.model.Board;
import main.model.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AutoGamePanel extends JPanel {

    private final static int MAX_TILES_FIRST = 9; // maximum team tiles

    Board board;
    boolean masterView;
    private int redCount; // red tiles revealed
    private int blueCount; // blue tiles revealed
    private boolean assassinTriggered; // if the black tile has been chosen

    public AutoGamePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Colors.GAME_PANEL);
        setLayout(new GridBagLayout());
        board = new Board();
        createBoard();
        masterView = false;
        addMouseControl();
        redCount = 0;
        blueCount = 0;
        assassinTriggered = false;
        ScorePanel.updateScore(false, redCount, blueCount);
    }

    private void createBoard() {
        removeAll();
        revalidate();
        GridBagConstraints c = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = insets;
        ArrayList<Location> locations = board.getTiles();
        ArrayList<String> words = board.getWords();
        for (int i = 0; i < locations.size(); i++) {
            c.gridx = i % 5;
            c.gridy = i / 5;
            if (words != null) {
                locations.get(i).setWord(words.get(i));
            }
            add(locations.get(i), c);
        }

    }

    private void addMouseControl() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                updateSelectedCard(e.getX(), e.getY());
            }
        });
    }

    private void changeColor(int index) {
        //TODO: implement!
    }

    private Color coverVersionOfColor() {
        //TODO: implement!
        return null;
    }

    private void updateCount(Color color) {
        //TODO: implement!
        ScorePanel.updateScore(false, redCount, blueCount);
    }

    private void updateSelectedCard(int x, int y) {
        ArrayList<Location> locations = board.getTiles();
        for (int i = 0; i < locations.size(); i++) {
            if (isInSpace(x, y, locations.get(i).getX(), locations.get(i).getY())) {
                changeColor(i);
                return;
            }
        }
    }

    //EFFECTS: returns true if the mouse clicks within the square of the person
    private boolean isInSpace(int mouseX, int mouseY, int locationX, int locationY) {
        int differenceX = mouseX - locationX;
        int differenceY = mouseY - locationY;

        return differenceX <= board.getTiles().get(0).getWidth() && differenceX >= 0 &&
                differenceY <= board.getTiles().get(0).getHeight() && differenceY >= 0;
    }

    public String isGameOver() {
        String gameOverMessage = "";
        boolean isRed = AutoMenuPanel.isRedFirst();
        if (isRed) {
            if (redCount == MAX_TILES_FIRST) {
                gameOverMessage = "Red wins!";
            } else if (blueCount == MAX_TILES_FIRST - 1) {
                gameOverMessage = "Blue wins!";
            }
        } else {
            if (redCount == MAX_TILES_FIRST - 1) {
                gameOverMessage = "Red wins!";
            } else if (blueCount == MAX_TILES_FIRST) {
                gameOverMessage = "Blue wins!";
            }
        }
        if (assassinTriggered) {
            gameOverMessage = "You were killed by the assassin!";
        }
        return gameOverMessage;
    }

    public void resetBoard() {
        redCount = 0;
        blueCount = 0;
        ScorePanel.updateScore(false, redCount, blueCount);
        masterView = false;
        board.loadNewBoard();
        createBoard();
        assassinTriggered = false;
    }

    public void setMasterView() {
        this.masterView = !this.masterView;
        for (Location l : board.getTiles()) {
            l.switchTileColor();
        }
    }
}

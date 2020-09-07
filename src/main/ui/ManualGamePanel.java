package main.ui;

import main.model.Location;
import main.model.WordList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates and manages the board game tiles
 */
public class ManualGamePanel extends JPanel {

    private final static int MAX_TILES_FIRST = 9; // maximum team tiles

    private int selectedSquare; // most recent clicked on square by client
    private ArrayList<Location> locations; // list of tile locations on the board

    private Boolean masterView; // determines whether the spy master view is displayed
    private ArrayList<String> words; // words displayed on tiles
    private int redCount; // red tiles revealed
    private int blueCount; // blue tiles revealed
    private boolean assassinTriggered; // if the black tile has been chosen

    /**
     * creates the game panel
     * @param width width for the game panel
     * @param height height for the game panel
     */
    public ManualGamePanel(int width, int height) {
        // TODO: change height to 3/4 of the screen
        setPreferredSize(new Dimension(width, height));
        setBackground(Colors.GAME_PANEL);
        setLayout(new GridBagLayout());
        locations = new ArrayList<>();
        loadWords();
        createBoard();
        selectedSquare = -1;
        masterView = false;
        addMouseControl();
        redCount = 0;
        blueCount = 0;
        assassinTriggered = false;
        ScorePanel.updateScore(true, redCount, blueCount);
    }

    private void loadLocations() {
        for (int i = 0; i < 25; i++) {
            locations.add(new Location());
        }
    }

    private void createBoard() {
        loadLocations();
        // TODO: learn to play with fonts for graphics
        GridBagConstraints c = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5);
        c.weightx = .5;
        c.weighty = .5;
        c.fill = GridBagConstraints.BOTH;
        c.insets = insets;
        for (int i = 0; i < locations.size(); i++) {
            c.gridx = i % 5;
            c.gridy = i / 5;
            if (words != null) {
                locations.get(i).setWord(words.get(i));
            }
            add(locations.get(i), c);
        }
    }

    private void loadWords() {
        try {
            WordList wordList = new WordList(false);
            wordList.formatWords();
            words = wordList.getWords();
        } catch (IOException e) {
            System.out.println("I'm sorry please try again");
            words = null;
        }
    }

    public void changeColor(Color color) {
        if (selectedSquare != -1) {
            if (masterView) {
                locations.get(selectedSquare).setMasterColor(masterVersionOfColor(color), true);
            } else {
                locations.get(selectedSquare).setCoverColor(color, words.get(0).length());
            }
            updateCounts(color);
        }
    }

    private Color masterVersionOfColor(Color color) {
        if (color.equals(Colors.RED_COVER)) {
            return Colors.RED_MASTER;
        } else if (color.equals(Colors.BLUE_COVER)) {
            return Colors.BLUE_MASTER;
        } else if (color.equals(Colors.NEUTRAL_COVER)) {
            return Colors.NEUTRAL_MASTER;
        } else {
            return Colors.BLACK_MASTER;
        }
    }

    private void updateCounts(Color color) {
        if (!masterView) {
            if (color.equals(Colors.RED_COVER)) {
                redCount++;
                ScorePanel.updateScore(true, redCount, blueCount);
            } else if (color.equals(Colors.BLUE_COVER)) {
                blueCount++;
                ScorePanel.updateScore(true, redCount, blueCount);
            } else if (color.equals(Colors.BLACK_COVER)) {
                assassinTriggered = true;
            }
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

    private void updateSelectedCard(int x, int y) {
        for (int i = 0; i < locations.size(); i++) {
            if (isInSpace(x, y, locations.get(i).getX(), locations.get(i).getY())) {
                selectedSquare = i;
                return;
            }
        }
        selectedSquare = -1;
    }

    //EFFECTS: returns true if the mouse clicks within the square of the person
    private boolean isInSpace(int mouseX, int mouseY, int locationX, int locationY) {
        int differenceX = mouseX - locationX;
        int differenceY = mouseY - locationY;
        return differenceX <= locations.get(0).getWidth() && differenceX >= 0 && differenceY <= locations.get(0).getHeight() && differenceY >= 0;
    }

    public String isGameOver() {
        String gameOverMessage = "";
        boolean isRed = GameMenuPanel.isRedFirst();
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
        ScorePanel.updateScore(true, redCount, blueCount);
        selectedSquare = -1;
        masterView = false;
        resetLocations();
        assassinTriggered = false;
    }

    private void resetLocations() {
        loadWords();
        for (int i = 0; i < locations.size(); i++) {
            Location l = locations.get(i);
            l.setMasterColor(Colors.NEUTRAL_MASTER, true);
            l.setCoverColor(Colors.TILE, 0);
            l.setText(words.get(i));
        }
    }

    public void setMasterView() {
        this.masterView = !this.masterView;
        for (Location l : locations) {
            l.switchTileColor();
        }
    }

    public void addWordsToTiles() {
        loadWords();
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getCoverColor().equals(Colors.TILE)) {
                locations.get(i).setText(words.get(i));
            }
        }
    }
}

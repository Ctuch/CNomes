package main.ui;

import main.model.Location;
import main.model.WordList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private final static int C_HEIGHT = 50;
    private final static int C_WIDTH = 100;
    private final static int C_X_MULTIPLIER = C_WIDTH + 10;
    private final static int C_Y_MULTIPLIER = C_HEIGHT + 10;
    private final static int TEXT_HORIZ_SPACE = C_WIDTH / 2;
    private final static int TEXT_VERT_SPACE = C_Y_MULTIPLIER / 2;
    private final static int MAX_TILES_FIRST = 9;


    private int selectedSquare;
    private ArrayList<Location> locations;

    private Boolean reset;
    private Boolean masterView;
    private ArrayList<String> words;
    private boolean inLocations;
    private int redCount;
    private int blueCount;
    private boolean assassinTriggered;

    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension((width * 3) / 4, height));
        setBackground(Color.WHITE);
        locations = new ArrayList<>();
        selectedSquare = -1;
        reset = true;
        masterView = true;
        inLocations = false;
        loadWords();
        addMouseControl();
        redCount = 0;
        blueCount = 0;
        assassinTriggered = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (reset) {
            loadDefaultBoard(g);
        } else {
            drawBoard(g);
        }
    }

    private void drawBoard(Graphics g) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int currentPos = getCurrentPos(i, j);
                drawTile(i, j, g, currentPos);
                if (!(words == null) && locations.get(currentPos).getCoverColor() == Colors.TILE) {
                    writeWord(words.get(currentPos), i, j, g);
                }
            }
        }
    }

    private void loadDefaultBoard(Graphics g) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
        locations.clear();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int currentPos = getCurrentPos(i, j);
                locations.add(new Location(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER));
                drawTile(i, j, g, currentPos);
                if (!(words == null)) {
                    writeWord(words.get(currentPos), i, j, g);
                }
            }
        }
        reset = false;
        masterView = false;
    }

    private int getCurrentPos(int i, int j) {
        return (i - 1) * 5 + j - 1;
    }

    private void drawTile(int i, int j, Graphics g, int currentPos) {
        if (masterView) {
            g.setColor(locations.get(currentPos).getMasterColor());
        } else {
            g.setColor(locations.get(currentPos).getCoverColor());
        }
        g.fillRect(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER, C_WIDTH, C_HEIGHT);
    }

    private void writeWord(String word, int i, int j, Graphics g) {
        g.setColor(Colors.TEXT);
        //TODO: fix centering of words
        g.drawString(word, i * C_X_MULTIPLIER + TEXT_HORIZ_SPACE - word.length() * 4,
                j * C_Y_MULTIPLIER + TEXT_VERT_SPACE);
    }


    public void loadWords() {
        try {
            WordList wordList = new WordList();
            words = wordList.getWords();
        } catch (IOException e) {
            System.out.println("I'm sorry please try again");
            words = null;
        }
    }

    public void changeColor(Color color) {
        if (selectedSquare != -1) {
            if (masterView) {
                locations.get(selectedSquare).setMasterColor(masterVersionOfColor(color));
            } else {
                locations.get(selectedSquare).setCoverColor(color);
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
            } else if (color.equals(Colors.BLUE_COVER)) {
                blueCount++;
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
            if (isInSpace(x, y, locations.get(i).getxPos(), locations.get(i).getyPos())) {
                selectedSquare = i;
                System.out.println("selected square is " + i);
                return;
            }
        }
        System.out.println("No square selected");
        selectedSquare = -1;
    }

    //EFFECTS: returns true if the mouse clicks within the square of the person
    public boolean isInSpace(int mouseX, int mouseY, int locationX, int locationY) {
        int differenceX = mouseX - locationX;
        int differenceY = mouseY - locationY;
        return differenceX <= C_WIDTH && differenceX >= 0 && differenceY <= C_HEIGHT && differenceY >= 0;
    }

    public void setReset(Boolean reset) {
        this.selectedSquare = -1;
        this.reset = reset;
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
        if (!gameOverMessage.equals("")) {
            resetBoard();
        }
        return gameOverMessage;
    }

    private void resetBoard() {
        reset = true;
        redCount = 0;
        blueCount = 0;
        selectedSquare = -1;
        masterView = true;
        locations.clear();
        assassinTriggered = false;
    }

    public void setMasterView() {
        this.masterView = !this.masterView;
    }
}

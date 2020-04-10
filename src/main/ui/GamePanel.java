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


    private int selectedSquare;
    private ArrayList<Location> locations;
    private ArrayList<Location> masterClues;

    private Boolean reset;
    private Boolean masterView;
    private ArrayList<String> words;
    private boolean inLocations;

    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension((width * 3) / 4, height));
        setBackground(Color.WHITE);
        locations = new ArrayList<>();
        masterClues = new ArrayList<>();
        selectedSquare = -1;
        reset = true;
        masterView = true;
        inLocations = false;
        loadWords();
        addMouseControl();
    }

    private void loadDefaultBoard(Graphics g) {
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
        locations.clear();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int currentPos = (i - 1) * 5 + j - 1;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER, C_WIDTH, C_HEIGHT);
                locations.add(new Location(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER));
                if (masterView) {
                    masterClues.add(new Location(locations.get(currentPos).getxPos(), locations.get(currentPos).getyPos() + C_HEIGHT));
                    masterClues.get(currentPos).setColor(new Color(236, 236, 236));
                    g.setColor(masterClues.get(currentPos).getColor());
                    drawMasterClue(i, j, g);
                }
                if (!(words == null)) {
                    g.setColor(Color.BLACK);
                    //TODO: center words
                    g.drawString(words.get(currentPos), i * C_X_MULTIPLIER + TEXT_HORIZ_SPACE - words.get(currentPos).length()*12, j * C_Y_MULTIPLIER + TEXT_VERT_SPACE);
                }
            }
        }
        reset = false;
        masterView = false;
    }

    private void drawMasterClue(int colX, int rowY, Graphics g) {
        g.fillRect(colX * C_X_MULTIPLIER, rowY * C_Y_MULTIPLIER + C_HEIGHT,
                C_WIDTH, 10);
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

                int currentPos = (i - 1) * 5 + j - 1;
                g.setColor(locations.get(currentPos).getColor());
                g.fillRect(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER, C_WIDTH, C_HEIGHT);
                locations.get(currentPos).setPos(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER);
                if (masterView) {
                    g.setColor(masterClues.get(currentPos).getColor());
                    drawMasterClue(i, j, g);
                }
                if (!(words == null) && locations.get(currentPos).getColor() == Color.LIGHT_GRAY) {
                    g.setColor(Color.BLACK);
                    //TODO: fix centering of words
                    g.drawString(words.get(currentPos), i * C_X_MULTIPLIER + TEXT_HORIZ_SPACE - words.get(currentPos).length()*4,
                            j * C_Y_MULTIPLIER + TEXT_VERT_SPACE);
                }
            }
        }
    }

    public void loadWords() {
        try {
            WordList wordList = new WordList();
            words =  wordList.getWords();
        } catch (IOException e) {
            System.out.println("I'm sorry please try again");
            words = null;
        }
    }

    public void changeColor(Color color) {
        if (selectedSquare != -1) {
            if (inLocations) {
                locations.get(selectedSquare).setColor(color);
            } else {
                System.out.println("Selected square was" + selectedSquare);
                masterClues.get(selectedSquare).setColor(color);
            }
        }
    }

    private void addMouseControl() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (updateSelectedCard(e.getX(), e.getY(), locations, C_HEIGHT)) {
                    inLocations = true;
                } else if (updateSelectedCard(e.getX(), e.getY(), masterClues, 10)) {
                    inLocations = false;
                }

            }
        });
    }

    private boolean updateSelectedCard(int x, int y, ArrayList<Location> locs, int height) {
        for (int i = 0; i < locs.size(); i++) {
            if (isInSpace(x, y, locs.get(i).getxPos(), locs.get(i).getyPos(), height)) {
                selectedSquare = i;
                System.out.println("selected square is " + i);
                System.out.println("On a location: " + (height == C_HEIGHT));
                return true;
            }
        }
        System.out.println("No square selected");
        selectedSquare = -1;
        return false;
    }

    //EFFECTS: returns true if the mouse clicks within the square of the person
    public boolean isInSpace(int mouseX, int mouseY, int locationX, int locationY, int height) {
        int differenceX = mouseX - locationX;
        int differenceY = mouseY - locationY;
        return differenceX <= 100 && differenceX >= 0 && differenceY <= height && differenceY >= 0;
    }

    public void setReset(Boolean reset) {
        this.selectedSquare = -1;
        this.reset = reset;
    }

    public void setMasterView() {
        this.masterView = !this.masterView;
    }
}

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
    private final static int TEXT_HORIZ_SPACE = C_WIDTH / 10;
    private final static int TEXT_VERT_SPACE = C_Y_MULTIPLIER / 2;


    private int selectedSquare;
    private ArrayList<Location> locations;

    private Boolean reset;
    private ArrayList<String> words;

    public GamePanel(int width, int height) {
        setPreferredSize(new Dimension((width * 3) / 4, height));
        setBackground(Color.WHITE);
        locations = new ArrayList<>();
        selectedSquare = -1;
        reset = true;
        loadWords();
        addMouseControl();
    }

    private void loadDefaultBoard(Graphics g) {
        locations.clear();
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int currentPos = (i - 1) * 5 + j - 1;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER, C_WIDTH, C_HEIGHT);
                locations.add(new Location(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER));
                if (!(words == null)) {
                    g.setColor(Color.BLACK);
                    //TODO: center words
                    g.drawString(words.get(currentPos), i * C_X_MULTIPLIER + TEXT_HORIZ_SPACE, j * C_Y_MULTIPLIER + TEXT_VERT_SPACE);
                }
            }
        }
        reset = false;
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
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {

                int currentPos = (i - 1) * 5 + j - 1;
                g.setColor(locations.get(currentPos).getColor());
                g.fillRect(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER, C_WIDTH, C_HEIGHT);
                locations.get(currentPos).setPos(i * C_X_MULTIPLIER, j * C_Y_MULTIPLIER);

                if (!(words == null)) {
                    g.setColor(Color.BLACK);
                    //TODO: center words
                    g.drawString(words.get(currentPos), i * C_X_MULTIPLIER + TEXT_HORIZ_SPACE, j * C_Y_MULTIPLIER + TEXT_VERT_SPACE);
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
            locations.get(selectedSquare).setColor(color);
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
        return differenceX <= 100 && differenceX >= 0 && differenceY <= 50 && differenceY >= 0;
    }

    public void setReset(Boolean reset) {
        this.reset = reset;
    }

}

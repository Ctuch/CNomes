package main.model;

import main.ui.AutoMenuPanel;
import main.ui.Colors;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * represents a random configuration of tiles
 */
public class Board {

    private ArrayList<Location> tiles; // A list of tiles with master colors generated
    private ArrayList<String> words; // A list of words to overlay on the tiles
    private ArrayList<String> allWords; // A long list of words to generate random sets from
    private final Random random; // random variable used throughout the class

    /**
     *  creates a random configuration of tiles with word labels
     */
    public Board() {
        random = new Random();
        tiles = loadRandomTileSet();
        allWords = loadWords();
        selectNewWords();
    }

    /**
     *  generates a new list of words for the board from the master list and deletes them from the master list
     */
    public void selectNewWords() {
        if (allWords.size() < 25) {
            allWords = loadWords();
        }

        ArrayList<String> selectedWords = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            String word = allWords.get(random.nextInt(allWords.size()));
            selectedWords.add(word);
            allWords.remove(word);
        }
        words = selectedWords;
    }

    /**
     *  generates a new configuration of tiles
     */
    public void loadNewBoard() {
        selectNewWords();
        tiles = loadRandomTileSet();
    }

    /**
     *  generates a random configuration of tile master colors with 7 neutrals, 1 assassin, 8 or 9 reds,
     *  and 8 or 9 blues depending on who is going first
     * @return list of locations with the master colors
     */
    private ArrayList<Location> loadRandomTileSet() {
        ArrayList<Location> locations = new ArrayList<>();
        boolean redFirst = random.nextBoolean();
        AutoMenuPanel.setRedFirst(redFirst);

        ArrayList<Integer> availableTiles = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            availableTiles.add(i);
            locations.add(new Location());
        }

        for (int i = 0; i < 9; i++) {
            if (i < 7) {
                assignColor(Colors.NEUTRAL_MASTER, locations, availableTiles);
            }
            if (i < 8 || redFirst) {
                assignColor(Colors.RED_MASTER, locations, availableTiles);
            }
            if (i < 8 || !redFirst) {
                assignColor(Colors.BLUE_MASTER, locations, availableTiles);
            }
        }
        locations.get(availableTiles.get(0)).setMasterColor(Colors.BLACK_MASTER);
        return locations;
    }

    private void assignColor(Color color, ArrayList<Location> locations, ArrayList<Integer> availableTiles) {
        int tileIndex = random.nextInt(availableTiles.size());
        int tile = availableTiles.get(tileIndex);
        locations.get(tile).setMasterColor(color);
        availableTiles.remove(tileIndex);
    }

    private ArrayList<String> loadWords() {
        try {
            WordList wordList = new WordList(true);
            return wordList.getWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  getter for field
     * @return list of tiles
     */
    public ArrayList<Location> getTiles() {
        return tiles;
    }

    /**
     *  getter for field
     * @return list of words
     */
    public ArrayList<String> getWords() {
        return words;
    }
}

package main.model;

import main.ui.Colors;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Board {

    private ArrayList<Location> tiles;
    private ArrayList<String> words;
    private ArrayList<String> allWords;
    private final Random random;

    public Board() {
        random = new Random();
        tiles = loadRandomTileSet();
        allWords = loadWords();
        selectNewWords();
    }

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

    public void loadNewBoard() {
        tiles = loadRandomTileSet();
    }


    private ArrayList<Location> loadRandomTileSet() {
        ArrayList<Location> locations = new ArrayList<>();
        boolean redFirst = random.nextBoolean();

        ArrayList<Integer> availableTiles = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            availableTiles.add(i);
            locations.add(new Location(0, 0));
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

    public ArrayList<Location> getTiles() {
        return tiles;
    }

    public ArrayList<String> getWords() {
        return words;
    }
}

package main.model;

import java.io.*;
import java.util.ArrayList;

public class WordList {
    private final String FILE_PATH = "CadeNomesWordList.txt";

    private ArrayList<String> words;

    public WordList() throws IOException {
        words = new ArrayList<>();
        loadWords();
    }

    private void loadWords() throws IOException {
        File file = new File(FILE_PATH);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            words.add(line);
        }
    }

    public ArrayList<String> getWords() {
        return words;
    }
}

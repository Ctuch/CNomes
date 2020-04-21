package main.model;

import java.io.*;
import java.util.ArrayList;

public class WordList {
    private final String FILE_PATH = "CadeNomesWordList.txt";
    private final String FILE_PATH_AUTO = "CadeNomesAutoWordList.txt";

    private ArrayList<String> words;

    public WordList(boolean isAuto) throws IOException {
        words = new ArrayList<>();
        loadWords(isAuto);
    }

    private void loadWords(boolean isAuto) throws IOException {
        File file;
        if (isAuto) {
            file = new File(FILE_PATH_AUTO);
        } else {
            file = new File(FILE_PATH);
        }

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

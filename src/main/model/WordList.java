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

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public int longestWord() {
        int longest = -1;
        for (String w : words) {
            if (w.length() > longest) {
                longest = w.length();
            }
        }
        return longest;
    }

    public void formatWords() {
        int longest = longestWord();
        for (int i = 0; i < words.size(); i++) {
            String w = words.get(i);
            if (w.length() < longest) {
                int spaces = longest - w.length();
                int halfSpaces = spaces / 2;
                boolean odd = spaces % 2 == 1;
                for (int j = 0; j < halfSpaces; j++) {
                    w = " " + w + " ";
                }
                if (odd) {
                    w = " " + w;
                }
                words.set(i, w);
            }
        }
    }
}

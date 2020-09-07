package test;

import main.model.WordList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WordListTest {

    WordList wordList;
    @BeforeEach
    public void runBefore() {
        try {
            wordList = new WordList(false);
        } catch (IOException e) {
            fail();
        }
        ArrayList<String> words = loadTestWords();
        wordList.setWords(words);
    }

    private ArrayList<String> loadTestWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add("Word 1");
        words.add("Quaffle");
        words.add("flowers");
        words.add("freedom");
        words.add("sports");
        words.add("ride");
        words.add("Kuggle");
        words.add("Hamentaschen");
        words.add("Word 6");
        words.add("Hoop");
        words.add("Broom");
        words.add("Stick");
        words.add("green");
        words.add("yellow");
        words.add("16");
        words.add("coolio");
        words.add("Word 13");
        words.add("14");
        words.add("right");
        words.add("22");
        words.add("21");
        words.add("hello");
        words.add("19");
        words.add("24");
        words.add("purple");
        return words;
    }

    @Test
    public void testLongestWord() {
        assertEquals("mystical".length(), wordList.longestWord());
    }

    @Test
    public void testFormatWords() {
        ArrayList<String> words = new ArrayList<>();
        words.add("   Word 1   ");
        words.add("   Quaffle  ");
        words.add("   flowers  ");
        words.add("   freedom  ");
        words.add("   sports   ");
        words.add("    ride    ");
        words.add("   Kuggle   ");
        words.add("Hamentaschen");
        words.add("   Word 6   ");
        words.add("    Hoop    ");
        words.add("    Broom   ");
        words.add("    Stick   ");
        words.add("    green   ");
        words.add("   yellow   ");
        words.add("     16     ");
        words.add("   coolio   ");
        words.add("   Word 13  ");
        words.add("     14     ");
        words.add("    right   ");
        words.add("     22     ");
        words.add("     21     ");
        words.add("    hello   ");
        words.add("     19     ");
        words.add("     24     ");
        words.add("   purple   ");
        wordList.formatWords();
        assertEquals(words, wordList.getWords());
    }
}

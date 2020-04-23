package test;

import main.model.Board;
import main.model.Location;
import main.ui.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void runBefore() {
        board = new Board();
    }

    @Test
    public void testConstructor() {
        assertEquals(board.getTiles().size(), 25);
        assertEquals(board.getWords().size(), 25);
    }

    @Test
    public void testSelectNewWords() {
        for (int i = 0; i < 1000; i++) {
            board.selectNewWords();
            assertEquals(board.getWords().size(), 25);
        }
    }

    @Test
    public void testLoadRandomTileSet() {
        board.loadNewBoard();
        ArrayList<Location> tiles = board.getTiles();
        assertEquals(tiles.size(), 25);

        int blueTotal = 0;
        int redTotal = 0;
        int neutralTotal = 0;
        int blackTotal = 0;

        for (Location tile : tiles) {
            if (tile.getMasterColor().equals(Colors.RED_MASTER)) {
                redTotal++;
            } else if (tile.getMasterColor().equals(Colors.BLUE_MASTER)) {
                blueTotal++;
            } else if (tile.getMasterColor().equals(Colors.NEUTRAL_MASTER)) {
                neutralTotal++;
            } else if (tile.getMasterColor().equals(Colors.BLACK_MASTER)) {
                blackTotal++;
            }
        }
        assertEquals(blackTotal, 1);
        assertEquals(neutralTotal, 7);
        if (blueTotal > redTotal) {
            assertEquals(blueTotal, 9);
            assertEquals(redTotal, 8);
        } else if (redTotal >= blueTotal) {
            assertEquals(blueTotal, 8);
            assertEquals(redTotal, 9);
        }
    }
}

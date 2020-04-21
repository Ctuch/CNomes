package test;

import main.model.Board;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void runBefore() {
        board = new Board();
    }

    @Test
    public void testConstructor() {}


}

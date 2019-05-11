package test;

import Wireworld.Cell;
import Wireworld.CellChecker;
import Wireworld.Grid;
import Wireworld.fileReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellCheckerTest {
    Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = fileReader.readFile("src/test/dane.txt");
    }

    @Test
    public void checkCell() {
        assertEquals(CellChecker.checkCell(0, 0, grid), Cell.State.EMPTY);

        assertEquals(CellChecker.checkCell(2, 3, grid), Cell.State.HEAD);
        assertEquals(CellChecker.checkCell(2, 1, grid), Cell.State.HEAD);
    }
}
package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.GameOfLife.GameOfLifeCell;
import Wireworld.Core.GameOfLife.GameOfLifeCellChecker;
import Wireworld.Core.Grid;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeCellCheckerTest {

    @Test
    public void checkCell() {
        AbstractCell[][] grid = new AbstractCell[3][3];

        for(int i = 0; i < 3; i++) {
            grid[0][i] = new GameOfLifeCell(0);
            grid[2][i] = new GameOfLifeCell(0);

        }
        grid[1][0] = new GameOfLifeCell(1);
        grid[1][1] = new GameOfLifeCell(1);
        grid[1][2] = new GameOfLifeCell(1);

        Grid g = new Grid(grid);
        GameOfLifeCellChecker checker = new GameOfLifeCellChecker();

        assertEquals(1, checker.checkCell(0, 0, g));
        assertEquals(1, checker.checkCell(2, 2, g));
        assertEquals(1, checker.checkCell(1, 1, g));

    }
}
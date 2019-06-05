package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.GameOfLife.GameOfLifeCell;
import Wireworld.Core.GameOfLife.GameOfLifeCellChecker;
import Wireworld.Core.Grid;
import Wireworld.Core.WireWorld.WireworldCell;
import Wireworld.Core.WireWorld.WireworldCellChecker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WireworldCellCheckerTest {

    @Test
    public void checkCell() {
        AbstractCell[][] grid = new AbstractCell[3][3];

        for(int i = 0; i < 3; i++) {
            grid[0][i] = new WireworldCell(0);
            grid[2][i] = new WireworldCell(0);

        }
        grid[1][0] = new WireworldCell(3);
        grid[1][1] = new WireworldCell(1);
        grid[1][2] = new WireworldCell(2);

        Grid g = new Grid(grid);
        WireworldCellChecker checker = new WireworldCellChecker();

        assertEquals(1, checker.checkCell(1, 0, g));
        assertEquals(2, checker.checkCell(1, 1, g));
        assertEquals(3, checker.checkCell(1, 2, g));
        assertEquals(0, checker.checkCell(0, 0, g));

    }
}
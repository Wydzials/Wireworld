package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.Grid;
import Wireworld.Core.WireWorld.WireworldCell;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void copyGrid() {
        AbstractCell[][] grid = new AbstractCell[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                grid[i][j] = new WireworldCell();
        Grid g = new Grid(grid);

        g.getGridTmp()[0][0].setState(0);
        g.getGridTmp()[0][1].setState(2);
        g.getGridTmp()[0][2].setState(1);

        g.getGridTmp()[1][0].setState(3);
        g.getGridTmp()[1][1].setState(1);
        g.getGridTmp()[1][2].setState(2);

        g.getGridTmp()[2][0].setState(0);
        g.getGridTmp()[2][1].setState(0);
        g.getGridTmp()[2][2].setState(1);

        g.copyGrid();

        assertEquals(0,g.getGrid()[0][0].getState());
        assertEquals(1,g.getGrid()[0][2].getState());
        assertEquals(3,g.getGrid()[1][0].getState());
        assertEquals(0,g.getGrid()[2][0].getState());
        assertEquals(1,g.getGrid()[2][2].getState());
    }
}
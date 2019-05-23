package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.Grid;
import Wireworld.Core.Simulation;
import Wireworld.Core.WireWorld.WireworldCell;
import Wireworld.Core.WireWorld.WireworldCellChecker;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimulationTest {

    @Test
    public void nextGeneration() {
        AbstractCell[][] grid = new AbstractCell[3][3];
        AbstractCell[][] expectedGrid = new AbstractCell[3][3];

        for(int i = 0; i < 3; i++) {
            grid[0][i] = new WireworldCell(0);
            grid[2][i] = new WireworldCell(0);

            expectedGrid[0][i] = new WireworldCell(0);
            expectedGrid[2][i] = new WireworldCell(0);
        }
        grid[1][0] = new WireworldCell(2);
        grid[1][1] = new WireworldCell(1);
        grid[1][2] = new WireworldCell(3);

        expectedGrid[1][0] = new WireworldCell(3);
        expectedGrid[1][1] = new WireworldCell(2);
        expectedGrid[1][2] = new WireworldCell(1);

        Simulation simulation = new Simulation(new Grid(grid), new WireworldCellChecker());
        simulation.nextGeneration();

        assertArrayEquals(simulation.getGrid().getGrid(), expectedGrid);
    }

    @Test
    public void loadGridFromFile() {
    }
}
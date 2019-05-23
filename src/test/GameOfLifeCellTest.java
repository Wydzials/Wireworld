package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.GameOfLife.GameOfLifeCell;
import Wireworld.Core.WireWorld.WireworldCell;
import com.sun.javafx.binding.SelectBinding;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeCellTest {

    @Test
    public void cloneTest() {
        GameOfLifeCell cell = new GameOfLifeCell(1);
        AbstractCell clonedCell = cell.clone();
        assertEquals(cell, clonedCell);
    }
}
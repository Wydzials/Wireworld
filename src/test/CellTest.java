package test;

import Wireworld.Cell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    private Cell cell;

    @Before
    public void setup() { cell = new Cell(); }

    @Test
    public void getState() {
        assertEquals(cell.getState(), Cell.State.EMPTY);
    }

    @Test
    public void setStateFromState() {
        cell.setState(Cell.State.CONDUCTOR);
        assertEquals(cell.getState(), Cell.State.CONDUCTOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setStateFromInt() {
        cell.setState(1);
        assertEquals(cell.getState(), Cell.State.HEAD);
        cell.setState(10);
    }

    @Test
    public void toStringTest() {
        assertEquals(cell.toString(), "0");
    }
}
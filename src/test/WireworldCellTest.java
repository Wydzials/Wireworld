package test;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.WireWorld.WireworldCell;
import org.junit.Test;

import static org.junit.Assert.*;

public class WireworldCellTest {

    @Test
    public void cloneTest() {
        WireworldCell cell = new WireworldCell(2);
        AbstractCell clonedCell = cell.clone();
        assertEquals(cell, clonedCell);
    }
}
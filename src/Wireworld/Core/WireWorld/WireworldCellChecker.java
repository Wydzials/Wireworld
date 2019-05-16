package Wireworld.Core.WireWorld;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.Grid;
import Wireworld.Core.ICellChecker;

public class WireworldCellChecker implements ICellChecker {

    public int checkCell(int row, int col, Grid grid){
        if(grid.getGrid()[row][col].getState() == 0)
            return 0;
        else if(grid.getGrid()[row][col].getState() == 1)
            return 2;
        else if(grid.getGrid()[row][col].getState() == 2)
            return 3;
        else {
            int numOfElectrons = 0;
            for(int i=-1; i<2; i++)
                for(int j=-1; j<2; j++) {
                    if (grid.getGrid()[(i + row + grid.getRows()) % grid.getRows()]
                            [(j + col + grid.getColumns()) % grid.getColumns()].getState() == 1)
                        numOfElectrons++;
                }
            if( numOfElectrons == 1 || numOfElectrons == 2)
                return 1;
            else
                return 3;
        }
    }

    public AbstractCell makeNewCell() {
        return new WireworldCell();
    }
}

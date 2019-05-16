package Wireworld.Core.GameOfLife;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.Grid;
import Wireworld.Core.ICellChecker;

public class GameOfLifeCellChecker implements ICellChecker {
    @Override
    public int checkCell(int row, int col, Grid grid) {
        int alive = 0;
        for(int i=-1; i < 2; i++)
            for(int j=-1; j < 2; j++) {
                if (grid.getGrid()[(i + row + grid.getRows()) % grid.getRows()][(j + col + grid.getColumns()) % grid.getColumns()].getState() == 1)
                    alive++;
            }
        if(alive == 3)
            return 1;
        else if(alive == 4)
            return grid.getGrid()[row][col].getState();
        else
            return 0;
    }

    @Override
    public AbstractCell createNewCell() {
        return new GameOfLifeCell();
    }
}

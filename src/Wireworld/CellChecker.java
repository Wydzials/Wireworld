package Wireworld;

public class CellChecker implements ICellChecker {
    public static Cell.State checkCell(int row, int col, Grid grid){
        if(grid.getGrid()[row][col].getState() == Cell.State.EMPTY)
            return Cell.State.EMPTY;
        else if(grid.getGrid()[row][col].getState() == Cell.State.HEAD)
            return Cell.State.TAIL;
        else if(grid.getGrid()[row][col].getState() == Cell.State.TAIL)
            return Cell.State.CONDUCTOR;
        else {
            int numOfElectrons = 0;
            for(int i=-1; i<2; i++)
                for(int j=-1; j<2; j++) {
                    if (grid.getGrid()[(i + row + grid.getSizeY()) % grid.getSizeY()]
                            [(j + col + grid.getSizeX()) % grid.getSizeX()].getState() == Cell.State.HEAD)
                        numOfElectrons++;
                }
            if( numOfElectrons == 1 || numOfElectrons == 2)
                return Cell.State.HEAD;
            else
                return Cell.State.CONDUCTOR;
        }
    }
}

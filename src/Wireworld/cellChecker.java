public class cellChecker implements ICellChecker {
    public static char checkCell(int row, int col, gridContainer grid){
        if(grid.getGrid()[row][col] == '0')
            return '0';
        else if(grid.getGrid()[row][col] == '1')
            return '2';
        else if(grid.getGrid()[row][col] == '2')
            return '3';
        else{
            int numOfElectrons = 0;
            for(int i=-1; i<2; i++)
                for(int j=-1; j<2; j++) {
                    if (grid.getGrid()[(i + row + grid.getSizeY()) % grid.getSizeY()][(j + col + grid.getSizeX()) % grid.getSizeX()] == '1')
                        numOfElectrons++;
                }
            if( numOfElectrons == 1 || numOfElectrons == 2)
                return '1';
            else
                return '3';
        }
    }
}

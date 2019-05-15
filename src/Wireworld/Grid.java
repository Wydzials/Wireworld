package Wireworld;

public class Grid {

    private int columns;
    private int rows;
    private AbstractCell[][] grid;
    private AbstractCell[][] gridTmp;

    public Grid(int columns, int rows, AbstractCell[][] grid){
        this.grid = grid;
        this.columns = columns;
        this.rows = rows;
        gridTmp = new AbstractCell[rows][columns];
        for(int i = 0; i < columns; i++)
            for(int j = 0; j < rows; j++) {
                gridTmp[j][i] = grid[0][0].clone();
            }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public AbstractCell[][] getGrid() {
        return grid;
    }

    public AbstractCell[][] getGridTmp() { return gridTmp; }

    public AbstractCell getCell(int row, int column) {
        return grid[row][column];
    }

    public void copyGrid(){
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                grid[i][j].setState(gridTmp[i][j].getState());
    }

    public void printGrid(){
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void resize(int targetRows, int targetColumns) {
        if(targetRows == rows && targetColumns == columns)
            return;
        AbstractCell[][] newGrid = new AbstractCell[targetRows][targetColumns];
        AbstractCell[][] newGridTmp = new AbstractCell[targetRows][targetColumns];

        for(int row = 0; row < targetRows; row++) {
            for (int column = 0; column < targetColumns; column++) {
                if (row < rows && column < columns)
                    newGrid[row][column] = grid[row][column].clone();
                else
                    newGrid[row][column] = grid[0][0].emptyClone();
                newGridTmp[row][column] = grid[0][0].emptyClone();
            }
        }

        grid = newGrid;
        gridTmp = newGridTmp;

        rows = targetRows;
        columns = targetColumns;
    }

    public void clear() {
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++)
                grid[j][i] = grid[0][0].emptyClone();
    }

}

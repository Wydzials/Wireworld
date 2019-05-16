package Wireworld.Core;

public class Grid {

    private int columns;
    private int rows;
    private AbstractCell[][] grid;
    private AbstractCell[][] gridTmp;

    public Grid(AbstractCell[][] grid){
        this.grid = grid;
        this.columns = grid[0].length;
        this.rows = grid.length;
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                sb.append(grid[i][j] + " ");
            sb.append('\n');
        }
        sb.append('\n');
        return sb.toString();
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

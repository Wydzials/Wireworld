package Wireworld;

public class Grid {
    private int columns;
    private int rows;
    private Cell[][] grid;
    private Cell[][] gridTmp;

    public Grid(int columns, int rows, Cell[][] grid){
        this.grid = grid;
        this.columns = columns;
        this.rows = rows;
        gridTmp = new Cell[rows][columns];
        for(int i = 0; i < columns; i++)
            for(int j = 0; j < rows; j++) {
                gridTmp[j][i] = new Cell();
            }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell[][] getGridTmp() {
        return gridTmp;
    }

    public Cell getCell(int row, int column) {
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

}

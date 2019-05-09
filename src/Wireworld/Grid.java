package Wireworld;

public class Grid {
    private int sizeX;
    private int sizeY;
    private Cell[][] grid;
    private Cell[][] gridTmp;

    public Grid(int sizeX, int sizeY, Cell[][] grid){
        this.grid = grid;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        gridTmp = new Cell[sizeY][sizeX];
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY; j++) {
                gridTmp[j][i] = new Cell();
            }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell[][] getGridTmp() {
        return gridTmp;
    }

    public Cell getCell(int x, int y) {
        if(x < 0 || x > sizeX || y < 0 || y > sizeY)
            return null;
        return grid[x][y];
    }

    public void copyGrid(){
        for(int i=0; i < sizeY; i++)
            for(int j=0; j < sizeX; j++)
                grid[i][j] = gridTmp[i][j];
    }

    public void printGrid(){
        for(int i=0; i<sizeY; i++) {
            for (int j = 0; j < sizeX; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}

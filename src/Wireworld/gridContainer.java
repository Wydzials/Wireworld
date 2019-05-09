public class gridContainer {
    private int sizeX;
    private int sizeY;
    private char[][] grid;
    private char[][] gridTmp;

    public gridContainer(int sizeX, int sizeY, char[][] grid){
        this.grid = grid;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        gridTmp = new char[sizeY][sizeX];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public char[][] getGrid() {
        return grid;
    }

    public char[][] getGridTmp() {
        return gridTmp;
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

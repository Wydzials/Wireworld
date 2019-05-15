package Wireworld;

public interface ICellChecker {
    public int checkCell(int row, int col, Grid grid);
    public AbstractCell makeNewCell();
}

package Wireworld.Core;

public interface ICellChecker {
    int checkCell(int row, int col, Grid grid);
    AbstractCell makeNewCell();
}

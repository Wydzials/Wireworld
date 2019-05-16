package Wireworld.GUI;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.Grid;
import Wireworld.Core.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasUtils {
    public static void printGrid(Grid grid, double cellSize, GraphicsContext gc) {
        int columns = grid.getColumns();
        int rows = grid.getRows();
        Main.controller.canvas.setWidth(cellSize * columns);
        Main.controller.canvas.setHeight(cellSize * rows);

        gc.clearRect(0, 0, 2000, 2000);
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++) {
                    printCell(i, j, cellSize, gc, grid.getCell(i, j));
            }
    }

    public static void printCell(int row, int column, double size, GraphicsContext gc, AbstractCell cell) {
        gc.setFill(cell.getColor());


        gc.setStroke(Color.DARKGREY);
        gc.setLineWidth(size / 70 + 0.2);

        gc.fillRect(column*size, row*size, size, size);
        gc.strokeRect(column*size, row*size, size, size);
    }
}

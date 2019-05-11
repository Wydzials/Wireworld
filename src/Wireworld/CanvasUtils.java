package Wireworld;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasUtils {
    public static  void printGrid(Grid grid, int cellSize, GraphicsContext gc) {
        int columns = grid.getColumns();
        int rows = grid.getRows();
        Main.controller.canvas.setWidth(cellSize * columns);
        Main.controller.canvas.setHeight(cellSize * rows);

        gc.clearRect(0, 0, 2000, 2000);
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++) {
                printCell(i, j, cellSize, gc, grid.getCell(i, j), false);
            }
    }

    public static void printCell(int row, int column, int size, GraphicsContext gc, Cell cell, boolean highlighted) {
        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW};
        Color[] lightColors = {Color.GREY, Color.LIGHTBLUE, Color.LIGHTSALMON, Color.LIGHTYELLOW};
        if(!highlighted)
            gc.setFill(colors[cell.getState().ordinal()]);
        else
            gc.setFill(lightColors[cell.getState().ordinal()]);

        gc.setStroke(Color.DARKGREY);
        gc.setLineWidth(1);

        gc.fillRect(column*size, row*size, size, size);
        gc.strokeRect(column*size, row*size, size, size);
    }
}

package Wireworld.Core;

import java.io.IOException;

public class Simulation {

    private Grid grid;
    private ICellChecker cellChecker;
    private int currentGeneration = 1;
    private int population;

    public Simulation(Grid grid, ICellChecker cellChecker) {
        this.grid = grid;
        this.cellChecker = cellChecker;
    }

    public Simulation(String path) {
        loadGridFromFile(path);
        this.cellChecker = cellChecker;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public int getPopulation() {
        countPopulation();
        return population;
    }

    public synchronized void nextGeneration() {
        for (int k = 0; k < grid.getRows(); k++) {
            for (int l = 0; l < grid.getColumns(); l++) {
                grid.getGridTmp()[k][l].setState(cellChecker.checkCell(k, l, grid));
            }
        }
        grid.copyGrid();
        currentGeneration++;
    }

    public void loadGridFromFile(String path) {
        try {
            grid = FileIO.readFile(path, cellChecker);
        } catch (IOException e) {
            System.err.println("Nie istnieje podany plik");
        } catch (BlankFileException e) {
            System.err.println("Podano pusty plik");
        }
        currentGeneration = 1;
    }

    public Grid getGrid() {
        return grid;
    }

    private void countPopulation() {
        population = 0;
        for (int i = 0; i < grid.getRows(); i++)
            for (int j = 0; j < grid.getColumns(); j++)
                if (grid.getCell(i, j).getState() != 0)
                    population++;
    }

    public void clear() {
        grid.clear();
        currentGeneration = 1;
    }
}

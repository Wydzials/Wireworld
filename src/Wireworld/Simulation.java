package Wireworld;

import java.io.IOException;

public class Simulation extends Thread implements ISimulation{

    private Grid grid;
    private int currentGeneration = 1;
    private int targetGenerations;
    private int population;
    private int delay;
    private boolean isPaused;

    public Simulation(Grid grid) {
        this.grid = grid;
        isPaused = true;
    }
    public Simulation(String path) {
        loadGridFromFile(path);
        isPaused = true;
    }

    public int getCurrentGeneration() {
        return currentGeneration;
    }

    public int getTargetGeneration() {
        return currentGeneration;
    }

    public int getPopulation() {
        countPopulation();
        return population;
    }

    public int getDelay() { return delay; }
    public void setDelay(int delay) { this.delay = delay; }

    public boolean isPaused() { return isPaused; }
    public void setPaused(boolean paused) { isPaused = paused; }


    public void nextGeneration() {
        for(int k = 0; k<grid.getRows(); k++){
            for(int l = 0; l<grid.getColumns(); l++){
                grid.getGridTmp()[k][l].setState(CellChecker.checkCell(k,l,grid));
            }
        }
        grid.copyGrid();
        currentGeneration++;
        targetGenerations--;
    }

    public void loadGridFromFile(String path) {
        try {
            grid = fileReader.readFile(path);
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
                if (grid.getCell(i, j).getState() != Cell.State.EMPTY)
                    population++;
    }

    public void clear() {
        grid.clear();
        currentGeneration = 1;
    }
}

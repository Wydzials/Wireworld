package Wireworld;

import java.io.IOException;

public class Simulation extends Thread implements ISimulation{

    private Grid grid;
    private int currentGeneration;
    private int totalGenerations;
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
        totalGenerations--;
    }

    public void loadGridFromFile(String path) {
        try {
            grid = fileReader.readFile(path);
        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }
        currentGeneration = 1;
    }

    public Grid getGrid() {
        return grid;
    }
}

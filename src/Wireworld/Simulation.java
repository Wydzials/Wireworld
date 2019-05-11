package Wireworld;

import java.io.IOException;

public class Simulation implements ISimulation{
    Grid grid;
    int generation;
    public Simulation(Grid grid) { this.grid = grid; }
    public Simulation(String path) {
        loadGridFromFile(path);
    }

    public void simulator(IOptions opt, Grid grid){
        for(int i=0; i<opt.getNumOfGenerations(); i++){
            grid.printGrid();
            for(int k = 0; k<grid.getRows(); k++){
                for(int l = 0; l<grid.getColumns(); l++){
                    grid.getGridTmp()[k][l].setState(CellChecker.checkCell(k,l,grid));
                }
            }
            generation++;
            grid.copyGrid();
        }
    }

    public void nextGeneration() {
        for(int k = 0; k<grid.getRows(); k++){
            for(int l = 0; l<grid.getColumns(); l++){
                grid.getGridTmp()[k][l].setState(CellChecker.checkCell(k,l,grid));
            }
        }
        grid.copyGrid();
        generation++;
    }

    public void loadGridFromFile(String path) {
        try {
            grid = fileReader.readFile(path);
        }catch (IOException e){
            System.err.println("Nie istnieje podany plik");
        }catch(BlankFileException e) {
            System.err.println("Podano pusty plik");
        }
    }
}

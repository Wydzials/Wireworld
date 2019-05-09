package Wireworld;

public class Simulation implements ISimulation{
    public void simulator(IOptions opt, Grid grid){
        for(int i=0; i<opt.getNumOfGenerations(); i++){
            grid.printGrid();
            for(int k=0; k<grid.getSizeY(); k++){
                for(int l=0; l<grid.getSizeX(); l++){
                    grid.getGridTmp()[k][l].setState(CellChecker.checkCell(k,l,grid));
                }
            }
            grid.copyGrid();
        }
    }
}

public class simulation implements ISimulation{
    public void simulator(IOptions opt, gridContainer grid){
        for(int i=0; i<opt.getNumOfGenerations(); i++){
            grid.printGrid();
            for(int k=0; k<grid.getSizeY(); k++){
                for(int l=0; l<grid.getSizeX(); l++){
                    grid.getGridTmp()[k][l] = cellChecker.checkCell(k,l,grid);
                }
            }
            grid.copyGrid();
        }
    }
}

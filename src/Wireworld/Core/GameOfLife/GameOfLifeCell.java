package Wireworld.Core.GameOfLife;

import Wireworld.Core.AbstractCell;
import javafx.scene.paint.Color;

public class GameOfLifeCell extends AbstractCell {

    public GameOfLifeCell(){
        super();
    }

    public GameOfLifeCell(int state){
        super(state);
    }

    @Override
    public AbstractCell clone() {
        return new GameOfLifeCell(state);
    }

    @Override
    public AbstractCell emptyClone() {
        return new GameOfLifeCell();
    }

    @Override
    protected void setColors() {
    colors = new Color[2];
    colors[0] = Color.BLACK;
    colors[1] = Color.WHITE;
    }
}

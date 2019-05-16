package Wireworld.Core.GameOfLife;

import Wireworld.Core.AbstractCell;
import Wireworld.Core.WireWorld.WireworldCell;
import javafx.scene.paint.Color;

public class GameOfLifeCell extends AbstractCell {

    public GameOfLifeCell(){
        super();
        setColors();
    }

    public GameOfLifeCell(int state){
        super(state);
        setColors();
    }

    @Override
    public AbstractCell clone() {
        return new WireworldCell(state);
    }

    @Override
    public AbstractCell emptyClone() {
        return new WireworldCell();
    }

    @Override
    protected void setColors() {
    colors = new Color[2];
    colors[0] = Color.BLACK;
    colors[1] = Color.WHITE;
    }
}

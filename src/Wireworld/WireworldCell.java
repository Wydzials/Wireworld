package Wireworld;

import javafx.scene.paint.Color;

public class WireworldCell extends AbstractCell {

    public WireworldCell() {
        super();
        setColors();
    }
    public WireworldCell(int state) {
        super(state);
        setColors();
    }

    protected void setColors() {
        colors = new Color[4];
        colors[0] = Color.BLACK;
        colors[1] = Color.BLUE;
        colors[2] = Color.RED;
        colors[3] = Color.YELLOW;
    }

    @Override
    public WireworldCell clone() {
        return new WireworldCell(state);
    }

    @Override
    public WireworldCell emptyClone() {
        return new WireworldCell();
    }
}
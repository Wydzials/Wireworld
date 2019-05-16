package Wireworld.Core;

import javafx.scene.paint.Color;

public abstract class AbstractCell implements Cloneable {

    protected int state;
    protected static Color[] colors;

    public AbstractCell() {
        state = 0;
    }

    public AbstractCell(int state) {
        setState(state);
    }

    public int getState() {
        return state;
    }

    public void setState(int s) {
        state = s;
    }

    public Color getColor() {
        return colors[state];
    }

    @Override
    public abstract AbstractCell clone();

    public abstract AbstractCell emptyClone();

    protected abstract void setColors();

    @Override
    public String toString() {
        return Integer.toString(state);
    }
}

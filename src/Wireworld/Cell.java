package Wireworld;

public class Cell {
    public enum State {EMPTY, HEAD, TAIL, CONDUCTOR};
    private State state;

    public Cell() { state = State.EMPTY; }

    public Cell(State state) { this.state = state; }

    public Cell(int s) { setState(s); }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public void setState(int s) {
        if(s >= 0 && s < State.values().length)
            this.state = State.values()[s];
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return Integer.toString(state.ordinal());
    }

}

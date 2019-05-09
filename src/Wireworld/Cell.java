package Wireworld;

public class Cell {
    public enum State {EMPTY, HEAD, TAIL, CONDUCTOR};
    private State state;

    Cell() {
        state = State.EMPTY;
    }
    Cell(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return Integer.toString(state.ordinal());
    }

}

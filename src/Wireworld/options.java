public class options implements IOptions{
    private int numOfGenerations;
    private int speedOfDepiction;

    public options(int numOfGenerations, int speedOfDepiction){
        this.numOfGenerations = numOfGenerations;
        this.speedOfDepiction = speedOfDepiction;
    }

    public int getNumOfGenerations() {
        return numOfGenerations;
    }

    public int getSpeedOfDepiction() {
        return speedOfDepiction;
    }
}

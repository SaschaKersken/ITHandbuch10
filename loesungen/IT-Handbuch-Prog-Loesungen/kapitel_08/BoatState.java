public class BoatState {
    public static int START = 0;
    public static int GOAL = 1;

    private int wolf = START;
    private int sheep = START;
    private int cabbage = START;
    private int boat = START;

    public BoatState() {
        // Nothing to do
    }

    public BoatState(int wolf, int sheep, int cabbage, int boat) {
        this.wolf = wolf;
        this.sheep = sheep;
        this.cabbage = cabbage;
        this.boat = boat;
    }

    public int getWolf() {
        return this.wolf;
    }

    public int getSheep() {
        return this.sheep;
    }

    public int getCabbage() {
        return this.cabbage;
    }

    public int getBoat() {
        return this.boat;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other && other != null) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        BoatState otherBS = (BoatState) other;
        if (this.wolf == otherBS.getWolf() && this.sheep == otherBS.getSheep()
            && this.cabbage == otherBS.getCabbage() && this.boat == otherBS.getBoat()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.wolf << 3 + this.sheep << 2 + this.cabbage << 1 + this.boat;
    }

    @Override
    public String toString() {
        String result = "- Wolf am " + (this.wolf == START ? "Start" : "Ziel") + "ufer\n";
        result += "- Schaf am " + (this.sheep == START ? "Start" : "Ziel") + "ufer\n";
        result += "- Kohl am " + (this.cabbage == START ? "Start" : "Ziel") + "ufer\n";
        result += "- Boot am " + (this.boat == START ? "Start" : "Ziel") + "ufer";
        return result;
    }
}

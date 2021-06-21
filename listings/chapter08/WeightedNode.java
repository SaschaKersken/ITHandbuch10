public class WeightedNode<T> extends Node<T> implements Comparable<WeightedNode<T>> {
    private double cost = 0.0;
    private double heuristic = 0.0;

    public WeightedNode(T state) {
        super(state);
    }

    public WeightedNode(T state, WeightedNode<T> parent) {
        super(state, parent);
    }

    public WeightedNode(T state, WeightedNode<T> parent, double cost, double heuristic) {
        super(state, parent);
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public double getCost() {
        return this.cost;
    }

    public double getHeuristic() {
        return this.heuristic;
    }

    @Override
    public int compareTo(WeightedNode<T> other) {
        double difference = (this.cost + this.heuristic) -
            (other.getCost() + other.getHeuristic());
        if (difference > 0) {
            return 1;
        }
        if (difference < 0) {
            return -1;
        }
        return 0;
    }
}

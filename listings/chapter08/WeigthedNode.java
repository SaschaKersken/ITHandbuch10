public class WeightedNode<T> extends Node<T> implements Comparable {
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

    public doble getCost() {
        return this.cost;
    }

    public double getHeuristic() {
        return this.heuristic;
    }

    @Override
    public int compareTo(WeightedNode<T> other) {
        Double local = new Double(this.cost + this.heuristic);
        Double remote = new Double(other.getCost() + other.getHeuristic());
        return local.compareTo(remote);
    }

import java.util.List;

public interface PathSearchable<T> {
    public boolean isGoal(T potential);

    public List<T> successors(T current);

    public double heuristic(T potential);
}

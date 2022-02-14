public class PathLengthAwareNode<T> extends Node<T> implements Comparable<PathLengthAwareNode<T>> {
    private double pathLength;

    public PathLengthAwareNode(T state) {
        super(state);
        this.pathLength = 1;
    }

    public PathLengthAwareNode(T state, PathLengthAwareNode<T> parent) {
        super(state, parent);
        this.pathLength = parent.getPathLength() + 1;
    }

    public PathLengthAwareNode(T state, PathLengthAwareNode<T> parent, double pathLength) {
        super(state, parent);
        this.pathLength = pathLength;
    }

    public double getPathLength() {
        return this.pathLength;
    }

    @Override
    public int compareTo(PathLengthAwareNode<T> other) {
        Integer local = (int)this.pathLength;
        Integer remote = (int)other.getPathLength();
        return local.compareTo(remote);
    }
}

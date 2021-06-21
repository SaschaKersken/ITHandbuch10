import java.util.Deque;
import java.util.LinkedList;

public class Node<T> {
    private T state;
    private Node<T> parent = null;

    public Node(T state) {
        this.state = state;
    }

    public Node(T state, Node<T> parent) {
        this.state = state;
        this.parent = parent;
    }

    public T getState() {
        return this.state;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public Deque<T> toPath() {
        Deque<T> result = new LinkedList<>();
        result.addFirst(this.state);
        Node<T> current = this;
        while (current.getParent() != null) {
            current = current.getParent();
            result.addLast(current.getState());
        }
        return result;
    }
}

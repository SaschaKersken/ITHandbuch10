import java.util.Deque;

public class NodeTest {
    public static void main(String[] args) {
        Node<String> previous = null;
        Node<String> node = null;
        for (String s:args) {
            node = new Node<>(s, previous);
            previous = node;
        }
        if (node != null) {
            Deque<String> path = node.toPath();
            while (path.size() > 0) {
                System.out.println(path.removeFirst());
            }
        }
    }
}

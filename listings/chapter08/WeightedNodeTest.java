import java.util.PriorityQueue;

public class WeightedNodeTest {
    public static void main(String[] args) {
        WeightedNode<String> wn1 = new WeightedNode<>("Hello");
        WeightedNode<String> wn2 = new WeightedNode<>("World", wn1);
        WeightedNode<String> wn3 = new WeightedNode<>("what's", wn2, 2, 3);
        WeightedNode<String> wn4 = new WeightedNode<>("up?", wn3, 2, 4);
        PriorityQueue<WeightedNode<String>> pq = new PriorityQueue<>();
        pq.offer(wn3);
        pq.offer(wn1);
        pq.offer(wn4);
        pq.offer(wn2);
        while (pq.size() > 0) {
            System.out.println(pq.poll().getState());
        }
    }
}

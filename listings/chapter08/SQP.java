import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SQP {
    public static void main(String[] args) {
        int[] data = {7, 1, 4, 9, 6, 8, 5, 3, 2, 1};
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> queue = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // Add elements
        System.out.println("Original data");
        for (int element:data) {
            stack.addFirst(element);
            queue.addLast(element);
            priorityQueue.offer(element);
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.println("Stack (Last In First Out)");
        while (stack.size() > 0) {
            System.out.print(stack.removeFirst() + " ");
        }
        System.out.println();
        System.out.println("Queue (First In First Out)");
        while (queue.size() > 0) {
            System.out.print(queue.removeFirst() + " ");
        }
        System.out.println();
        System.out.println("Priority Queue (by priority)");
        while (priorityQueue.size() > 0) {
            System.out.print(priorityQueue.poll() + " ");
        }
        System.out.println();
    }
}

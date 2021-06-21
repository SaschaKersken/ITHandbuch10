import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PathSearch {
    public static <T> Node<T> dfs(T start, PathSearchable<T> searchable) {
        // Frontier als leeren Stack initialisieren
        Deque<Node<T>> frontier = new LinkedList<>();
        // Den Startknoten erzeugen und auf Frontier ablegen
        frontier.addFirst(new Node<T>(start));
        // Der Startknoten wurde bereits besucht
        Set<T> visited = new HashSet<>();
        visited.add(start);
        // Solange es noch Elemente in Frontier gibt
        while (frontier.size() > 0) {
            // Zuletzt hinzugefügtes Element aus Frontier holen
            Node<T> node = frontier.removeFirst();
            // Ziel erreicht? Dann aktuellen Knoten zurückgeben
            if (searchable.isGoal(node.getState())) {
                return node;
            }
            // Alle Nachfolger untersuchen
            for (T child:searchable.successors(node.getState())) {
                // Falls der Nachfolger schon besucht wurde, überspringen
                if (visited.contains(child)) {
                    continue;
                }
                // Nachfolger zur Liste der besuchten Zustände hinzufügen
                visited.add(child);
                // Aus dem Nachfolger einen Knoten erzeugen und auf Frontier legen
                frontier.addFirst(new Node<T>(child, node));
            }
        }
        // Nichts gefunden
        return null;
    }

    public static <T> Node<T> bfs(T start, PathSearchable<T> searchable) {
        // Frontier als leere Queue initialisieren
        Deque<Node<T>> frontier = new LinkedList<>();
        // Den Startknoten erzeugen und auf Frontier ablegen
        frontier.addLast(new Node<T>(start));
        // Der Startknoten wurde bereits besucht
        Set<T> visited = new HashSet<>();
        visited.add(start);
        // Solange es noch Elemente in Frontier gibt
        while (frontier.size() > 0) {
            // Zuletzt hinzugefügtes Element aus Frontier holen
            Node<T> node = frontier.removeFirst();
            // Ziel erreicht? Dann aktuellen Knoten zurückgeben
            if (searchable.isGoal(node.getState())) {
                return node;
            }
            // Alle Nachfolger untersuchen
            for (T child:searchable.successors(node.getState())) {
                // Falls der Nachfolger schon besucht wurde, überspringen
                if (visited.contains(child)) {
                    continue;
                }
                // Nachfolger zur Liste der besuchten Zustände hinzufügen
                visited.add(child);
                // Aus dem Nachfolger einen Knoten erzeugen und auf Frontier legen
                frontier.addLast(new Node<T>(child, node));
            }
        }
        // Nichts gefunden
        return null;
    }

    public static <T> WeightedNode<T> astar(T start, PathSearchable<T> searchable) {
        // Frontier als leere Prioritätswarteschlange initialisieren
        PriorityQueue<WeightedNode<T>> frontier = new PriorityQueue<>();
        // Den Startknoten erzeugen und auf Frontier ablegen
        frontier.offer(new WeightedNode<T>(start, null, 0.0, searchable.heuristic(start)));
        // Der Startknoten wurde bereits besucht
        Map<T, Double> visited = new HashMap<>();
        visited.put(start, 0.0);
        // Solange es noch Elemente in Frontier gibt
        while (frontier.size() > 0) {
            // Günstigstes Element aus Frontier holen
            WeightedNode<T> node = frontier.poll();
            // Ziel erreicht? Dann aktuellen Knoten zurückgeben
            if (searchable.isGoal(node.getState())) {
                return node;
            }
            // Alle Nachfolger untersuchen
            for (T child:searchable.successors(node.getState())) {
                // Kosten berechnen
                double childCost = node.getCost() + 1;
                // Falls Nachfolger schon besucht und Kosten nicht geringer, überspringen
                if (visited.containsKey(child) && visited.get(child) <= childCost) {
                    continue;
                }
                // Nachfolger hinzufügen oder Kosten aktualisieren
                visited.put(child, childCost);
                // Aus dem Nachfolger einen Knoten erzeugen und auf Frontier legen
                frontier.offer(new WeightedNode<T>(child, node, childCost, searchable.heuristic(child)));
            }
        }
        // Nichts gefunden
        return null;
    }
}

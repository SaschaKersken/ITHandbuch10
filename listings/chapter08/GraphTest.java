import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Deque;

public class GraphTest implements PathSearchable<String> {
    private Graph<String> graph;

    public GraphTest(Graph<String> graph) {
        this.graph = graph;
    }

    @Override
    public List<String> successors(String current) {
        return this.graph.getNeighbours(current);
    }

    @Override
    public boolean isGoal(String current) {
        return current.equals("C");
    }

    @Override
    public double heuristic(String potential) {
        // Wird hier nicht gebraucht
        return 0.0;
    }    

    public static void printEdges(Graph<String> graph, List<Edge> edges) {
        for (Edge edge:edges) {
            String from = graph.getVertex(edge.getFrom());
            String to = graph.getVertex(edge.getTo());
            if (from != null && to != null) {
                System.out.print(from + "-" + to + "  ");
            }
        }
        System.out.println();
    }

    public static void printVertices(List<String> vertices) {
        for (String vertex:vertices) {
            System.out.print(vertex + "  ");
        }
        System.out.println();
    }

    public static void printGraph(Graph<String> graph) {
        for (String vertex:graph.getVertices().values()) {
            System.out.print(vertex + ": ");
            printVertices(graph.getNeighbours(vertex));
        }
    }

    public static void printPath(Deque<String> path) {
        while (path.size() > 0) {
            System.out.print(path.removeLast() + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        GraphTest test = new GraphTest(graph);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdgeByVertices("A", "B");
        graph.addEdgeByVertices("A", "D");
        graph.addEdgeByVertices("B", "C");
        graph.addEdgeByVertices("B", "D");
        graph.addEdgeByVertices("B", "E");
        graph.addEdgeByVertices("C", "E");
        graph.addEdgeByVertices("D", "E");
        printGraph(graph);
        System.out.print("Von A ausgehende Kanten: ");
        printEdges(graph, graph.getEdgesFromVertex("A"));
        System.out.println();
        System.out.print("Von A nach C mit Tiefensuche: ");
        Node<String> aToC1 = PathSearch.dfs("A", test);
        printPath(aToC1.toPath());
        System.out.print("Von A nach C mit Breitensuche: ");
        Node<String> aToC2 = PathSearch.bfs("A", test);
        printPath(aToC2.toPath());
        System.out.println();
        System.out.println("Entferne Knoten D:");
        graph.removeVertex("D");
        printGraph(graph);
        System.out.print("Von A ausgehende Kanten: ");
        printEdges(graph, graph.getEdgesFromVertex("A"));        
    }
}

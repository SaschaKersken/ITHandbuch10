import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Deque;
import java.util.Arrays;

public class SocialNetwork implements PathSearchable<String> {
    private Graph<String> graph;
    private String goal;

    public SocialNetwork(Graph<String> graph, String goal) {
        this.graph = graph;
        this.goal = goal;
    }

    @Override
    public List<String> successors(String current) {
        return this.graph.getNeighbours(current);
    }

    @Override
    public boolean isGoal(String current) {
        return current.equals(this.goal);
    }

    @Override
    public double heuristic(String potential) {
        // Wird hier nicht gebraucht
        return 0.0;
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

    public static void printUsage(List<String> users) {
        System.out.println("java SocialNetwork USER1 USER2 MAX_CONNECTIONS");
        System.out.println("USER1 and USER2 have to be different and may be out of the following:");
        System.out.println(users);
    }

    public static void main(String[] args) {
        List<String> users = Arrays.asList(
            "Alex", "Betty", "Carl", "Dana", "Emil", "Frances", "Gary", "Hannah", "Ian", "Jenny"
        );
        if (args.length < 2 || !users.contains(args[0]) || !users.contains(args[1]) || args[0].equals(args[1])) {
            printUsage(users);
            System.exit(1);
        }
        int maxPaths = 1;
        if (args.length > 2) {
            maxPaths = Integer.parseInt(args[2]);
        }
        Graph<String> graph = new Graph<>();
        SocialNetwork social = new SocialNetwork(graph, args[1]);
        for (String user:users) {
            graph.addVertex(user);
        }
        graph.addEdgeByVertices("Alex", "Betty");
        graph.addEdgeByVertices("Alex", "Dana");
        graph.addEdgeByVertices("Alex", "Emil");
        graph.addEdgeByVertices("Alex", "Hannah");
        graph.addEdgeByVertices("Betty", "Jenny");
        graph.addEdgeByVertices("Betty", "Gary");
        graph.addEdgeByVertices("Betty", "Frances");
        graph.addEdgeByVertices("Carl", "Dana");
        graph.addEdgeByVertices("Carl", "Ian");
        graph.addEdgeByVertices("Carl", "Jenny");
        graph.addEdgeByVertices("Dana", "Gary");
        graph.addEdgeByVertices("Dana", "Hannah");
        graph.addEdgeByVertices("Dana", "Ian");
        graph.addEdgeByVertices("Dana", "Jenny");
        graph.addEdgeByVertices("Emil", "Frances");
        graph.addEdgeByVertices("Emil", "Hannah");
        graph.addEdgeByVertices("Emil", "Ian");
        graph.addEdgeByVertices("Frances", "Gary");
        graph.addEdgeByVertices("Frances", "Hannah");
        graph.addEdgeByVertices("Gary", "Ian");
        graph.addEdgeByVertices("Hannah", "Jenny");
        graph.addEdgeByVertices("Ian", "Jenny");
        System.out.println("Alle User und ihre Kontakte:");
        printGraph(graph);
        System.out.println();
        List<PathLengthAwareNode<String>> paths = PathSearch.multiplePaths(
            args[0], social, 10, maxPaths
        );
        System.out.println("Kontaktpfade zwischen " + args[0] + " und " + args[1] + ":");
        for (PathLengthAwareNode<String> path: paths) {
            printPath(path.toPath());
        }
    }
}

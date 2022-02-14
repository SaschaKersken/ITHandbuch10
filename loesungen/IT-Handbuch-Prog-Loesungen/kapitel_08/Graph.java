import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
* Graph mit Knoten eines generischen Typs
*/
public class Graph<T> {
    /**
    * Ganzzahlig indizierte Liste der Knoten
    */
    private Map<Integer, T> vertices;

    /**
    * Liste der Kanten
    */
    private List<Edge> edges;

    /**
    * Höchster bisher vergebener Knotenindex
    */
    private int maxIndex = 0;

    /**
    * Der Konstruktor initialisiert Knoten- und Kantenliste als leer
    */
    public Graph() {
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    /**
    * Alle Kanten auslesen
    *
    * @return Liste der Kanten
    */
    public List<Edge> getEdges() {
        return this.edges;
    }

    /**
    * Alle Knoten auslesen
    *
    * @return Map der Knoten
    */
    public Map<Integer, T> getVertices() {
        return this.vertices;
    }

    /**
    * Einen Knoten anhand seines Index ermitteln
    *
    * @param index Index des gewünschten Knotens
    * @return Knoten mit dem angegebenen Index oder null, falls nicht vorhanden
    */
    public T getVertex(int index) {
        if (this.vertices.containsKey(index)) {
            return this.vertices.get(index);
        }
        return null;
    }

    /**
    * Einen neuen Knoten hinzufügen
    *
    * @param Knoten
    * @return Nächster freier Index
    */
    public int addVertex(T vertex) {
        if (!this.vertices.containsValue(vertex)) {
            int index = this.maxIndex;
            this.vertices.put(this.maxIndex++, vertex);
            return index;
        }
        // Kein Knoten hinzugefügt
        return -1;
    }

    /**
    * Eine Kante anhand von Knoten-Indizes hinzufügen
    *
    * Die Kante wird stets in beide Richtungen angelegt
    *
    * @param from Index des Startknotens
    * @param to Index des Zielknotens
    */
    public void addEdge(int from, int to) {
        Edge fromEdge = new Edge(from, to);
        Edge toEdge = new Edge(to, from);
        if (!this.edges.contains(fromEdge)) {
            this.edges.add(fromEdge);
        }
        if (!this.edges.contains(toEdge)) {
            this.edges.add(toEdge);
        }
    }

    /**
    * Eine Kante anhand von Knoten-Werten hinzufügen
    *
    * Falls noch nicht vorhanden, werden auch die Knoten hinzugefügt
    *
    * @param fromVertex Startknoten
    * @param toVertex Zielknoten
    */
    public void addEdgeByVertices(T fromVertex, T toVertex) {
        int from = this.getIndexForVertex(fromVertex);
        if (from == -1) {
            from = this.addVertex(fromVertex);
        }
        int to = this.getIndexForVertex(toVertex);
        if (to == -1) {
            to = this.addVertex(toVertex);
        }
        this.addEdge(from, to);
    }

    /**
    * Den Index eines Knotens ermitteln
    *
    * @param vertex Gesuchter Knoten
    * @return Index oder -1, falls der Knoten nicht existiert
    */
    public int getIndexForVertex(T vertex) {
        if (!this.vertices.containsValue(vertex)) {
            return -1;
        }
        for (Map.Entry<Integer, T> entry:this.vertices.entrySet()) {
            if (entry.getValue().equals(vertex)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
    * Alle von einem Knoten ausgehenden Kanten ermitteln
    *
    * @param vertex Der Knoten, dessen Kanten gesucht sind
    * @return Liste der Kanten
    */
    public List<Edge> getEdgesFromVertex(T vertex) {
        List<Edge> result = new ArrayList<>();
        int index = this.getIndexForVertex(vertex);
        if (index > -1) {
            for (Edge edge:this.edges) {
                if (edge.getFrom() == index) {
                    result.add(edge);
                }
            }
        }
        return result;
    }

    /**
    * Alle direkt benachbarten Knoten eines Knotens ermitteln
    *
    * @param vertex Knoten, dessen Nachbarn gesucht sind
    * @return Liste der Knoten
    */
    public List<T> getNeighbours(T vertex) {
        List<T> result = new ArrayList<>();
        for (Edge edge:this.getEdgesFromVertex(vertex)) {
            result.add(this.vertices.get(edge.getTo()));
        }
        return result;
    }

    /**
    * Eine Kante entfernen
    *
    * @param edge Die zu entfernende Kante
    */
    public void removeEdge(Edge edge) {
        Edge reverse = new Edge(edge.getTo(), edge.getFrom());
        this.edges.remove(edge);
        this.edges.remove(reverse);
    }

    /**
    * Einen Knoten entfernen
    *
    * Zuerst werden alle mit dem Knoten verbundenen Kanten entfernt,
    * erst danach der Knoten selbst
    *
    * @param vertex Der zu entfernende Knoten
    */
    public void removeVertex(T vertex) {
        int index = this.getIndexForVertex(vertex);
        if (index > -1) {
            List<Edge> edges = this.getEdgesFromVertex(vertex);
            for (Edge edge:edges) {
                this.removeEdge(edge);
            }
            this.vertices.remove(index);
        }
    }
} 

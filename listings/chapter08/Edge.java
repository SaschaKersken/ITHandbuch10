public class Edge {
    private int from;
    private int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return this.from;
    }

    public int getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other && other != null) {
            return true;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Edge otherEdge = (Edge)other;
        if (this.from == otherEdge.getFrom() &&
            this.to == otherEdge.getTo()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.from * 1000 + this.to;
    }
}

import java.util.List;
import java.util.ArrayList;

public class Maze implements PathSearchable<GridLocation> {
    private char[][] data = null;
    private GridLocation startLocation = null;
    private GridLocation goalLocation = null;

    public void parse(String[] mazeSource) {
        int length = -1;
        int lineCounter = 0;
        for (String line:mazeSource) {
            if (length == -1) {
                length = line.length();
                this.data = new char[mazeSource.length][length];
            } else if (length != line.length()) {
                throw new IllegalArgumentException("Alle Zeilen benötigen die Länge " + length);
            }
            for (int i = 0; i < length; i++) {
                char current = line.charAt(i);
                switch (current) {
                case 'S':
                    this.startLocation = new GridLocation(lineCounter, i);
                    break;         
                case 'G':
                    this.goalLocation = new GridLocation(lineCounter, i);
                    break;
                case '+':
                case ' ':
                    break;
                default:
                    throw new IllegalArgumentException("Ungültiges Zeichen " + current);
                }
                this.data[lineCounter][i] = current;
            }
            lineCounter++;
        }
        if (this.startLocation == null || this.goalLocation == null) {
            throw new IllegalArgumentException("Start und/oder Ziel fehlt.");
        }
    }

    @Override
    public List<GridLocation> successors(GridLocation location) {
        int row = location.getRow();
        int column = location.getColumn();
        List<GridLocation> successors = new ArrayList<>();
        // Nachfolger unten?
        if (row < this.data.length - 1 && this.data[row + 1][column] != '+') {
            successors.add(new GridLocation(row + 1, column));
        }
        // Nachfolger oben?
        if (row > 0 && this.data[row - 1][column] != '+') {
            successors.add(new GridLocation(row - 1, column));
        }
        // Nachfolger rechts?
        if (column < this.data[0].length - 1 && this.data[row][column + 1] != '+') {
            successors.add(new GridLocation(row, column + 1));
        }
        // Nachfolger links?
        if (column > 0 && this.data[row][column - 1] != '+') {
            successors.add(new GridLocation(row, column - 1));
        }
        return successors;
    }

    @Override
    public boolean isGoal(GridLocation location) {
        return location.equals(this.goalLocation);
    }

    @Override
    public double heuristic(GridLocation location) {
        int rows = location.getRow() - this.goalLocation.getRow();
        int columns = location.getColumn() - this.goalLocation.getColumn();
        return Math.sqrt(Math.pow(rows, 2) + Math.pow(columns, 2));
    }

    public void print(List<GridLocation> path) {
        if (path != null) {
            for (GridLocation location:path) {
                if (!location.equals(this.startLocation) && !location.equals(this.goalLocation)) {
                    this.data[location.getRow()][location.getColumn()] = 'X';
                }
            }
        }
        for (char[] line:this.data) {
            for (char current:line) {
                System.out.print(current);
            }
            System.out.println();
        }
        if (path != null) {
            for (GridLocation location:path) {
                if (!location.equals(this.startLocation) && !location.equals(this.goalLocation)) {
                    this.data[location.getRow()][location.getColumn()] = ' ';
                }
            }
        }
    }

    public void print() {
        this.print(null);
    }

    public static void main(String[] args) {
        String[] mazeSource = new String[9];
        mazeSource[0] = "+++++++++";
        mazeSource[1] = "+S      +";
        mazeSource[2] = "+ +++++ +";
        mazeSource[3] = "+       +";
        mazeSource[4] = "+ ++ ++ +";
        mazeSource[5] = "+       +";
        mazeSource[6] = "+ +++++ +";
        mazeSource[7] = "+      G+";
        mazeSource[8] = "+++++++++";
        Maze maze = new Maze();
        maze.parse(mazeSource);
        maze.print();
        System.out.println();
        System.out.println("Tiefensuche");
        Node<GridLocation> solution1 = PathSearch.dfs(maze.startLocation, maze);
        if (solution1 != null) {
            maze.print(new ArrayList<GridLocation>(solution1.toPath()));
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
        System.out.println();
        System.out.println("Breitensuche");
        Node<GridLocation> solution2 = PathSearch.bfs(maze.startLocation, maze);
        if (solution2 != null) {
            maze.print(new ArrayList<GridLocation>(solution2.toPath()));
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
        System.out.println();
        System.out.println("A*-Suche");
        WeightedNode<GridLocation> solution3 = PathSearch.astar(maze.startLocation, maze);
        if (solution3 != null) {
            maze.print(new ArrayList<GridLocation>(solution3.toPath()));
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }
}

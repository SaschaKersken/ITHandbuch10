import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class EmptySudoku implements Constraint<GridLocation, Integer> {
    public boolean check(Map<GridLocation, Integer> assignment) {
        // Zeilen
        for (int row = 0; row < 9; row++) {
            List<Integer> data = new ArrayList<>();
            for (int column = 0; column < 9; column++) {
                GridLocation location = new GridLocation(row, column);
                if (assignment.containsKey(location)) {
                    int value = assignment.get(location);
                    if (data.contains(value)) {
                        return false;
                    }
                    data.add(value);
                }
            }
        }
        // Spalten
        for (int column = 0; column < 9; column++) {
            List<Integer> data = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                GridLocation location = new GridLocation(row, column);
                if (assignment.containsKey(location)) {
                    int value = assignment.get(location);
                    if (data.contains(value)) {
                        return false;
                    }
                    data.add(value);
                }
            }
        }
        // 3x3-Felder
        for (int startRow = 0; startRow <9; startRow += 3) {
            for (int startColumn = 0; startColumn < 9; startColumn += 3) {
                List<Integer> data = new ArrayList<>();
                for (int row = startRow; row < startRow + 3; row++) {
                    for (int column = startColumn; column < startColumn + 3; column++) {
                        GridLocation location = new GridLocation(row, column);
                        if (assignment.containsKey(location)) {
                            int value = assignment.get(location);
                            if (data.contains(value)) {
                                return false;
                            }
                            data.add(value);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void printSudoku(Map<GridLocation, Integer> assignment) {
        for (int row = 0; row < 9; row++) {
            System.out.println("+-+-+-+-+-+-+-+-+-+");
            System.out.print("|");
            for (int column = 0; column < 9; column++) {
                GridLocation location = new GridLocation(row, column);
                if (assignment.containsKey(location)) {
                    System.out.print(assignment.get(location));
                } else {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+");
    }
 
    public static void main(String[] args) {
        List<GridLocation> variables = new ArrayList<>();
        Map<GridLocation, List<Integer>> domains = new HashMap<>();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                GridLocation location = new GridLocation(row, column);
                variables.add(location);
                domains.put(location, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            }
        }
        EmptySudoku constraint = new EmptySudoku();
        CSP<GridLocation, Integer> sudokuCSP = new CSP<>(
            variables, domains, constraint
        );
        Map<GridLocation, Integer> solution = sudokuCSP.solve();
        if (solution != null) {
            printSudoku(solution);
        }
    }
}

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sudoku implements Constraint<GridLocation, Integer> {
    private static final List<Character> NUMBERS = Arrays.asList(
        '1', '2', '3', '4', '5', '6', '7', '8', '9'
    );

    @Override
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
 
    public static Map<GridLocation, Integer> parsePredefinition(
            Sudoku sudoku, String filename) {
        Map<GridLocation, Integer> predefinition = new HashMap<>();
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(filename)
            );
            String line = "";
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Datei nicht gefunden.");
            System.exit(1);
        } catch(IOException e) {
            System.out.println("Dateilesefehler.");
            System.exit(2);
        }
        if (lines.size() > 0) {
            int maxLine = 8;
            if (lines.size() < maxLine + 1) {
                maxLine = lines.size() - 1;
            }
            for (int i = 0; i <= maxLine; i++) {
                String line = lines.get(i);
                int maxChar = 8;
                if (line.length() < maxChar + 1) {
                    maxChar = line.length() - 1;
                }
                for (int j = 0; j <= maxChar; j++) {
                    char current = line.charAt(j);
                    if (NUMBERS.contains(current)) {
                        predefinition.put(
                            new GridLocation(i, j),
                            current - '0'
                        );
                    }
                }
            }
            if (!sudoku.check(predefinition)) {
                System.out.println("Konflikt in der Vorbelegung.");
                System.exit(3);
            }
        }
        return predefinition;
    }                     

    public static void main(String[] args) {
        List<GridLocation> variables = new ArrayList<>();
        Map<GridLocation, List<Integer>> domains = new HashMap<>();
        Map<GridLocation, Integer> predefinition = new HashMap<>();
        Sudoku constraint = new Sudoku();
        if (args.length > 0) {
            predefinition = parsePredefinition(constraint, args[0]);
        }
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                GridLocation location = new GridLocation(row, column);
                variables.add(location);
                if (predefinition.containsKey(location)) {
                    domains.put(location, Arrays.asList(predefinition.get(location)));
                } else {
                    domains.put(location, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                }
            }
        }
        CSP<GridLocation, Integer> sudokuCSP = new CSP<>(
            variables, domains, constraint
        );
        Map<GridLocation, Integer> solution = sudokuCSP.solve();
        if (solution != null) {
            printSudoku(solution);
        }
    }
}

import java.util.List;
import java.util.ArrayList;

public class BranchSortTest {
    public static void main(String[] args) {
        // Neues BranchSort-Objekt erzeugen
        BranchSort<Integer> branchSort = new BranchSort<>();
        // Argumente einsortieren, falls in int umwandelbar
        for (int i = 0; i < args.length; i++) {
            try {
                branchSort.addValue(Integer.parseInt(args[i]));
            } catch(NumberFormatException e) {
            }
        }
        // Den sortierten Baum ausgeben
        branchSort.getRoot().print();
        System.out.println();
        // Die sortierte Liste holen und elementweise ausgeben
        List<Integer> sortedIntList = branchSort.getSorted();
        for (int i:sortedIntList) {
            System.out.println(i);
        }
    }
}


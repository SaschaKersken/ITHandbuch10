import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericBubblesort<T extends Comparable<? super T>> {
    public List<T> bubblesort(List<T> list) {
        boolean isSorted;
        do {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    // Tauschen:
                    T temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    // Nicht sortiert!
                    isSorted = false;
                }
            }
        } while (!isSorted);
        return list;
    }

    public static void main (String args[]) {
        List<String> values = new ArrayList<>(Arrays.asList(args));
        GenericBubblesort<String> sorter = new GenericBubblesort<>();
        values = sorter.bubblesort(values);
        for (String value:values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

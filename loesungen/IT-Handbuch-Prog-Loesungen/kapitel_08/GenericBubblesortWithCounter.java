import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericBubblesortWithCounter<T extends Comparable<? super T>> {
    private int counter = 0;

    public List<T> bubblesort(List<T> list) {
        boolean isSorted;
        this.counter = 0;
        do {
            isSorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                this.counter++;
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

    public int getCounter() {
        return this.counter;
    }

    public static void main (String args[]) {
        List<String> values = new ArrayList<>(Arrays.asList(args));
        GenericBubblesortWithCounter<String> sorter = new GenericBubblesortWithCounter<>();
        values = sorter.bubblesort(values);
        for (String value:values) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.printf("%d Durchläufe.\n", sorter.getCounter());
    }
}

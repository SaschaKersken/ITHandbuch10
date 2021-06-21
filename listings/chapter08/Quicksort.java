public class Quicksort {
    static int[] quicksort(int[] list) {
        /* quicksort(int[]) ist nur ein bequemer Starter für die
           eigentliche Arbeitsmethode quicksort(int[], int, int).   */
        // quicksort(int[], int, int) mit dem gesamten Array aufrufen:
        quicksort (list, 0, list.length - 1);
        return list;
    }

    static void quicksort(int[] list, int low, int high) {
        int pivot;
        if (low < high) {
            // Vergleichselement Pivot wählen:
            pivot = list[high];
            int i = low - 1;
            int j = high;
            for ( ; ; ) {
                // Werte von beiden Enden aus vergleichen:
                while (list[++i] < pivot);
                while (j > 0 && list[--j] > pivot);
                // Schleife verlassen, falls sortiert:
                if (j <= i) {
                    break;
                }
                // Tauschen
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
            list[high] = list[i];
            list[i] = pivot;

            // Linke/rechte Partition sortieren:
            quicksort (list, low, i - 1);
            quicksort (list, i + 1, high);
        }
    }

    public static void main (String args[]) {
        int[] values = {3, 7, 1, 9, 2, 5, 2};
        values = quicksort(values);
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}

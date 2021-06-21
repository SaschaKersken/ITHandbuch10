public class Bubblesort {
    static int[] bubblesort(int[] list) {
        boolean isSorted;
        do {
            isSorted = true;
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1]) {
                    // Tauschen:
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    // Nicht sortiert!
                    isSorted = false;
                }
            }
        } while (!isSorted);
        return list;
    }

    public static void main (String args[]) {
        int[] values = {3, 7, 1, 9, 2, 5, 2};
        values = bubblesort(values);
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}

public class LinearSearchWithCounter {
    private static int counter = 0;

    static int linearSearch(int[] list, int value) {
        counter = 0;
        for (int i = 0; i < list.length; i++) {
            counter++;
            if (list[i] == value) {
                return i;
            }
        }
        // Nicht gefunden:
        return -1;
    }

    static int getCounter() {
        return counter;
    }

    public static void main (String args[]) {
        int numbers[] = {5, 1, 8, 3, 9, 2, 7};
        int searchValue = 2;
        int index = linearSearch(numbers, searchValue);
        if (index > -1) {
            System.out.printf(
                "%d kommt in der Liste an Position %d vor.\n",
                searchValue, index
            );
        } else {
            System.out.println(searchValue + " kommt nicht in der Liste vor.");
        }
        System.out.printf("%d Durchläufe.\n", getCounter());
    }
}

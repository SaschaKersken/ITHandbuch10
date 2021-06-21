public class LinearSearch {
    static int linearSearch(int[] list, int value) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == value) {
                return i;
            }
        }
        // Nicht gefunden:
        return -1;
    }

    public static void main (String args[]) {
        int numbers[] = {5, 1, 8, 3, 9, 2, 7};
        int searchValue = 14;
        int index = linearSearch(numbers, searchValue);
        if (index > -1) {
            System.out.printf(
                "%d kommt in der Liste an Position %d vor.\n",
                searchValue, index
            );
        } else {
            System.out.println(searchValue + " kommt nicht in der Liste vor.");
        }
    }
}

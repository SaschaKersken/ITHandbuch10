public class BinarySearch {
    public static int binarySearch(int[] list, int value) {
        //Initialisierung der eigentlichen Methode binarySearch(int[], int, int, int)
        return binarySearch(list, value, 0, list.length);
    }

    static int binarySearch(int[] list, int value, int low, int high) {    
        // Nicht gefunden?
        if (low > high) {
            return -1;
        }
        int m = (low + high) / 2;
        int median = list[m];
        if (value < median) {
            return binarySearch(list, value, low, m - 1);
        }
        if (value > median) {
            return binarySearch(list, value, m + 1, high);
        }
        // Gefunden!
        return m;
    }

    public static void printResult(int searchValue, int result) {
        if (result == -1) {
            System.out.println(searchValue + " nicht gefunden.");
        } else {
            System.out.printf("%d gefunden an Position %d.\n", searchValue, result);
        }
    }

    public static void main(String[] args) {
        int[] list = {1, 4, 7, 9, 11, 18, 21, 27, 31, 36, 52};
        printResult(31, binarySearch(list, 31));
        printResult(32, binarySearch(list, 32));
    }
}

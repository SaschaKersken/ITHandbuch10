public class Lambda1 {
    interface Comparison {
        boolean compare(int a, int b);
    }

    public static void main(String[] args) {
        Comparison isMultiple = (a, b) -> (a % b) == 0;
        Comparison isDivider = (a, b) -> (b % a) == 0;

        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        for (int i: values) {
            for (int j: values) {
                if (i != 1 && j != 1 && i != j) {
                    if (isMultiple.compare(i, j)) {
                        System.out.println(i + " ist Vielfaches von " + j);
                    }
                    if (isDivider.compare(i, j)) {
                        System.out.println(i + " ist Teiler von " + j);
                    }
                }
            }
        }
    }
}

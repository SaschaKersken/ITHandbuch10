public class ArrayTest {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = array1;
        array2[4] = 42;
        for (int i = 0; i < array1.length; i++) {
            System.out.print("\t" + array1[i]);
        }
        System.out.println();
    }
}

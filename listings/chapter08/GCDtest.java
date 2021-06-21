public class GCDtest {
    public static int gcd(int m, int n) {
        // Trivialer Fall: m und n gleich:
        if (m == n) {
            return m;
        }
        // Größeren und kleineren Wert ermitteln:
        int greater = (m > n) ? m : n;
        int smaller = (m < n) ? m : n;
        // Minimum aus smaller und greater / 2
        int maxDivisor = greater / 2 < smaller ? greater / 2 : smaller;
        // Jeden möglichen Teiler testen:
        for (int i = maxDivisor; i >= 2; i--) {
            // Sind beide Werte durch i teilbar?
            if (greater % i == 0 && smaller % i == 0) {
                return i;
            }
        }
        // Hier bleibt nur noch die 1:
        return 1;
    }

    public static void main(String args[]) {
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        System.out.printf(
            "Der GGT von %d und %d ist %d.\n", m, n, gcd(m, n)
        );
    }
}

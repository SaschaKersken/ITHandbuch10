public class EuclidGcdLcm {
    public static int gcd(int m, int n) {
        while (n != 0) {
            int temp = n;
            n = m % n;
            m = temp;
        }
        return m;
    }

    public static int lcm(int m, int n) {
        return m * n / gcd(m, n);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
             System.out.println("Verwendung: java EuclidGCD m n");
             System.exit(1);
        }
        int m = 0;
        int n = 0;
        try {
            m = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.out.println("Bitte nur Zahlen angeben");
            System.exit(2);
        }
        int gcd = gcd(m, n);
        int lcm = lcm(m, n);
        System.out.printf("GGT von %d und %d: %d\n", m, n, gcd);
        System.out.printf("KGV von %d und %d: %d\n", m, n, lcm);
    }
}

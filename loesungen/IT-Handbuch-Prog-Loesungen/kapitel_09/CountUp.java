public class CountUp extends Thread {
    public void run() {
        for (int i = 1; i <= 10000; i++) {
            System.out.println("Hochzähler ist bei " + i);
        }
    }
}

public class CountDown extends Thread {
    public void run() {
        for (int i = 10000; i >= 1; i--) {
            System.out.println("        Runterzähler ist bei " + i);
        }
    }
}

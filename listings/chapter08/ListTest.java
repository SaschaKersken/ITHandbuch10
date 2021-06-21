import java.util.List;
import java.util.ArrayList;

public class ListTest {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Verwendung: java ListTest integer [...]");
            System.exit(1);
        }
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            try {
                int value = Integer.parseInt(args[i]);
                intList.add(value);
                System.out.println("- Element hinzugefügt: " + value);
            } catch(NumberFormatException e) {
                System.out.println("! '" + args[i] +
                    "' ist kein gültiger Integer."
                );
            }
        }
        int sum = 0;
        for (int i:intList) {
            sum += i;
        }
        double average = (double)sum / intList.size();
        System.out.println();
        System.out.println("Anzahl Elemente: " + intList.size());
        System.out.println("Summe:           " + sum);
        System.out.println("Mittelwert:      " + average);
    }
}


public class ControlFlow {
    public static void main(String args[]) {
        int a = 101;
        if (a > 100)
            System.out.println("Herzlichen Glückwunsch!");
        int b = -99;
        if (b < 0) {
            b = 100;
            System.out.println("b auf 100 gesetzt.");
        }
        if (a > 0) {
            // Ausgabe: a ist positiv.
            System.out.println("a ist positiv.");
        } else {
            // Ausgabe: a ist nicht positiv.
            System.out.println("a ist nicht positiv.");
        }
        a = -1;
        if (a > 0) {
            // Ausgabe: a ist positiv.
            System.out.println("a ist positiv.");
        } else {
            // Ausgabe: a ist nicht positiv.
            System.out.println("a ist nicht positiv.");
        }
        a = 1;
        if (a > 0) {
            System.out.println("a ist positiv.");
        } else if (a < 0) {
            System.out.println("a ist negativ.");
        } else {
            System.out.println("a ist 0.");
        }
        a = -1;
        if (a > 0) {
            System.out.println("a ist positiv.");
        } else if (a < 0) {
            System.out.println("a ist negativ.");
        } else {
            System.out.println("a ist 0.");
        }
        a = 0;
        if (a > 0) {
            System.out.println("a ist positiv.");
        } else if (a < 0) {
            System.out.println("a ist negativ.");
        } else {
            System.out.println("a ist 0.");
        }
    }
}


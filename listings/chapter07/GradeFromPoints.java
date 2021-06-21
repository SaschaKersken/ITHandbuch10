public class GradeFromPoints {
    public static void main(String args[]) {
        if (args.length >= 1) {
            int points = 0;
            try {
                points = Integer.parseInt(args[0]); 
            } catch(NumberFormatException e) {
                System.out.println("Ihre Eingabe ist keine Zahl."); 
                System.exit(1);
            }
            int grade;
            if (points < 30) {
                grade = 6;
            } else if (points < 50) {
                grade = 5;
            } else if (points < 67) {
                grade = 4;
            } else if (points < 81) {
                grade = 3;
            } else if (points < 92) {
                grade = 2;
            } else {
                grade = 1;
            }
            System.out.println("Sie haben die Note " + grade + " erreicht.");
        } else {
            // Zu wenige Argumente
            System.out.println("Verwendung: java GradeFromPoints PUNKTE");
        }
    }
}


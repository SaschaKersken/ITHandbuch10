public class NumericalGradeToText {
    public static void main(String args[]) {
        if (args.length >= 1) {
            int grade = 0;
            try {
                grade = Integer.parseInt(args[0]);
            } catch(NumberFormatException e) {
                System.out.println("Die Note muss eine Zahl sein.");
                System.exit(1);
            }
            switch (grade) {
            case 6:
                System.out.println("Ungenügend");
                break;
            case 5:
                System.out.println("Mangelhaft");
                break;
            case 4:
                System.out.println("Ausreichend");
                break;
            case 3:
                System.out.println("Befriedigend");
                break;
            case 2:
                System.out.println("Gut");
                break;
            case 1:
                System.out.println("Sehr gut");
                break;
            default:
                System.out.println("Keine gültige Note.");
            }
        } else {
            System.out.println("Verwendung: java NumericalGradeToText NOTE");
        }
    }
}


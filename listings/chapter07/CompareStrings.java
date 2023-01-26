public class CompareStrings {
    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("Please provide at least 2 strings.");
        } else if (args[0].compareTo(args[1]) < 0) {
            System.out.println(args[0] + " < " + args[1]);
        } else if (args[0].compareTo(args[1]) > 0) {
            System.out.println(args[0] + " > " + args[1]);
        } else {
            System.out.println(args[0] + " == " + args[1]);
        }
    }
}

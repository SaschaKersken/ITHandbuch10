public class DataTypes {
    public static void byteTest(Byte b) {
        System.out.println("- The byte is " + b);
        System.out.println("  Range: " +
            Byte.MIN_VALUE + " => " + Byte.MAX_VALUE
        );
    }

    public static void shortTest(Short s) {
        System.out.println("- The short is " + s);
        System.out.println("  Range: " + Short.MIN_VALUE +
            " => " + Short.MAX_VALUE + " (" + Short.BYTES + " bytes)"
        );
    }

    public static void intTest(Integer i) {
        System.out.println("- The integer is " + i);
        System.out.println("  Range: " + Integer.MIN_VALUE +
            " => " + Integer.MAX_VALUE + " (" + Integer.BYTES + " bytes)"
        );
    }

    public static void longTest(Long l) {
        System.out.println("- The long is " + l);
        System.out.println("  Range: " + Long.MIN_VALUE + 
            " => " + Long.MAX_VALUE + " (" + Long.BYTES + " bytes)"
        );
    }

    public static void floatTest(Float f) {
        System.out.println("- The float is " + f);
        System.out.println("  Range: " + Float.MIN_VALUE +
            " => " + Float.MAX_VALUE + " (" + Float.BYTES + " bytes)"
        );
    }

    public static void doubleTest(Double d) {
        System.out.println("- The double is " + d);
        System.out.println("  Range: " + Double.MIN_VALUE +
            " => " + Double.MAX_VALUE + " (" + Double.BYTES + " bytes)"
        );
    }

    public static void charTest(Character c) {
        System.out.println("- The char is '" + c + "'");
    }

    public static void booleanTest(Boolean bl) {
        System.out.println("- The boolean is " + bl);
    }

    public static void main(String[] args) {
        byte b = 127;
        short s = 32767;
        int i = 23;
        long l = 1_000_000_000_000L;
        float f = 1.234f;
        double d = 3.1415926;
        char c = 'a';
        boolean bl = true;
        byteTest(b);
        shortTest(s);
        intTest(i);
        longTest(l);
        floatTest(f);
        doubleTest(d);
        charTest(c);
        booleanTest(bl);
    }
}

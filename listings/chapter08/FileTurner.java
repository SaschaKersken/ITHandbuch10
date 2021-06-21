import java.io.*;
import java.util.*;

public class FileTurner {
    public static void main (String[] args) {
        try {
            turnFile(args[0]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Verwendung: java FileTurner DATEINAME");
        }
        catch(FileNotFoundException e) {
            System.out.println("Datei nicht gefunden!");
        }
        catch(IOException e) {
            System.out.println("Dateifehler!");
        }
    }

    static void turnFile (String filename) throws
            FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader (
            new FileReader (filename)
        );
        String line = "";
        Stack<String> lines = new Stack<>();
        while ((line = reader.readLine()) != null) {
            lines.push (line);
        }
        while (!lines.empty()) {
            line = (lines.pop());
            System.out.println(line);
        }
    }
}


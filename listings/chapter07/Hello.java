import java.io.*;

public class Hello {
   public static void main(String args[]) {
      BufferedReader input = new BufferedReader(
           new InputStreamReader (System.in)
      );
      String name = "";
      System.out.println("Hallo Welt!");
      System.out.print("Ihr Name, bitte: ");
      try {
         name = input.readLine();
      } catch(IOException e) {}
      System.out.println("Hallo, " + name + "!");
   }
}


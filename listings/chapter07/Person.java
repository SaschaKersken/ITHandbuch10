public class Person {
    // Eigenschaften:
    private String lastName;
    private String firstName;
    private int age;

    // Konstruktor:
    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

   // Methoden:
   public void birthday() {
      this.age++;
   }

   public String getFullName() {
      return this.firstName + " " + this.lastName;
   }

   public int getAge() {
      return this.age;
   }
}


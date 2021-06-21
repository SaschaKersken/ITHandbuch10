public class Student extends Person {
   // zusätzliche Eigenschaft:
   private int grade;

   // Konstruktor:
   public Student(String lastName, String firstName, int age, int grade) {
      super(lastName, firstName, age);
      this.grade = grade;
   }

   // Neue Methoden:
   public int getGrade() {
      return this.grade;
   }

   public void graduation() {
      this.grade++;
   }
}


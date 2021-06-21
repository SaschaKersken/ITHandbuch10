public class SchoolTest {
    public static void main(String args[]) {
        Teacher welsch = new Teacher("Welsch", "Jo", 64, "Mathe");
        System.out.println(welsch.getFullName() + " unterrichtet " +
            welsch.getSubject()
        );

        Student tim = new Student("Witt", "Tim", 16, 11);
        tim.graduation();
        System.out.println(tim.getFullName() +
            " versetzt in Klasse " + tim.getGrade()
        );
    }
}


public class PersonTest {
    public static void main(String args[]) {
        Person klaus = new Person("Schmitz", "Klaus", 42);
        System.out.println("Person: " + klaus.getFullName());
        klaus.birthday();
        System.out.println("Neues Alter: " +
            klaus.getAge()
        );
    }
}


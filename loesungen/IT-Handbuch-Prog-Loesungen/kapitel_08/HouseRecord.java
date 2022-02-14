/**
* Einzelne Belegung von Alter der Person,
* Farbe des Hauses und Hausnummer im Logikrätsel
*/
public class HouseRecord {
    private int age;
    private String color;
    private int number;

    public HouseRecord(int age, String color, int number) {
        this.age = age;
        this.color = color;
        this.number = number;
    }

    public int getAge() {
        return this.age;
    }

    public String getColor() {
        return this.color;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean different(HouseRecord other) {
        return this.age != other.age && !this.color.equals(other.color)
            && this.number != other.number;
    }

    public String toString() {
        return "(Alter: " + this.age + ", Farbe: " + this.color + ", Hausnummer: " + this.number + ")";
    }
}

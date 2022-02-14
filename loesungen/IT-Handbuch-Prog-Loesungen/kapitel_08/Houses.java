import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
* Ein Logikrätsel (mehrdimensionale Zuordnung) als CSP lösen
*/
public class Houses implements Constraint<String, HouseRecord> {
    private List<Integer> ages;
    private List<String> colors;
    private List<Integer> numbers;

    /**
    * Konstruktor
    * Initialisiert die möglichen Werte für die Zuordnungen
    */
    public Houses() {
        this.ages = Arrays.asList(36, 37, 39, 42);
        this.colors = Arrays.asList("rot", "grün", "blau", "gelb");
        this.numbers = Arrays.asList(2, 4, 6, 8);
    }

    /**
    * Einen Datensatz anhand einer Altersangabe finden
    *
    * @param assignment Aktuelle Zuordnung
    * @param age Altersangabe
    * @return Datensatz oder null, falls nicht vorhanden
    */
    public HouseRecord findByAge(Map<String, HouseRecord> assignment, int age) {
        for (HouseRecord record: assignment.values()) {
            if (age == record.getAge()) {
                return record;
            }
        }
        return null;
    }

    /**
    * Einen Datensatz anhand einer Hausfarbe finden
    *
    * @param assigment Aktuelle Zuordnung
    * @param color Hausfarbe
    * @return Datensatz oder null, falls nicht vorhanden
    */
    public HouseRecord findByColor(Map<String, HouseRecord> assignment, String color) {
        for (HouseRecord record: assignment.values()) {
            if (color.equals(record.getColor())) {
                return record;
            }
        }
        return null;
    }

    /**
    * Einen Datensatz anhand einer Hausnummer finden
    *
    * @param assignment Aktuelle Zuordnung
    * @param number Hausnummer
    * @return Datensatz oder null, falls nicht vorhanden
    */
    public HouseRecord findByNumber(Map<String, HouseRecord> assignment, int number) {
        for (HouseRecord record: assignment.values()) {
            if (number == record.getNumber()) {
                return record;
            }
        }
        return null;
    }

    /**
    * Prüfen, ob eine Zuordnung alle Bedingungen erfüllt
    *
    * Trifft das Gegenteil der jeweiligen Bedingung zu,
    * wird false zurückgegeben, ansonsten am Ende true
    *
    * @param assignment Aktuelle Zuordnung
    * @return true Wenn die Bedingungen erfüllt sind, sonst false
    */
    @Override
    public boolean check(Map<String, HouseRecord> assignment) {
        // Zugeordnete Werte dürfen nicht identisch sein
        for (Map.Entry<String, HouseRecord> left: assignment.entrySet()) {
            for (Map.Entry<String, HouseRecord> right: assignment.entrySet()) {
                if (left.getKey().equals(right.getKey())) {
                    continue;
                }
                if (!left.getValue().different(right.getValue())) {
                    return false;
                }
            }
        }
        // Die jüngste Person wohnt im gelben Haus?
        HouseRecord youngest = findByNumber(assignment, 36);
        HouseRecord yellow = findByColor(assignment, "gelb");
        if (youngest != null && yellow != null && youngest.different(yellow)) {
            return false;
        }
        // Klaus ist kein Nachbar von Hector?
        if (assignment.containsKey("Klaus") && assignment.containsKey("Hector")) {
            if (Math.abs(assignment.get("Klaus").getNumber() - assignment.get("Hector").getNumber()) == 2) {
                return false;
            }
        }
        // Das grüne Haus steht nicht neben dem gelben?
        HouseRecord green = findByColor(assignment, "grün");
        if (yellow != null && green != null) {
            if (Math.abs(yellow.getNumber() - green.getNumber()) == 2) {
                return false;
            }
        }
        // Hector wohnt nicht in einem der äußeren Häuser
        if (assignment.containsKey("Hector")) {
            HouseRecord hector = assignment.get("Hector");
            if (hector.getNumber() == 2 || hector.getNumber() == 8) {
                return false;
            }
        }
        // Die Person im grünen Haus ist 42 Jahre alt?
        if (green != null && green.getAge() != 42) {
            return false;
        }
        // Anna wohnt nicht im roten Haus?
        if (assignment.containsKey("Anna") && assignment.get("Anna").getColor().equals("rot")) {
            return false;
        }
        // Filiz ist nicht 39 Jahre alt?
        if (assignment.containsKey("Filiz") && assignment.get("Filiz").getAge() == 39) {
            return false;
        }
        // Das gelbe Haus steht ganz rechts?
        if (yellow != null && yellow.getNumber() != 8) {
            return false;
        }
        // Anna oder Klaus wohnt in Hausnummer 4?
        if (assignment.containsKey("Anna") && assignment.containsKey("Klaus")) {
            if (assignment.get("Anna").getNumber() != 4 && assignment.get("Klaus").getNumber() != 4) {
                return false;
            }
        }
        // Die 39-jährige Person wohnt ganz links?
        HouseRecord age39 = findByAge(assignment, 39);
        if (age39 != null && age39.getNumber() != 2) {
            return false;
        }
        // Anna ist älter als Hector, dessen Haus nicht rot ist
        if (assignment.containsKey("Hector")) {
            HouseRecord hector = assignment.get("Hector");
            if (hector.getColor().equals("rot")) {
                return false;
            }
            if (assignment.containsKey("Anna") && assignment.get("Anna").getAge() < hector.getAge()) {
                return false;
            }
        }
        // Alle Bedingungen erfüllt
        return true;
    }

    /**
    * Hauptprogramm
    *
    * Initialsierung, Ausführung, Ausgabe
    */
    public static void main(String[] args) {
        Houses houses = new Houses();
        List<HouseRecord> domainTemplate = new ArrayList<>();
        for (int age: houses.ages) {
            for (String color: houses.colors) {
                for (int number: houses.numbers) {
                    domainTemplate.add(new HouseRecord(age, color, number));
                }
            }
        }
        List<String> variables = Arrays.asList("Anna", "Filiz", "Hector", "Klaus");
        Map<String, List<HouseRecord>> domains = new HashMap<>();
        for (String variable: variables) {
            domains.put(variable, domainTemplate);
        }
        CSP<String, HouseRecord> housesCSP = new CSP<>(variables, domains, houses);
        Map<String, HouseRecord> solution = housesCSP.solve();
        if (solution != null) {
            for (Map.Entry<String, HouseRecord> entry: solution.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } else {
            System.out.println("Keine Lösung gefunden.");
        }
    }
}

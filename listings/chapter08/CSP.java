import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
* Bedingungserfüllungssystem für Variablen des generischen Typs <K>
* und Domäneninhalte des generischen Typs <V>
*/
public class CSP<K, V> {
    /**
    * Variablen
    */
    private List<K> variables;

    /**
    * Domänen
    */
    private Map<K, List<V>> domains;

    /**
    * Instanz, die Bedingungen prüft
    */
    private Constraint<K, V> constraint;

    /**
    * Konstruktor
    */
    public CSP(List<K> variables, Map<K, List<V>> domains, Constraint<K, V> constraint) {
        this.variables = variables;
        this.domains = domains;
        this.constraint = constraint;
    }

    /**
    * Das Bedingungserfüllungsproblem lösen
    *
    * @return Die gefundene Lösung oder null, wenn es keine gibt
    */
    public Map<K, V> solve() {
        return solve(new HashMap<K, V>());
    }

    /**
    * Interne Hilfsmethode zur rekursiven Lösungssuche
    */
    private Map<K, V> solve(Map<K, V> assignment) {
        // Lösung für jede Variable gefunden? Dann zurückgeben
        if (assignment.size() == this.variables.size()) {
            return assignment;
        }
        // Variablen finden, die noch keine Zuordnung haben
        List<K> unassigned = new ArrayList<>();
        for (K key:variables) {
            if (!assignment.containsKey(key)) {
                unassigned.add(key);
            }
        }
        // Die erste noch nicht zugeordnete Variable auswählen
        K testVariable = unassigned.get(0);
        // Alle Domänenwerte für die neue Variable durchprobieren
        for (V value:this.domains.get(testVariable)) {
            Map<K, V> testAssignment = new HashMap<>(assignment);
            testAssignment.put(testVariable, value);
            // Falls die Zuordnung die Bedingungen erfüllt
            if (constraint.check(testAssignment)) {
                // Rekursiver Aufruf
                Map<K, V> result = solve(testAssignment);
                // Falls OK, bisheriges Teilergebnis zurückgeben
                if (result != null) {
                    return result;
                }
            }
        }
        // Kein (Teil-)Ergebnis gefunden
        return null;
    }
}

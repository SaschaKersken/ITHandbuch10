import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class CSPtest1 implements Constraint<String, String> {
    public boolean check(Map<String, String> assignment) {
        Set<String> unique = new HashSet<>(assignment.values());
        return unique.size() == assignment.values().size();
    }

    public static void main(String[] args) {
        List<String> variables = Arrays.asList("Alfred", "Birte", "Cem", "Dana");
        Map<String, List<String>> domains = new HashMap<>();
        domains.put("Alfred", Arrays.asList("9 Uhr", "15 Uhr"));
        domains.put("Birte", Arrays.asList("9 Uhr", "14 Uhr"));
        domains.put("Cem", Arrays.asList("14 Uhr", "15 Uhr", "17 Uhr"));
        domains.put("Dana", Arrays.asList("9 Uhr", "11 Uhr", "17 Uhr"));
        CSPtest1 test = new CSPtest1();
        CSP<String, String> appointmentCSP = new CSP<>(variables, domains, test);
        Map<String, String> solution = appointmentCSP.solve();
        if (solution != null) {
            for(String k:solution.keySet()) {
                System.out.println(k + ": " + solution.get(k));
            }
        }
    }
}

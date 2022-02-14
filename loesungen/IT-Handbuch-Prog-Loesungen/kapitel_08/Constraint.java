import java.util.Map;

public interface Constraint<K, V> {
    public boolean check(Map<K, V> assignment);
}

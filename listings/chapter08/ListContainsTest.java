import java.util.List;
import java.util.ArrayList;

public class ListContainsTest {
    public static void main(String[] args) {
        Integer[] e1 = {1, 2};
        Integer[] e2 = {1, 2};
        List<Integer[]> l = new ArrayList<>();
        l.add(e1);
        if (l.contains(e1)) {
            System.out.println("Object yes");
        } else {
            System.out.println("Onject NO");
        }
        if (l.contains(e2)) {
            System.out.println("Data yes");
        } else {
            System.out.println("Data NO");
        }
    }
}        

import java.util.List;
import java.util.ArrayList;

public class RemoveTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(Integer.valueOf(3));
        list.remove(Integer.valueOf(4));
        for (int i:list) {
            System.out.println(i);
        }
    }
}

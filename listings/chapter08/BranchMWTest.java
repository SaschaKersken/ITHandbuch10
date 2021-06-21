public class BranchMWTest {
    public static void main(String[] args) {
        System.out.println(Branch.getMaxDisplayWidth());
        Branch.setMaxDisplayWidth(100);
        System.out.println(Branch.getMaxDisplayWidth());
    }
}

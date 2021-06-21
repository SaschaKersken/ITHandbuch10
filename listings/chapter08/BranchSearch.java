import java.util.List;
import java.util.ArrayList;
import java.util.Deque;

public class BranchSearch implements PathSearchable<Branch<Integer>> {
    private Branch<Integer> goal;

    public BranchSearch(Branch<Integer> goal) {
        this.goal = goal;
    }

    @Override
    public List<Branch<Integer>> successors(Branch<Integer> branch) {
        List<Branch<Integer>> result = new ArrayList<>();
        Branch<Integer> left = branch.getLeft();
        Branch<Integer> right = branch.getRight();
        if (left != null) {
            result.add(left);
        }
        if (right != null) {
            result.add(right);
        }
        return result;
    }

    @Override
    public boolean isGoal(Branch<Integer> potential) {
        return this.goal.getValue().equals(potential.getValue());
    }

    @Override
    public double heuristic(Branch<Integer> potential) {
        // Wird hier nicht benötigt
        return 0.0;
    }

    public static void printGoal(Node<Branch<Integer>> goal) {
        if (goal != null) {
            Deque<Branch<Integer>> goalPath = goal.toPath();
            while (goalPath.size() > 0) {
                System.out.print("  " + goalPath.removeLast().getValue());
            }
            System.out.println();
        } else {
            System.out.println("Kein Pfad gefunden.");
        }
    }

    public static void main(String[] args) {
        BranchSort<Integer> tree = new BranchSort<>();
        int[] values = {6, 3, 7, 1, 4, 9, 5, 2, 8};
        for (int i = 0; i < values.length; i++) {
            tree.addValue(values[i]);
        }
        Branch<Integer> root = tree.getRoot();
        root.print();
        System.out.println();
        BranchSearch bs1 = new BranchSearch(new Branch<Integer>(4));
        Node<Branch<Integer>> goal1 = PathSearch.dfs(root, bs1);
        System.out.println("Tiefensuche nach 4:");
        printGoal(goal1);
        Node<Branch<Integer>> goal2 = PathSearch.bfs(root, bs1);
        System.out.println("Breitensuche nach 4:");
        printGoal(goal2);
        BranchSearch bs2 = new BranchSearch(new Branch<Integer>(10));
        Node<Branch<Integer>> goal3 = PathSearch.dfs(root, bs2);
        System.out.println("Tiefensuche nach 10:");
        printGoal(goal3);
    }
}

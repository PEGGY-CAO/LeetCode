import java.util.ArrayList;


class TreeNode {
    int value;
    ArrayList<TreeNode> children;

    public TreeNode(int value, ArrayList<TreeNode> childlist) {
        this.value = value;
        children = childlist;
    }

    @Override
    public String toString() {
        return "node: " + value;
    }
}

public class SubtreeMaxAverage {

     public static class Shelter{
        double sum;

        double size;


        public Shelter (double su, double si) {
            sum = su;
            size = si;
        }


    }

    private TreeNode subtree = null;

    private Shelter dummyResult = null;


    public TreeNode findMaxAverage(TreeNode root) {
        Shelter dummy = dfs(root);
        return subtree;

    }

    private Shelter dfs(TreeNode root) {
        if (root.children == null) {
            return new Shelter(root.value, 1);
        }

        ArrayList<TreeNode> children = root.children;
        //Shelter x = new Shelter(0,0);
        double sum = 0.0;
        double size = 0.0;
        for (TreeNode t : children) {
            Shelter x = dfs(t);
            sum += x.sum;
            size += x.size;
        }

        sum += root.value;
        size++;

        if (dummyResult == null || dummyResult.sum / dummyResult.size < sum / size) {
            dummyResult = new Shelter(sum, size);
            subtree = root;
        }
        return new Shelter(sum, size);

    }

    public static void main(String[] args) {
        SubtreeMaxAverage res = new SubtreeMaxAverage();
        TreeNode node3 = new TreeNode(3, null);
        ArrayList<TreeNode> list2 = new ArrayList<>();
        list2.add(node3);
        TreeNode node2 = new TreeNode(2, list2);
        ArrayList<TreeNode> list1 = new ArrayList<>();
        list1.add(node2);
        TreeNode node1 = new TreeNode(1, list1);

        TreeNode resultNode = res.findMaxAverage(node1);
        System.out.println(resultNode);

    }

}

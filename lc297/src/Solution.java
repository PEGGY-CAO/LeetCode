import java.util.List;
import java.util.ArrayList;

public class Solution {
    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<Integer> store(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preOrder(root, result);
        return result;
    }
    private void preOrder(TreeNode parent, ArrayList<Integer> result) {
        TreeNode leftChild = parent.left;
        TreeNode rightChild = parent.right;
        result.add(parent.data);
        if (leftChild == null && rightChild == null) {
            return;
        }
        if (leftChild != null) {
            preOrder(leftChild, result);
        }
        if (rightChild != null) {
            preOrder(rightChild, result);
        }
        return;
    }

    // Decodes your encoded data to tree.
    public TreeNode restore(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int[] array = list.toArray();
        return restoreHelper(0, array);
    }
    private TreeNode restoreHelper(int i, int[]array) {
        TreeNode parent = new TreeNode(array[i]);
        if (array[i + 1] = null && array[i + 2] = null) {
            parent.left = null;
            parent.right = null;
            return parent;
        }
        if (array[i + 1] != null) {
            parent.left = restoreHelper(i + 1, array);
        }
        if (array[i + 2] != null) {
            parent.right = restoreHelper(i + 2, array);
        }
        return parent;
    }
}

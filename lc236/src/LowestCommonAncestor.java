

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}

public class LowestCommonAncestor {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode leftSide = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSide = lowestCommonAncestor(root.right, p, q);
        if (leftSide != null && rightSide != null) {
            return root;
        } else if (leftSide == null) {
            return rightSide != null ? rightSide : null;
        } else {
            return leftSide;
        }

    }

    public static void main(String[] args) {
        TreeNode c11 = new TreeNode(6);

        TreeNode c21 = new TreeNode(0);
        TreeNode c22 = new TreeNode(8);
        TreeNode c121 = new TreeNode(7);
        TreeNode c122 = new TreeNode(4);
        TreeNode c12 = new TreeNode(2, c121, c122);
        TreeNode c1 = new TreeNode(5, c11, c12);
        TreeNode c2 = new TreeNode(1, c21, c22);
        TreeNode testRoot = new TreeNode(3, c1, c2);

        TreeNode testp = new TreeNode(5);
        TreeNode testq = new TreeNode(1);


        TreeNode ans = lowestCommonAncestor(testRoot, testp, testq);
        if (ans == null) {
            System.out.println("There's no such p or q");
        } else {
            System.out.println(ans.val);
        }
    }
}

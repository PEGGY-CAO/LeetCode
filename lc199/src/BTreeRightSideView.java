import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BTreeRightSideView {

    static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Integer> rightSideView(Node root) {
        List<Integer> result = new ArrayList<>();

        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int size = q.size();
        while (!q.isEmpty()) {
            Node current = q.poll();
            size --;
            if (current.left != null) {
                q.offer(current.left);
            }
            if (current.right != null) {
                q.offer(current.right);
            }
            if (size == 0) {
                //then we know current is the last one in its level, then add it to our result list
                result.add(current.value);
                size = q.size();
            }

        }

        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node l1 = new Node(2);
        Node r1 = new Node(3);
        root.left = l1;
        root.right = r1;

        Node l11 = new Node(4);
        Node l12 = new Node(5);
        l1.left = l11;
        l1.right = l12;
        l1.right = l12;

        List<Integer> result = rightSideView(root);
        for (int i : result) {
            System.out.println(i);
        }
    }
}

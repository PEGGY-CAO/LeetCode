import java.util.*;

public class ConstructSExpression {
    /**
     You are given a binary tree as a sequence of parent-child pairs.
     For example, the tree represented by the node pairs below:
     (A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E)
     may be illustrated in many ways, with two possible representations below:
     A   /  \  B    C / \  / \G  D  E   H       \            F
     A   /  \  B    C / \  / \D  G H   E        /       F
     Below is the recursive definition for the S-expression of a tree:
     S-exp(node) = ( node->val
     (S-exp(node->first_child))(S-exp(node->second_child))), if node != NULL
     = "", node == NULL
     where, first_child->val < second_child->val (lexicographically smaller)
     This tree can be represented in a S-expression in multiple ways.
     The lexicographically smallest way of expressing this is as follows:
     (A(B(D)(G))(C(E(F))(H)))
     We need to translate the node-pair representation into an S-expression
     (lexicographically smallest one), and report any errors that do not conform to the definition of a binary tree.
     The list of errors with their codes is as follows:
     Error Code          Type of error
     E1                 More than 2 children
     E2                 Duplicate Edges
     E3                 Cycle present
     E4                 Multiple roots
     E5                 Any other error
     Input Format:
     Input must be read from standard input.
     Input will consist of on line of parent-child pairs. Each pair consists of two node names separated by a single comma and enclosed in parentheses. A single space separates the pairs.
     Output:
     The function must write to standard output.
     Output the given tree in the S-expression representation described above.
     There should be no spaces in the output.
     Constraints:
     There is no specific sequence in which the input (parent,child) pairs are represented.
     The name space is composed of upper case single letter (A-Z) so the maximum size is 26 nodes.
     Error cases are to be tagged in the order they appear on the list. For example,
     if one input pair raises both error cases 1 and 2, the output must be E1.
     Sample Input #00
     (B,D) (D,E) (A,B) (C,F) (E,G) (A,C)
     Sample Output #00
     (A(B(D(E(G))))(C(F)))
     Sample Input #01
     (A,B) (A,C) (B,D) (D,C)
     Sample Output #01
     E3
     Explanation
     Node D is both a child of B and a parent of C, but C and B are both child nodes of A.
     Since D tries to attach itself as parent to a node already above it in the tree, this forms a cycle.
     Idea:
     Use a 26*26 graph to represent the input tree. Then check for each error in the order, finally use
     a recursive DFS to form the tree and the output SExpression.
     E1: More than 2 children. Check if graph[i][j], j from 0 to 25 has more than two cell that is true.
     E2: Duplicate Edge. Check when constructing the graph, if graph[x][y] is already true, E2=true.
     E3: Cycle Present: Check when looking for the root, starting from each root, use recursive DFS to check if there is
     a cycle in the graph.
     E4: Multiple roots: traverse all nodes in the HashSet, if no edge connected TO this node, then it must be a root.
     即这个顶点没有入度，没有其他的点指向这个点，那么这个点必然是root. If number of roots > 1, return "E4".
     Note: if number of roots == 0, then there must also be a cycle, return "E3".
     */

    public static String constructSExpression(String test) {

        int edges = 0;
        Set<Integer> children = new HashSet<>();
        Map<Integer, List<Integer>> parentChild = new HashMap<>();

        for (int i = 1; i < test.length(); i += 6) {
            int parent = test.charAt(i) - 'A';
            int child = test.charAt(i + 2) - 'A';
            List<Integer> childList = parentChild.getOrDefault(parent, new ArrayList<>());
            if (childList.contains(child)) {
                //duplicate edge
                return "E2";
            } else if (childList.size() == 2) {
                //will be more than 2 children after adding current one
                return "E1";
            } else {
                childList.add(child);
                parentChild.put(parent, childList);
            }
            children.add(child);
            edges ++;
        }

        //detect cycle
        if (detectCycle(edges, children, parentChild.keySet())) {
            return "E3";
        }

        int countRoots = 0;
        int rootIndex = -1;
        Set<Integer> parents = parentChild.keySet();
        for (Integer p : parents) {
            if (!children.contains(p)) {
                countRoots ++;
                if (countRoots > 2) {
                    return "E4";
                }
                rootIndex = p;
            }
        }

        return constructHelper(rootIndex, parentChild).toString();
    }

    public static StringBuilder constructHelper(int currentIndex, Map<Integer, List<Integer>> parentChild) {
        //when reach leaves node
        if (!parentChild.keySet().contains(currentIndex)) {
            StringBuilder str = new StringBuilder().append('(');
            char cur = (char)('A' + currentIndex);
            str.append(cur).append(')');
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('(').append((char)('A' + currentIndex));
        List<Integer> childList = parentChild.get(currentIndex);
        Collections.sort(childList);
        for (Integer c : childList) {
            sb.append(constructHelper(c, parentChild));
        }
        sb.append(')');
        return sb;
    }

    public static boolean detectCycle(int edges, Set<Integer> children, Set<Integer> allParents) {
        Set<Integer> allNodes = new HashSet<>();
        allNodes.addAll(children);
        allNodes.addAll(allParents);
        return edges >= allNodes.size();
    }

    public static void main(String[] args) {
        String test = "(A,B) (A,C) (B,G) (C,H) (E,F) (B,D) (C,E)";
        String test1 = "(B,D) (D,E) (A,B) (C,F) (E,G) (A,C)";
        String test2 = "(A,B) (A,C) (B,D) (D,C)";
        System.out.println(constructSExpression(test));
    }
}

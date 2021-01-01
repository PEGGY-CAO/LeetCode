import java.util.HashSet;
import java.util.Stack;
import java.util.Set;

public class BalanceParentheses {
    //leetcode 1249
    public static String minRemoveToMakeValid(String s) {

        if (s == null || s.length() == 0) return "";
        //use a stack to store the index of extra '('
        Stack<Integer> stackOfL = new Stack<>();
        //use a set to store the index of extra ')'
        Set<Integer> setOfR = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stackOfL.push(i);
            } else if (c == ')') {
                if (stackOfL.size() == 0) {
                    setOfR.add(i);
                } else {
                    stackOfL.pop();
                }
            }
        }

        //build up the answer
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' && stackOfL.contains(i)) {
                continue;
            } else if (c == ')' && setOfR.contains(i)) {
                continue;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        String result = minRemoveToMakeValid("ab(cd))le(code");
        System.out.println(result);
    }
}

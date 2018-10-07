import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        int length = s.length();
        if (length == 0) {
            return true;
        }
        Stack<Character> dummy = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '(') {
                dummy.push(')');
            } else if (arr[i] == '[') {
                dummy.push(']');
            } else if (arr[i] == '{') {
                dummy.push('}');
            } else {
                if (dummy.isEmpty() || dummy.pop() != arr[i]) {
                    return false;
                }
            }
        }
        return dummy.isEmpty();
    }
}

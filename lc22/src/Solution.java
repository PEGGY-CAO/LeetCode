import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        //corner case
        if (n == 0) {
            return res;
        }
        helper(res, n, n, "");
        return res;
    }

    public static void helper(List<String> res, int left, int right, String s) {
        if (left > right) {
            return;
        }

        if (left == 0 && right == 0) {
            System.out.println(s);
            res.add(s);
            return;
        }
        if (left > 0) {
            helper(res, left - 1, right, s + "(");
        }
        if (right > 0) {
            helper(res, left, right - 1, s + ")");
        }
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        List<String> res = test.generateParenthesis(3);
        
    }
}

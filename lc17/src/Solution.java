import java.util.List;
import java.util.ArrayList;


public class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {"0" , "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder cur = new StringBuilder();
        dfs(digits, mapping, 0, cur, result);
        return result;

    }
    private void dfs(String digits, String[] map, int index, StringBuilder cur, List<String> result) {
        if (index == digits.length()) {
            result.add(cur.toString());
            return;
        }
        char[] ite = map[digits.charAt(index) - '0'].toCharArray();
        for (char c : ite) {
            cur.append(c);
            dfs(digits, map, index + 1, cur, result);
            cur.deleteCharAt(index);
        }

    }

    public static void main(String[] str) {
        Solution sol = new Solution();
        List<String> x = sol.letterCombinations("45");
        for (int i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
    }
}

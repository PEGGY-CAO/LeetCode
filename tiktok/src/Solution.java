import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * Given string "A?B?". Return a list contains all combination with question mark substituted
     * eg. Should return
     * [AABA, AABB, ABBA, ABBB]
     * @param input
     */
    public List<String> findStrings(String input) {
        //corner cases
        if (input == null || input.length() == 0) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();

        int[] marks = new int[input.length()];
        int countMark = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '?') {
                countMark++;
                marks[i] = 1;
            }
        }

        if (countMark == 0) {
            ans.add(input);
            return ans;
        }

        //backtracking
        ans = dfs(input, marks, ans, countMark, 0);
        return ans;

    }

    private List<String> dfs(String input, int[] marks, List<String> ans, int countMark, int currentLevel) {
        //ending case
        if (ans.size() == Math.pow(2, countMark)) {
            return ans;
        }
        if (currentLevel == countMark) {
            ans.add(input);
            return ans;
        }

        //backtracking
        for (int i = 0; i < input.length(); i++) {

            if (marks[i] == 1) {
                marks[i] = 0;
                String potentialA = input.substring(0, i) + 'A' + input.substring(i + 1);
//                System.out.println(potentialA);
                ans = dfs(potentialA, marks, ans, countMark, currentLevel + 1);

                String potentialB = input.substring(0, i) + 'B' + input.substring(i + 1);
//                System.out.println(potentialB);
                ans = dfs(potentialB, marks, ans, countMark, currentLevel + 1);
                if (ans.size() == Math.pow(2, countMark)) {
                    break;
                }
                marks[i] = 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> test1 = s.findStrings("A?B?");
        for (String t : test1) {
            System.out.println(t);
        }

        List<String> test2 = s.findStrings("AB?AB");
        for (String t : test2) {
            System.out.println(t);
        }
    }
}

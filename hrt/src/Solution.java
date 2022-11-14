// let's do the harder version of lc1307!

import java.util.*;

public class Solution {

    public int solution(String[] words) {

        if (words == null || words.length == 0 || words[words.length - 1] == null || words[words.length - 1].length() == 0) {
            return 0;
        }
        //pre prune
        int leftLength = 0;

        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            leftLength = Math.max(leftLength, word.length());
        }
        if (leftLength > words[words.length - 1].length() || leftLength + 2 <= words[words.length - 1].length()) {
            return 0;
        }

        boolean[] nonZero = new boolean[26];
        //prepare then reverse
        for (int row = 0; row < words.length; row++) {
            String word = words[row];
            StringBuilder sb = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                char current = word.charAt(i);
                sb.append(current);
                if (i == 0 && word.length() > 1) {
                    nonZero[current - 'A'] = true;
                }
            }
            words[row] = new String(sb.toString());
            sb.setLength(0);
        }

        //data structure we need for dfs
        int[] charToInt = new int[26];
        Arrays.fill(charToInt, -1);
        int[] visited = new int[10];
        Arrays.fill(visited, 0);

        //dfs backtracking
        int count = 0;
        dfs(words, charToInt, visited, nonZero, count, 0, 0, 0);

        return count;
    }

    private void dfs(String[] words, int[] charToInt, int[] visited, boolean[] nonZero, int result, int row, int col, int carrySum) {
        //if finally find a combination makes sense
        if (col == words[words.length - 1].length()) {
            if (carrySum != 0) return;
            if (charToInt[words[words.length - 1].charAt(words[words.length - 1].length() - 1) - 'A'] == 0 && words[words.length - 1].length() > 1) {
                // System.out.println("it's here");
                return;
            }
            result++;
            return;
        }
        //last row to result
        if (row == words.length - 1) {
            int d = carrySum % 10;
            char cFromResult = words[words.length - 1].charAt(col);
            if (charToInt[cFromResult - 'A'] == -1) {
                //character hasn't been assigned yet
                if (visited[d] == 0) {
                    //digit hasn't been matched to character yet
                    visited[d] = 1;
                    charToInt[cFromResult - 'A'] = d;
                    dfs(words, charToInt, visited, nonZero, result, 0, col + 1, carrySum / 10);
                    visited[d] = 0;
                    charToInt[cFromResult - 'A'] = -1;
                    return;
                }
            }
        }


        //backtracking
        String currentWord = words[row];
        if (col >= currentWord.length()) {
            if (charToInt[currentWord.charAt(currentWord.length() - 1) - 'A'] == 0 && currentWord.length() > 1) return;
            dfs(words, charToInt, visited, nonZero, result, row + 1, col, carrySum);
        } else {

            char c = currentWord.charAt(col);
            if (charToInt[c - 'A'] == -1) {
                //the character hasn't been assigned yet
                for (int digit = 0; digit < 10; digit++) {
                    //leading 0
                    if (digit == 0 && col == currentWord.length() - 1 && currentWord.length() > 1) continue;
                    if (digit == 0 && nonZero[c - 'A']) continue;

                    if (visited[digit] == 0) {
                        //digit hasn't been matched to character yet
                        visited[digit] = 1;
                        charToInt[c - 'A'] = digit;
                        dfs(words, charToInt, visited, nonZero, result, row + 1, col, carrySum + digit);
                        visited[digit] = 0;
                        charToInt[c - 'A'] = -1;
                    }
                }
                return;
            } else {
                //c has already got a matching integer
                int digit = charToInt[c - 'A'];
                dfs(words, charToInt, visited, nonZero, result, row + 1, col, carrySum + digit);
            }
        }

    }

    public static void main(String[] args) {
        String[] testWords1 = new String[3];
        testWords1[0] = "SEND";
        testWords1[1] = "MORE";
        testWords1[2] = "MONEY";
        Solution s1 = new Solution();
        int res1 = s1.solution(testWords1);
        System.out.println(res1);
    }
}
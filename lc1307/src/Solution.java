import java.util.Arrays;

public class Solution {
    public static boolean isSolvable(String[] words, String result) {
        if (words == null || words.length == 0 || result == null || result.length() == 0) {
            return false;
        }
        //pre prune
        int leftLength = 0;
        for (String word : words) {
            leftLength = Math.max(leftLength, word.length());
        }
        if (leftLength > result.length() || leftLength + 2 <= result.length()) {
            return false;
        }

        boolean[] nonZero = new boolean[26];
        //prepare then reverse
        String[] reversedWords = new String[words.length];
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
            reversedWords[row] = new String(sb.toString());
            sb.setLength(0);
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = result.length() - 1; i >= 0; i--) {
            reversed.append(result.charAt(i));
        }
        String reversedResult = reversed.toString();

        //data structure we need for dfs
        int[] charToInt = new int[26];
        Arrays.fill(charToInt, -1);
        int[] visited = new int[10];
        Arrays.fill(visited, 0);
        boolean res = dfs(charToInt, visited, 0, 0, 0, reversedWords, reversedResult, nonZero);

        // System.out.println("send: " + charToInt['S' - 'A'] + charToInt['E' - 'A'] + charToInt['N' - 'A'] + charToInt['D' - 'A']);
        return res;

    }

    private static boolean dfs(int[] charToInt, int[] visited, int row, int col, int carrySum, String[] reversedWords, String reversedResult, boolean[] nonZero) {
        //finally reach the end
        if (col == reversedResult.length()) {
            if (carrySum != 0) {

                return false;
            }
            if (charToInt[reversedResult.charAt(reversedResult.length() - 1) - 'A'] == 0 && reversedResult.length() > 1) {
                // System.out.println("it's here");
                return false;
            }
            return true;
        }

        //last row to result
        if (row == reversedWords.length) {
            int d = carrySum % 10;
            char cFromResult = reversedResult.charAt(col);
            if (charToInt[cFromResult - 'A'] == -1) {
                //character hasn't been assigned yet
                if (visited[d] == 0) {
                    //digit hasn't been matched to character yet
                    visited[d] = 1;
                    charToInt[cFromResult - 'A'] = d;

                    if (dfs(charToInt, visited, 0, col + 1, carrySum / 10, reversedWords, reversedResult, nonZero)) {
                        return true;
                    }
                    visited[d] = 0;
                    charToInt[cFromResult - 'A'] = -1;
                    return false;
                } else {
                    //digit has been matched to character
                    // System.out.println("it's here");
                    return false;
                }
            } else {
                //character has been assigned already
                if (charToInt[cFromResult - 'A'] == d) {
                    return dfs(charToInt, visited, 0, col + 1, carrySum / 10, reversedWords, reversedResult, nonZero);
                } else {
                    return false;
                }

            }
        }

        //backtracking
        String currentWord = reversedWords[row];
        if (col >= currentWord.length()) {
            if (charToInt[currentWord.charAt(currentWord.length() - 1) - 'A'] == 0 && currentWord.length() > 1) return false;
            return dfs(charToInt, visited, row + 1, col, carrySum, reversedWords, reversedResult, nonZero);
        } else {
            char c = currentWord.charAt(col);

            if (charToInt[c - 'A'] == -1) {
                //character hasn't been assigned yet
                for (int digit = 0; digit < 10; digit++) {
                    if (digit == 0 && col == currentWord.length() - 1 && currentWord.length() > 1) continue;
                    if (digit == 0 && nonZero[c - 'A']) continue;

                    if (visited[digit] == 0) {
                        //digit hasn't been matched to character yet

                        visited[digit] = 1;
                        charToInt[c - 'A'] = digit;
                        if (dfs(charToInt, visited, row + 1, col, carrySum + digit, reversedWords, reversedResult, nonZero)) {
                            return true;
                        }
                        visited[digit] = 0;
                        charToInt[c - 'A'] = -1;


                    } else {
                        continue;
                    }
                }
                return false;
            } else {
                //c has already got a matching integer
                int digit = charToInt[c - 'A'];
                return dfs(charToInt, visited, row + 1, col, carrySum + digit, reversedWords, reversedResult, nonZero);
            }
        }

    }

    public static void main(String[] args) {

        String[] testWords1 = new String[2];
        testWords1[0] = "SEND";
        testWords1[1] = "MORE";
        String testResult = "MONEY";
        boolean test1 = Solution.isSolvable(testWords1, testResult);
        System.out.println(test1);

        String[] testWords2 = new String[2];
        testWords2[0] = "AA";
        testWords2[1] = "BB";
        String testResult2 = "AA";
        boolean test2 = Solution.isSolvable(testWords2, testResult2);
        System.out.println(test2);

        String[] testWords3 = new String[3];
        testWords3[0] = "SIX";
        testWords3[1] = "SEVEN";
        testWords3[2] = "SEVEN";
        String testResult3 = "TWENTY";
        boolean test3 = Solution.isSolvable(testWords3, testResult3);
        System.out.println(test3);//EXPECTED: TRUE

        String[] testWords4 = new String[2];
        testWords4[0] = "A";
        testWords4[1] = "B";
        String testResult4 = "A";
        boolean test4 = Solution.isSolvable(testWords4, testResult4);
        System.out.println(test4);//EXPECTED: TRUE

    }
}

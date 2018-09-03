import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Solution {
    public int longestPalindrome(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        if (s == null || len == 0) {
            return 0;
        }
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if (visited. contains(curr)) {
                map.put(curr, map.get(curr) + 1);
            } else {
                map.put(curr, 1);
                visited.add(curr);
            }
        }
        Set<Character> charSet = map.keySet();
        int countOdd = 0;
        for (Character ch : charSet) {
            int val = map.get(ch);
            if (val % 2 == 1) {
                countOdd++;

                if (countOdd > 1) {
                    val--;
                    map.put(ch, val);
                }
            }
        }
        int result = 0;
        for (Character ch : charSet) {
            result += map.get(ch);
        }
        return result;
    }
}

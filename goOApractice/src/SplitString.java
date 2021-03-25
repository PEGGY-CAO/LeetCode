import java.util.Map;
import java.util.HashMap;

public class SplitString {
    public static int solution(String S) {

        //System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        int ans = 0;
        Map<Character, Integer> left = new HashMap<>();
        Map<Character, Integer> right = new HashMap<>();

        //add all characters to right map
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            //System.err.println("char: " + c);
            int count = right.getOrDefault(c, 0) + 1;
            right.put(c, count);
        }
        //System.err.println("right size: " + right.get('a'));
        for (int i = 0; i < S.length() - 1; i++) {
            char c = S.charAt(i);
            //System.err.println("char: " + c);
            left.put(c, left.getOrDefault(c, 0) + 1);
            right.put(c, right.get(c) - 1);
            //System.err.println(right.get(c));
            if (right.get(c) == 0) {
                right.remove(c);
            }
            if (left.keySet().size() == right.keySet().size()) {
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(solution("abaca"));
        System.out.println(solution("aaaa"));
    }
}

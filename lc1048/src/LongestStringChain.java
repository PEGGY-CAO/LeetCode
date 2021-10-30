import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {

    public static int findLongestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Map<String, Integer> dp = new HashMap<>();

        int result = 1;
        for (String w : words) {
            dp.put(w, 1);
            for (int i = 0; i < w.length(); i++) {
                StringBuilder sb = new StringBuilder(w);
                String predecessor = sb.deleteCharAt(i).toString();
                if (dp.containsKey(predecessor)) {
                    int newLength = dp.get(predecessor) + 1;
                    newLength = Math.max(newLength, dp.get(w));
                    result = Math.max(newLength, result);
                    dp.put(w, newLength);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        String[] test1 = {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        String[] test2 = {"abcd","dbqca"};
        System.out.println(findLongestStrChain(words));
    }
}

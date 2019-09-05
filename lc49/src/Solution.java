import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Solution {
    //time complexity: O(N*Klog(K)), where N is the length of strs, K is the max length of single string in strs
    //space complexity O(KN)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> dict = new HashMap<>();
        for (String s : strs) {
            String keyStr = sortStr(s);
            if (dict.keySet().contains(keyStr)) {
                List<String> temp = dict.get(keyStr);
                temp.add(s);
                dict.put(keyStr, temp);
            } else {
                List<String> values = new ArrayList<>();
                values.add(s);
                dict.put(keyStr, values);
            }

        }
        return new ArrayList(dict.values());
    }

    private String sortStr(String x) {
        char[] chars = x.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] str) {
        String[] test = {"eat","tea","tan","ate","nat","bat"};
        Solution solution = new Solution();
        List<List<String>> result = solution.groupAnagrams(test);
        for (List<String> s : result) {
            System.out.println(s);
        }

    }
}

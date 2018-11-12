import java.util.Map;
import java.util.HashMap;


public class DifferentTeams {
    static int differentTeams(String skills) {
        int length = skills.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char temp = skills.charAt(i);
            if (map.containsKey(temp)) {
                map.put(temp, map.get(temp) + 1);
            } else {
                map.put(temp, 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (char c : map.keySet()) {
            int freq = map.get(c);
            if (freq < min) {
                min = freq;
            }
        }
        if (map.keySet().size() == 5) {
            return min;
        }
        return 0;
    }
}

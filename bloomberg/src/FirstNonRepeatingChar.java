import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

public class FirstNonRepeatingChar {

    public static Character nonRepeatingFirst(String input) {
        //corner cases
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("input is not valid");
        }

        Map<Character, Integer> freq = new HashMap<>();
        Map<Character, Integer> indexLookup = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int counts = freq.getOrDefault(c, 0);

            if (!freq.containsKey(c)) {
                indexLookup.put(c, i);
            }
            freq.put(c, counts + 1);
        }

        for (Map.Entry<Character, Integer> entry : indexLookup.entrySet()) {
            if (freq.get(entry.getKey()) == 1) {
                return input.charAt(entry.getValue());
            }
        }
        return '0';
    }

    public static void main(String[] args) {
        System.out.println(nonRepeatingFirst("bloomberg"));
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpSideDownString {

    public static List<String> turnUpsideDown(List<String> input) {

        Map<Character, Character> lookup = new HashMap<>();
        char[][] dict = {{'a', 'e'}, {'b', 'q'}, {'d', 'p'}, {'f', 'f'}, {'h', 'y'}, {'i', 'i'}, {'l', 'l'}, {'m', 'w'},
                {'n', 'u'}, {'o', 'o'}, {'s', 's'}, {'t', 't'}, {'x', 'x'}, {'z', 'z'}};
        for (char[] pairs : dict) {
            lookup.put(pairs[0], pairs[1]);
            lookup.put(pairs[1], pairs[0]);
        }

        List<String> ans = new ArrayList<>();
        boolean valid;

        for (String s : input) {
            valid = true;
            StringBuilder sb = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (!lookup.keySet().contains(c)) {
                    valid = false;
                    break;
                }
                sb.append(lookup.get(c));

            }
            if (valid) {
                ans.add(sb.toString());
            }

        }
        return ans;
    }


    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("axe");
        input.add("play");
        input.add("invalid");
        input.add("swims");
        List<String> result = turnUpsideDown(input);
        for (String s : result) {
            System.out.println(s);
        }
    }
}

import java.lang.IllegalArgumentException;
import java.util.HashMap;
import java.util.HashSet;


public class MaxOccur {

    public static char maximumOccurringCharacter(String input) {

        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Input is not correct");
        }

        if (input.length() == 1 || input.length() == 2) {
            return input.charAt(0);
        }

        HashMap<Character, Integer> dict = new HashMap<>();
        int maxCount = 0;


        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            int count = dict.getOrDefault(current, 0) + 1;
            if (count > maxCount) {
                maxCount = count;
            }
            dict.put(current, count);

        }

        char result = input.charAt(0);

        for (int i = 0; i < input.length(); i++) {
            int count = dict.get(input.charAt(i));
            if (count == maxCount) {
                result = input.charAt(i);
                break;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String eg = "abbbaacc";
        char result = maximumOccurringCharacter(eg);
        System.out.println(result);
    }
}

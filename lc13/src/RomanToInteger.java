import java.util.Map;
import java.util.HashMap;

public class RomanToInteger {

    public int romanToInt(String s) {
        if (s.length() == 0 || s == null) {
            throw new IllegalArgumentException("string cannot be empty");
        }
        int length = s.length();
        Map<Character, Integer> m = new HashMap<>();
        /*
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
        */
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        int result = 0;
        for (int i = 0; i < length; i++) {
            System.out.println("result: " + result);

            int value = m.get(s.charAt(i));
            System.out.println("value: " + value);

            if (i == length - 1 || value >= m.get(s.charAt(i + 1))) {
                result += value;
            } else {
                result -= value;
            }
        }


        return result;
    }

    public static void main(String[] str) {
        RomanToInteger solution = new RomanToInteger();
        String c = "MCMXCIV";
        System.out.println(solution.romanToInt(c));
    }
}

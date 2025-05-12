import java.util.HashMap;
import java.util.*;

public class ParseString {


    public int parseString(String str) {

        for (int i = 1; i < str.length(); i++) {
            String subS = str.substring(0, i);
            boolean result = findRepetable(str.substring(i), subS);
            if (result) {
                return subS.length();
            }
        }
        return str.length();

    }
    //abaaab, a
    public boolean findRepetable(String rest, String target) {
        if (rest.equals(target)) {
            return true;
        }
        if (rest.length() % target.length() != 0) {
            return false;
        }
        int reapeats = rest.length() / target.length();
        boolean result = true;
        for (int i = 0; i < reapeats; i++) {
            boolean compaRes = compareString(rest.substring(i*target.length(), i + target.length()), target);
            result = result && compaRes;
            if (!result) {
                return result;
            }

        }
        return result;
    }

    public boolean compareString(String a, String b) {
        //compare anagram
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            int f = freq.getOrDefault(c, 0);
            f++;
            freq.put(c, f);
        }

        Map<Character, Integer> freqB = new HashMap<>();
        for (int i = 0; i < b.length(); i++) {
            char c = b.charAt(i);
            int f = freqB.getOrDefault(c, 0);
            f++;
            freqB.put(c, f);
        }
        //compare 2 maps
        return freq.equals(freqB);
    }

    public static void main(String[] args) {
        ParseString test = new ParseString();
        int result = test.parseString("aababa");
        System.out.println(result);
    }
}

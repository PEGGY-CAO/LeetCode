import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     Write a method that, given an input string, generates a longest palindrome by removing characters from the input string or rearranging characters in the input string.

     Example Inputs and Outputs :

     'aha' -> 'aha'
     'add' -> 'dad'
     'data' -> 'ada' (or 'ata')
     'dat' -> 'a' (or 'd' or 't')
     aabbccddegf -> abcdedcba, abcdfdcba, abcdgdcba, bacdedcab, …


     map key - char
     value - shown times

     */
    public String palindrome(String raw) {
        if (raw == null || raw.length() == 0) {
            throw new IllegalArgumentException();
        }
        //construct a map which each key is character and each value is its show times
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < raw.length(); i++) {
            char curr = raw.charAt(i);
            if (visited.contains(curr)) {
                //if visited, then update the times shown
                map.put(curr, map.get(curr) + 1);
            } else {
                map.put(curr, 1);
                visited.add(curr);

            }
        }
        Set<Character> charSet = map.keySet();
        //Set<Integer> valueSet = map.valueSet();
        //modify tha map so that the second time we see an odd show time, decrement it to make it to be even
        int countOdd = 0;
        for (Character charac : charSet) {
            int val = map.get(charac);
            if (val % 2 == 1) {
                countOdd++;
                if (countOdd > 1) {

                    val--;
                    map.put(charac, val);
                }

            }
        }

        //construct the result string
        StringBuilder result = new StringBuilder();
        StringBuilder endpart = new StringBuilder();
        char oddintheM = 0;
        //Set<Character> newkey = map.keySet();
        for (Character charac : charSet) {
            //get the show time for each character
            int valOfC = map.get(charac);

            for (int i = 0; i < valOfC / 2; i++) {
                if (valOfC / 2 > 0) {
                    result.append(charac);
                    endpart.insert(0, charac);
                    valOfC -=2;
                    //map.put(charac,  valOfC);
                }
            }
            if (valOfC % 2 == 1) {
                oddintheM = charac;
            }
        }
        if (countOdd > 0) {
            result.append(oddintheM);
        }
        return result.append(endpart).toString();


    }



    public static void main(String[] args) {
        String raw = "aaBbCCCdde";
        Solution sol = new Solution();
        String res = sol.palindrome(raw);
        System.out.println(res);
        //for (int i = 0; i < res.length(); i++)
    }
}

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
  {problem, promo} - add/edit/delete of chars. min
 */

class Solution {

    public int minEdit(String aaa, String bbb) {

        //corner cases
        if (aaa.length() == 0 || bbb.length() == 0) {
            return aaa.length() == 0? bbb.length() : aaa.length();
        }


        if (aaa.length() < bbb.length()) {
            return helper(bbb, aaa);
        }

        return helper(aaa, bbb);

    }

    private int helper(String aaa, String bbb) {


        int answer = 0;

        int i = 0;
        int j = 0;

        while(j < bbb.length() && i < aaa.length()) {
            char a = aaa.charAt(i);
            char b = bbb.charAt(j);
            System.out.println("a is : " + a);
            System.out.println("b is : " + b);
            //we need to add an character to bbb
            if (a != b) {
                i++;
                System.out.println("i is : " + i);
                answer++;
            } else {
                i++;
                j++;
            }


        }
        System.out.println("After while loop i is : " + i);

        // we need to add chars in the end of bbb
        if (j < bbb.length()) {
            answer += bbb.length() - j;
            return answer;
        }

        // the second string has finished construction but the first str has been go over
        if (j < i) {

            answer += aaa.length() - i;
        } else if (j > i) {

            answer += bbb.length() - j;
        }



        return answer;

    }


    public static void main(String[] args) {

        String first = "problem";
        String second = "promo";

        String example2 = "romo";
        String example1 = "";

        Solution s = new Solution();
        // int ans = s.minEdit(second, first);
        // System.out.println(ans);

        int test2 = s.minEdit(example1, example2);
        System.out.println(test2);

    }
}



// Cake System Design
// Map<String, float> dict


// List<String> topping
// List<String> userTopping

// List<String> flavor
// List<String> userFlavor


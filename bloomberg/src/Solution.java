import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String decode(String input) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // System.out.println(c);
            if  (Character.isDigit(c))  {
                k = 10 * k + c - '0';
            } else if (c == '[') {
                strStack.push(cur);
                //cur.setLength(0);
                cur = new StringBuilder();
                intStack.push(k);
                k = 0;
            } else if (c == ']') {
                StringBuilder temp = cur;
                // System.out.println(temp);
                cur = strStack.pop();
                // System.out.println("before while loop: " + cur);

                k = intStack.pop();
                // System.out.println(k);

                while (k > 0) {
                    cur.append(temp);
                    // System.out.println(k);
                    // System.out.println(cur);

                    k--;
                }
                // System.out.println("after while loop: " + cur);


            } else {
                cur.append(c);
            }
        }
        return cur.toString();

    }



    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String test1 = "13[a]2[b]";
        String res = decode(test1);
        System.out.println(res);
    }
}


import java.util.*;
import java.io.*;
public class BigButton {

    public static long solution(int n, int m, String[] forbiddenPrefix) {
        long count = 0;
        //Construct an int array forbiddenBitwise used to calculate later
        long[] forbiddenBitwise = new long[m];
        int[] leftShift = new int[m];
        for (int i = 0; i < m; i++) {

            String current = forbiddenPrefix[i];
//            System.out.println("current: " + forbiddenPrefix.length);
            long prefix = 0;
//            System.out.println("n: " + n + "current length" + current.length());
            leftShift[i] = n - current.length();
            for (int j = 0; j < current.length(); j++) {
                prefix = (prefix << 1);
                char c = current.charAt(j);
                if (c == 'R') {
                    prefix += 1;
                } else {
                    prefix += 0;
                }

            }
            forbiddenBitwise[i] = prefix;

        }

        //there are n buttons, so 2^n possible answer in total
        long max = (long) Math.pow(2, n);
        //brute force
        for (long i = 0; i < max; i++) {
            boolean valid = true;
            for (int j = 0; j < m; j++) {
                long temp = (i >> leftShift[j]);
                if (temp == forbiddenBitwise[j]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }

        }

        return count;
    }



    public static void main(String[] args) {
//        String file = "src/A-small-attempt0.in";

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        Scanner in = new Scanner(new BufferedReader(new File(file)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.

        BigButton sol = new BigButton();

        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            //System.out.println("n: " + n + " m: " + m);
            String[] forbiddenPrefix = new String[m];
            String empty = in.nextLine();
            for (int j = 0; j < m; j++) {
                String s = in.nextLine();
                forbiddenPrefix[j] = s;
                //System.out.println(s);
            }
            long result = sol.solution(n, m, forbiddenPrefix);


            System.out.println("Case #" + i + ": " + result);
        }
    }
}


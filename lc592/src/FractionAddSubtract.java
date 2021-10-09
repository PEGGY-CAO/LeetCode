import java.util.Scanner;

public class FractionAddSubtract {

    public static String addTwoFraction(String input) {

        Scanner sc = new Scanner(input).useDelimiter("/|(?=[-+])");
        //The (?=) part is a zero-width positive lookahead
        //the regex (?=[-+]) means the next element is either - or +.
        //Since | is logical or operator, "/|(?=[-+])" means the element is "/", or the next element is either - or +.
        // For example, when expression = "-1/2+1/2-1/3",
        //Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])")
        //generates [-1, 2, +1, 2, -1, 3 ]. Note that the signs - and + are preserved.

        long numerator = 0;
        long denominator = 1;

        while(sc.hasNext()) {
            long nextNumerator = sc.nextLong();
            long nextDenominator = sc.nextLong();
            numerator *= nextDenominator;
            numerator += denominator * nextNumerator;
            denominator *= nextDenominator;
            long g = numerator > denominator ? findgcd(numerator, denominator) :  findgcd(denominator, numerator);
            numerator /= g;
            denominator /= g;
        }

        return Long.toString(numerator) + "/" + Long.toString(denominator);
    }

    private static long findgcd(long numerator, long denominator) {
        //euclidean algorithm
        if (denominator == 0) {
            return numerator;
        } else {
            return findgcd(denominator, numerator % denominator);
        }
    }

    public static void main(String[] args) {
        String input = "1/2+5/6";
        System.out.println(addTwoFraction(input));
    }
}

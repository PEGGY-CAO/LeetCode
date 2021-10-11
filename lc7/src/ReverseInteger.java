public class ReverseInteger {

    public static int reverseInteger(int input) {
        boolean positive = true;
        if (input < 0) positive = false;

        int result = 0;

        while (input != 0) {
            result *= 10;
            int remainder = input % 10;
            result += remainder;
            input /= 10;

        }
        return result;
    }

    public static void main(String[] args) {
        int eg = 90247;
        System.out.println(reverseInteger(eg));
    }
}

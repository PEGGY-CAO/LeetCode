public class ReverseInteger {

    public static int reverseInteger(int input) {
        int result = 0;
        while (input != 0) {
            int remainder = input % 10;
            int newResult = result * 10 + remainder;
            if (newResult / 10 != result) {
                return 0;
            }
            result = newResult;
            input /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int eg = 90247;
        System.out.println(reverseInteger(eg));
    }
}

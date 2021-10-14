public class IsPowerOfTen {

    public static boolean isPowerOfTen(int x) {
        if (x < 1) return false;
        if (x == 1) return true;

        while (x > 1) {
            int remainder = x % 10;
            if (remainder != 0) return false;
            x /= 10;
        }

        if (x != 1) return false;
        return true;
    }


    public static void main(String[] args) {
        int x = -50;
        System.out.println(isPowerOfTen(x));
    }
}

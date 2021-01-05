public class CountDigit024 {

    public static int count024(int x) {
        int c = 0;
        if (x == 0) return 1;

        while (x > 0) {
            int lastDigit = x % 10;
            if (lastDigit == 0 || lastDigit == 2 || lastDigit == 4) {
                c++;
            }
            x = x / 10;
        }
        return c;
    }

    public static int countDigit024(int n) {
        int count = 0;

        for (int i = 0; i <= n; i++) {
            count+=count024(i);
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(countDigit024(61523));
    }
}

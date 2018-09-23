public class Champernowne {

    /**
     * the Champernowne constant C10 is a transcendental real constant whose decimal expansion has
     * important properties. It is named after economist and mathematician D. G. Champernowne,
     * who published it as an undergraduate in 1933.
     *
     * For base 10, the number is defined by concatenating representations of successive integers:
     *
     * C10 = 0.12345678910111213141516…
     * @param i the i th digit
     * @return the digit we found
     */
    public int solution(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("i should be greater than 0");
        }
        if (i < 10) {
            return i;
        }
        int count = 1;
        boolean flag = true;
        int dummy = i;
        int result = 0;
        while (flag) {
            dummy -= 9 * (int) Math.pow(10, count - 1) * count;
            if (dummy > 0) {
                count++;
            } else {
                flag = false;
                dummy += 9 * (int) Math.pow(10, count - 1) * count;
                //dummy = 811, count = 3
                int x = dummy / count;
                // x = 270
                int y = dummy % count;
                // y = 1
                int num = (int) Math.pow(10, count - 1) - 1 + x;
                // num = 369
                if (y == 0) {

                    result = num % 10;
                } else {
                    num++;
                    // num = 370
                    //num / (int) Math.pow(10, (count - y));
                    result = findDigit(num, y, count);
                }
            }
        }
        return result;
    }

    private int findDigit(int num, int y, int digits) {
        // 1 <= y < digits
        // num = 370, y = 1, digits = 3
        num /= (int) Math.pow(10, digits - y);
        return num % 10;

    }




    public static void main(String[] args) {
        Champernowne sol = new Champernowne();
        System.out.println(sol.solution(999));
    }
}

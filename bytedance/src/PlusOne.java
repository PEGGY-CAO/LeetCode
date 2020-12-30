import java.util.List;
import java.util.ArrayList;

public class PlusOne {

    public static String plusOne(List<Integer> m) {

        if (m == null || m.size() == 0) {
            return String.valueOf(1);
        }

        //skip 0 in the beginning
        int startDigit = 0;
        while(true) {
            if (m.get(startDigit) == 0) {
                if (startDigit == m.size() - 1) {
                    return String.valueOf(1);
                }
                startDigit++;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();

        //check if last digit is 9
        int lastDigit = m.get(m.size() - 1);

        if (lastDigit != 9) {
            //if last digit is not 9, simple increment then append from the beginning
            lastDigit++;
            for (int i = startDigit; i < m.size() - 1; i++) {
                sb.append(m.get(i));
            }
            sb.append(lastDigit);
        } else {
            //if last digit is 9, append from the end of the list to the beginning
            boolean carry = true;
            sb.insert(0, 0);
            int newEnd = m.size() - 2;
            while (carry && newEnd >= startDigit) {
                int currentDigit = m.get(newEnd);
                if (currentDigit != 9) {
                    currentDigit++;
                    sb.insert(0, currentDigit);
                    carry = false;
                } else {
                    //still 9
                    sb.insert(0, 0);
                }
                newEnd--;
            }
            while (newEnd >= startDigit) {
                sb.insert(0, m.get(newEnd));
                newEnd--;
            }
            if (carry) {
                sb.insert(0, 1);
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {

        int[] example1 = {1, 2, 3, 4, 5};
        int[] ex2 = {0, 0};
        List<Integer> m = new ArrayList<>(example1.length);
        for (int i = 0; i < ex2.length; i++) {
            m.add(ex2[i]);
        }

        String result = plusOne(m);
        System.out.println(result);
    }
}

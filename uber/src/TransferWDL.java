import java.util.Map;
import java.util.HashMap;

public class TransferWDL {

    public static String transfer(String inputString) {

        Map<Character, Integer> dicWDL = new HashMap<>();

        dicWDL.put('W', 0);
        dicWDL.put('D', 0);
        dicWDL.put('L', 0);

        for (int i = 0; i < inputString.length(); i++) {
            char c  = inputString.charAt(i);
            int existing = dicWDL.get(c);
            existing++;
            dicWDL.put(c, existing);
        }

        char maxChar;
        int max = Integer.MIN_VALUE;

        for (char c : dicWDL.keySet()) {
            int countOfChar = dicWDL.get(c);
            if (countOfChar > max) {
                maxChar = c;
                max = countOfChar;
            }
        }

        //concatenate result
        StringBuilder sb = new StringBuilder();
        while(max > 0) {
            int countOfW = dicWDL.get('W');
            int countOfD = dicWDL.get('D');
            int countOfL = dicWDL.get('L');

            if (countOfW != 0) {
                sb.append('W');
                countOfW--;
                dicWDL.put('W', countOfW);
            }

            if (countOfD != 0) {
                sb.append('D');
                countOfD--;
                dicWDL.put('D', countOfD);
            }
            if (countOfL != 0) {
                sb.append('L');
                countOfL--;
                dicWDL.put('L', countOfL);
            }
            max--;
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String ex1 = "LLLWWWDWDDDD";
        System.out.println(transfer(ex1));
    }
}

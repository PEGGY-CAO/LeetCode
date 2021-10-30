import java.util.Arrays;

public class AnagramDifference {

    public static int[] calculateEditDistance(String[] a, String[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            String first = a[i];
            String second = b[i];
            if (first.length() != second.length()) {
                result[i] = -1;
            } else {
                result[i] = editDistance(first, second);
            }
        }
        return result;
    }

    public static int editDistance(String f, String s) {
        int[] abc = new int[26];
        for (int i = 0; i < f.length(); i++) {
            abc[f.charAt(i) - 'a'] ++;
            abc[s.charAt(i) - 'a'] --;
        }
        return Arrays.stream(abc)
                .filter(i -> i > 0)
                .sum();
    }

    public static void main(String[] args) {

        String[] a = {"tea", "tea", "act"};
        String[] b = {"ate", "toe", "acts"};

        int[] result = calculateEditDistance(a, b);
        for (int i : result) {
            System.out.println(i);
        }
    }
}

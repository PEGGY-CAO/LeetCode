import java.util.Arrays;


public class SubstringKdist {
    int countkDist (String str, int k) {

        int length = str.length();
        int[] dummy = new int[26];
        int result = 0;

        for (int i = 0; i < length; i++) {
            int distinct = 0;
            Arrays.fill(dummy, 0);
            for (int j = i; j < length; j++) {
                //if this is a new character
                if (dummy[str.charAt(j) - 'a'] == 0)
                    distinct++;
                dummy[str.charAt(j) - 'a']++;
                if (distinct == k)
                    result++;

            }

        }

        return result;
    }


    public static void main(String[] args) {
        SubstringKdist res = new SubstringKdist();
        String ch = "abcbaa";
        int k = 3;
        System.out.println("Total substrings with exactly " +
                k +    " distinct characters : "
                + res.countkDist(ch, k));
    }
}

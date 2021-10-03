public class DeleteB {

    public static int minCost(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        //initialization
        int[] fromLeft = new int[s.length()];
        int[] fromRight = new int[s.length()];

        if (s.charAt(0) == 'b') fromLeft[0] = 1;
        if (s.charAt(s.length() - 1) == 'b') fromRight[s.length() - 1] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                fromLeft[i] = Math.min(fromLeft[i - 1] + 2, i + 1);
            } else {
                fromLeft[i] = fromLeft[i - 1];
            }
        }

        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == 'b') {
                fromRight[i] = Math.min(fromRight[i + 1] + 2, s.length() - i);
            } else {
                fromRight[i] = fromRight[i + 1];
            }
        }

        int result = Math.min(fromLeft[s.length() - 1], fromRight[0]);

        for (int i = 0; i < s.length() - 2; i++) {
            result = Math.min(result, fromLeft[i] + fromRight[i + 1]);
        }

        return result;

    }

    public static void main(String[] args) {
        String s1 = "aabaa";
        String s2 = "bbabaa";
        String s3 = "abba";
        System.out.println(minCost(s3));
    }
}
